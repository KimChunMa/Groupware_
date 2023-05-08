package connect.web.domain.messenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagesDto {
    private int chatMessagesId; //메세지 고유 번호, 자동증가
    private String content; // 메세지 내용
    private int memberNo; //보낸사람 멤버
    private int chatRoomId; // 보낸 채팅방 id
    private String msgType; // 메세지 타입
    private String filePath;// 파일 경로


    public ChatMessagesEntity toEntity(){
        return ChatMessagesEntity.builder()
                .content(this.content)
                .msgType(this.msgType)
                .filePath(this.filePath)
                .build();
    }


}
