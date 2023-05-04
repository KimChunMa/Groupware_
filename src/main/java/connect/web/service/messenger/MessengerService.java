package connect.web.service.messenger;

import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.MemberEntityRepository;
import connect.web.domain.messenger.ChatRoomsDto;
import connect.web.domain.messenger.ChatRoomsEntity;
import connect.web.domain.messenger.ChatRoomsEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class MessengerService {

    @Autowired
    ChatRoomsEntityRepository chatRoomsEntityRepository;
    @Autowired
    MemberEntityRepository memberEntityRepository;

    public  boolean Create_chat(ChatRoomsDto chatRoomsDto){

        // 1.로그인 된 사람인지 검사 (유효성)

        //2. 있는 회원인지 검사 (유효성)
        MemberEntity memberEntity =
                memberEntityRepository.findById(chatRoomsDto.getMember_no()).get();

        //없는 회원이라면 중단
        if(memberEntity != null){  return false;  }


        //DB에 넣기
        ChatRoomsEntity chatRoomsEntity = chatRoomsDto.toEntity();
        chatRoomsEntityRepository.save(chatRoomsEntity);
    return true;
    }



}
