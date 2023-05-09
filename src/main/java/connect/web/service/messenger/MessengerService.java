package connect.web.service.messenger;

import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.MemberEntityRepository;
import connect.web.domain.messenger.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /* ---------------------- 채팅방 생성 기능 ------------------------- */
    //1. 방생성하기
    public  boolean createChat(ChatRoomsDto chatRoomsDto){

        // 1.로그인 된 사람인지 검사 (유효성)

/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //2. 있는 회원인지 검사 (유효성)
        MemberEntity memberEntity =
                memberEntityRepository.findById(chatRoomsDto.getMemberNo()).get();

        //없는 회원이라면 중단
        if(memberEntity != null){  return false;  }

*/

        //chatRooms DB 입력
        ChatRoomsEntity chatRoomsEntity = chatRoomsDto.toEntity();
        chatRoomsEntityRepository.save(chatRoomsEntity);

        //chatParticipants DB 입력
        ChatParticipantsEntity chatParticipantsEntity = new ChatParticipantsEntity();
        chatParticipantsEntity.setChatRoomsEntity(chatRoomsEntity);
        chatParticipantsEntityRepository.save(chatParticipantsEntity);

    return true;
    }

    //2.본인이 속한 채팅방 출력
    public List<ChatRoomsDto> printChat (){
        //로그인 회원 id 추출
        MemberEntity memberEntity = memberEntityRepository.findById(1).get();

        // 자신이 속한 채팅방 번호(Id) 찾기
        List<ChatParticipantsEntity> chatParticipantsEntityList =
            chatParticipantsEntityRepository.findByMemberNo(memberEntity.getMemberNo());

        // 채팅방 리스트 넣기
        List<ChatRoomsDto> chatRoomsDtoList = new ArrayList<>();

        chatParticipantsEntityList.forEach( (o)-> {
            chatRoomsDtoList.add(o.getChatRoomsEntity().toDto());
        });
        return chatRoomsDtoList;
    }

    //3. 방 이름 수정하기
    public boolean editChat (int chatRoomId, String name){
        //방주인 인지 검사

        //현재 수정할려는 채팅방 검사
        Optional<ChatRoomsEntity> chatRoomsEntity =
                chatRoomsEntityRepository.findById(chatRoomId);

        chatRoomsEntity.get().setName(name);

        return true;
    }

    //4. 방 삭제하기
    public boolean deletChat(int chatRoomId){
        //방주인 인지 검사

        //삭제할려는 방검사
        Optional<ChatRoomsEntity> chatRoomsEntity =
                chatRoomsEntityRepository.findById(chatRoomId);
        //삭제
        chatRoomsEntityRepository.delete(chatRoomsEntity.get());
        return true;
    }

    //---------------------------- 메세지 보내기 -------------------------
    //1. 메세지 보내기
    public boolean sendMessages(ChatMessagesDto messagesDto){
        //현재 받은 메세지를 Entity로 변환
        ChatMessagesEntity chatMessagesEntity = messagesDto.toEntity();
        //변환한 Entity에 멤버,채팅방 넣기
        chatMessagesEntity.getMemberEntity().setMemberNo(1);
        chatMessagesEntity.getChatRoomsEntity().setChatRoomId(messagesDto.getChatRoomId());
        chatMessagesEntityRepository.save(messagesDto.toEntity());

        return true;
    }

    //2. 메세지 출력
    public List<ChatMessagesDto> printMessages(int chatRoomId){
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
    public boolean editMessages(int chatMessagesId, String content){
        //메세지 보낸 사람과 일치한지 검사

        //메세지 수정하기
        Optional<ChatMessagesEntity> chatMessagesEntity =
            chatMessagesEntityRepository.findById(chatMessagesId);
        chatMessagesEntity.get().setContent(content);

        return true;
    }

    //4. 삭제하기
    public boolean DeleteMessages(int chatMessagesId){
        //메세지 보낸 사람과 일치한지 검사

        //메세지 삭제하기
        Optional<ChatMessagesEntity> chatMessagesEntity =
                chatMessagesEntityRepository.findById(chatMessagesId);

        chatMessagesEntityRepository.delete(chatMessagesEntity.get());
        return true;
    }
}
