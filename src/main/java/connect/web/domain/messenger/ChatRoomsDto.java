package connect.web.domain.messenger;


import lombok.*;


@Data@Builder
@NoArgsConstructor@AllArgsConstructor
public class ChatRoomsDto {
    private int chatRoomId; //채팅방 고유 번호, 자동증가
    private String name; //채팅방 이름
    private String cdate; // 채팅방 생성 날짜
    private int memberNo; //방주인

    public ChatRoomsEntity toEntity(){
        return ChatRoomsEntity.builder()
                .name(this.name)
                .build();
    }
}

