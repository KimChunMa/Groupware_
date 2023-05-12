package connect.web.service.messenger;

import connect.web.controller.messenger.ChattingHandler;
import connect.web.domain.member.MemberDto;
import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.MemberEntityRepository;
import connect.web.domain.messenger.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class MessengerService {

    @Autowired
    ChatRoomsEntityRepository chatRoomsEntityRepository;
    @Autowired
    ChatParticipantsEntityRepository chatParticipantsEntityRepository;

    @Autowired
    ChatMessagesEntityRepository chatMessagesEntityRepository;
    @Autowired
    MemberEntityRepository memberEntityRepository;

    @Autowired
    HttpServletRequest request;


    /* ---------------------- 0. 로그인 검사 ------------------------- */
    public MemberDto loginMember(){
        String login = (String)request.getSession().getAttribute("login");

        //1. 있는 회원인지 검사 (유효성)
        Optional<MemberEntity> optionalMemberEntity =
                memberEntityRepository.findByMemberId(login);
        //1-2.있으면 MemberNo() 검사
        if(optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity  = optionalMemberEntity.get();
            return memberEntity.toDto();
        }
        return null;
    }

    //* ---------------------- 채팅방 생성 기능 ------------------------- */
    //1. 방생성하기
    @Transactional
    public  boolean createChat(ChatRoomsDto chatRoomsDto){
        // 1-1. 로그인된 회원 빼오기 {header 에서 들고오기}
        MemberEntity memberEntity = memberEntityRepository.findById(chatRoomsDto.getMemberNo()).get();
        // 1-2. ChatRoom html -> dto -> entity
        ChatRoomsEntity chatRoomsEntity = chatRoomsDto.toEntity();
        //1-3. chatRooms <- member 단방향
        chatRoomsEntity.setMemberEntity(memberEntity);
        chatRoomsEntityRepository.save(chatRoomsEntity);
        //1-4. chatRooms -> chatParticipants <- member 단방향
        ChatParticipantsEntity chatParticipantsEntity = new ChatParticipantsEntity();
        chatParticipantsEntity.setChatRoomsEntity(chatRoomsEntity);
        chatParticipantsEntity.setMemberEntity(memberEntity);
        chatParticipantsEntityRepository.save(chatParticipantsEntity);
        return true;
    }

    //2.본인이 속한 채팅방 출력
    @Transactional
    public List<ChatRoomsDto> printChat (){
        //1. 회원정보 빼오기 {java에서 빼오기}
        MemberEntity memberEntity =
                memberEntityRepository.findByMemberId(loginMember().getMemberId()).get();

        // 자신이 속한 chatRooms(Id) 여러개 찾기 (ChatParticipantsEntity)
        List<ChatParticipantsEntity> chatParticipantsEntityList =
                chatParticipantsEntityRepository.findByMemberNo(memberEntity.getMemberNo());

        // 채팅방 리스트
        List<ChatRoomsDto> chatRoomsDtoList = new ArrayList<>();

        // chatParticipantsEntityList 과 ChatRooms의 memberNo 일치하는것 찾기
        chatParticipantsEntityList.forEach( (o)-> {
            chatRoomsDtoList.add(chatRoomsEntityRepository.findByChatRoomId(o.getChatRoomsEntity()
                    .getChatRoomId()).toDto());
        });
        return chatRoomsDtoList;
    }

    //3. 방 이름 수정하기
    @Transactional
    public boolean editChat (ChatRoomsDto chatRoomsDto){
        //현재 수정할려는 채팅방 검사
        Optional<ChatRoomsEntity> optionalChatRoomsEntity =
                chatRoomsEntityRepository.findById(chatRoomsDto.getChatRoomId());

        if( optionalChatRoomsEntity.get().getMemberEntity().getMemberNo() != loginMember().getMemberNo()){
            return false;
        }
        optionalChatRoomsEntity.get().setName(chatRoomsDto.getName());
        return true;
    }

    //4. 방 삭제하기
    @Transactional
    public boolean deletChat(int chatRoomId){
        //방주인 인지 검사
        MemberEntity memberEntity =
                memberEntityRepository.findById( loginMember().getMemberNo()).get();

        ChatRoomsEntity chatRoomsEntity = chatRoomsEntityRepository.findById(chatRoomId).get();

        if(memberEntity.getMemberNo() == chatRoomsEntity.getMemberEntity().getMemberNo()) {
            System.out.println("------------------ 일치함");
            System.out.println(chatRoomsEntity);
            //삭제
            chatRoomsEntityRepository.delete(chatRoomsEntity);
            return true;
        }
        return false;
    }

    //---------------------------- 메세지 보내기 -------------------------
    //1. 메세지 보내기
    @Transactional
    public boolean sendMessages(ChatMessagesDto messagesDto){
        //1) 현재 받은 메세지를 Entity로 변환
        ChatMessagesEntity chatMessagesEntity = messagesDto.toEntity();
        //2)변환한 Entity에 멤버,채팅방 넣기
        MemberEntity memberEntity = memberEntityRepository.findById(messagesDto.getMemberNo()).get();
        ChatRoomsEntity chatRoomsEntity = chatRoomsEntityRepository.findById(messagesDto.getChatRoomId()).get();
        //chatMessages <- memberEntity
        chatMessagesEntity.setMemberEntity(memberEntity);
        //chatMessages <- chatRooms
        chatMessagesEntity.setChatRoomsEntity(chatRoomsEntity);
        chatMessagesEntityRepository.save(chatMessagesEntity);

        try { // 메시지가 도착한 방번호 전송 [ 해당 방번호를 최신화 할려고 ]
            chattingHandler.handleMessage(null, new TextMessage(String.valueOf(messagesDto.getChatRoomId())) );
        }
        catch (Exception e ){}
        return true;
    }

    @Autowired
    ChattingHandler chattingHandler;

    //2. 메세지 출력
    @Transactional
    public List<ChatMessagesDto> printMessages(int chatRoomId){
        System.out.println("-----------------------------");
        System.out.println(chatRoomId);
        //현재 채팅방에 속한 메세지 가져오기
        List<ChatMessagesEntity> chatMessagesEntityList =
                chatMessagesEntityRepository.findAllByChatRoomId(chatRoomId);

        //속한 메세지 전체 dto 변환
        List<ChatMessagesDto> chatMessagesDtoList = new ArrayList<>();

        chatMessagesEntityList.forEach((o)->{
            chatMessagesDtoList.add(o.toDto());
        });
        return chatMessagesDtoList;
    }

    //3. 메세지 수정하기
    @Transactional
    public boolean editMessages(int chatMessagesId, String content){
        //메세지 보낸 사람과 일치한지 검사

        //메세지 수정하기
        Optional<ChatMessagesEntity> chatMessagesEntity =
            chatMessagesEntityRepository.findById(chatMessagesId);
        chatMessagesEntity.get().setContent(content);

        return true;
    }

    //4. 삭제하기
    @Transactional
    public boolean DeleteMessages(int chatMessagesId){
        //메세지 보낸 사람과 일치한지 검사
        //메세지 삭제하기
        Optional<ChatMessagesEntity> chatMessagesEntity =
                chatMessagesEntityRepository.findById(chatMessagesId);

        chatMessagesEntityRepository.delete(chatMessagesEntity.get());
        return true;
    }
}
