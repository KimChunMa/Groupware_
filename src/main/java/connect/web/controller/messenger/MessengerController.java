package connect.web.controller.messenger;

import connect.web.domain.member.MemberDto;
import connect.web.domain.messenger.ChatMessagesDto;
import connect.web.domain.messenger.ChatRoomsDto;
import connect.web.service.messenger.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessengerController {
    @Autowired
    private MessengerService messengerService;

    //----------------------- 로그인 기능 --------------------------------
    //0. 로그인된 MemberNo() 꺼내오기
    @GetMapping("/login")
    public MemberDto loginMemberNo(){
        return messengerService.loginMember();
    }

    //----------------------- 채팅방 기능 --------------------------------
    //1.방 만들기
    @PostMapping("")
    public boolean CreateChat (@RequestBody ChatRoomsDto chatRoomsDto){
        return messengerService.createChat(chatRoomsDto);
    }

    //2. 방 출력하기
    @GetMapping("")
    public List<ChatRoomsDto> printChat(){
        return messengerService.printChat();
    }

    //3. 방 수정하기
    @PutMapping("")
    public boolean editChat (@RequestParam int chatRoomId, @RequestParam String name){
        return messengerService.editChat(chatRoomId, name);
    }

    //4. 방삭제하기
    @DeleteMapping("")
    public boolean deletChat(@RequestParam int chatRoomId){
        return messengerService.deletChat(chatRoomId);
    }

    //------------------------------------- 메세지 보내기 ----------------------------------
    //1. 메세지 보내기
    @PostMapping("/message")
    public boolean sendMessages(@RequestBody ChatMessagesDto chatMessagesDto){
        return messengerService.sendMessages(chatMessagesDto);
    }

    //2. 메세지 출력하기
    @GetMapping("/message")
    public List<ChatMessagesDto> printMessages(@RequestParam  int chatRoomId) {
        System.out.println("----------------------------------------");
        System.out.println(chatRoomId);
        return  messengerService.printMessages(chatRoomId);
    }

    //3. 메세지 수정하기
    @PutMapping("/message")
    public boolean editMessages(@RequestParam int chatMessagesId,@RequestParam String content){
        return messengerService.editMessages(chatMessagesId, content);
    }

    //4. 메세지 삭제하기
    @DeleteMapping("/message")
    public boolean DeleteMessages(int chatMessagesId){
        return messengerService.DeleteMessages(chatMessagesId);
    }

}
