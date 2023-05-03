package connect.web.domain.messenger;

import connect.web.domain.BaseTime;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity @Table(name="ChatRooms")
@Data@Builder
@NoArgsConstructor@AllArgsConstructor
//채팅방 테이블
public class ChatRoomsEntity extends BaseTime { // 채팅방 생성날짜를 전달받기위함

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatRoom_id; //채팅방 고유 번호, 자동증가

    @Column
    private String name; //채팅방 이름

    public ChatRoomsDto toDto(){
        return ChatRoomsDto.builder().chatRoom_id(this.chatRoom_id)
                .name(this.name)
                .cdate(
                        this.cdate.toLocalDate().toString().equals(LocalDateTime.now().toLocalDate().toString())?
                        this.cdate.toLocalDate().format(DateTimeFormatter.ofPattern("HH:mm:ss")):
                        this.cdate.toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd"))
                )
                .build();

    }
}

