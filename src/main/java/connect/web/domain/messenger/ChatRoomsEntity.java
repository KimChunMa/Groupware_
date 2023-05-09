package connect.web.domain.messenger;

import connect.web.domain.BaseTime;
import connect.web.domain.member.MemberEntity;
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
    private int chatRoomId; //채팅방 고유 번호, 자동증가

    @Column
    private String name; //채팅방 이름

    @ManyToOne
    @JoinColumn(name="memberNo")
    @ToString.Exclude
    private MemberEntity memberEntity; //방주인

    public ChatRoomsDto toDto(){
        return ChatRoomsDto.builder()
                .chatRoomId(this.chatRoomId)
                .name(this.name)
                .cdate(
                    //만약 작성 날짜/시간중 날짜가 현재 날짜와 동일하다면
                    this.cdate.toLocalDate().toString().equals(LocalDateTime.now().toLocalDate().toString()) ?
                    this.cdate.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) :
                    this.cdate.toLocalDate().format(DateTimeFormatter.ofPattern("yy-MM-dd") )
                )
                .memberNo(this.memberEntity.getMemberNo())
                .build();
    }
}

