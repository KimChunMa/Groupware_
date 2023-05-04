package connect.web.controller.messenger;

import connect.web.domain.messenger.ChatRoomsDto;
import connect.web.service.messenger.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class MessengerController {
    @Autowired
    private MessengerService messengerService;

    @PostMapping("")
    public boolean Create_chat (@RequestBody ChatRoomsDto chatRoomsDto){
        return messengerService.Create_chat(chatRoomsDto);
    }
}
