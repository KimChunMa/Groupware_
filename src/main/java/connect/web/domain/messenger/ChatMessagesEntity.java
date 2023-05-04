package connect.web.domain.messenger;

import connect.web.domain.BaseTime;
import connect.web.domain.member.MemberEntity;
import lombok.*;
import javax.persistence.*;

@Entity @Table(name="ChatMessages")
@Builder@Data
@NoArgsConstructor@AllArgsConstructor
//메세지 테이블
public class ChatMessagesEntity extends BaseTime {  // 메세지 생성날짜를 전달받기위함
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int chatMessages_id; //메세지 고유 번호, 자동증가

    @Column
    private String content; // 메세지 내용

    @ManyToOne
    @JoinColumn(name="member_no")
    @ToString.Exclude
    private MemberEntity memberEntity; //멤버 ID

    @ManyToOne
    @JoinColumn(name="chatRoom_id")
    @ToString.Exclude
    private ChatRoomsEntity chatRoomsEntity; //  채팅방 ID (fk)


    @Column
    private String msg_type; // 파일 타입

    @Column
    private String file_path; //파일 경로

}
