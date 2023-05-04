package connect.web.domain.messenger;

public class ChatMessagesDto {
    private int chatMessagesId; //메세지 고유 번호, 자동증가
    private String content; // 메세지 내용
    private int memberNo; //보낸사람 멤버
    private int chatRoomId; // 보낸 채팅방 id
    private String msgType; // 메세지 타입
    private String filePath;// 파일 경로

    //단순 메세지만 보내기
    public ChatMessagesEntity toEntity_Message(){
        return ChatMessagesEntity.builder()
                .content(this.content)
                .build();
    }

    //파일 보내기
    public ChatMessagesEntity toEntity_file(){
        return ChatMessagesEntity.builder()
                .msgType(this.msgType)
                .filePath(this.filePath)
                .build();
    }
}
