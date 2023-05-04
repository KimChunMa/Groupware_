package connect.web.domain.messenger;

import connect.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Entity @Table(name="ChatParticipants")
@Builder @Data
@NoArgsConstructor @AllArgsConstructor
//채팅참여자 테이블
public class ChatParticipantsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatParticipants_id; //채팅방 참여자 고유 번호, 자동증가

    @ManyToOne
    @JoinColumn(name="member_no")
    @ToString.Exclude
    private MemberEntity memberEntity; //멤버 ID


    @ManyToOne
    @JoinColumn(name="chatRoom_id")
    @ToString.Exclude
    private ChatRoomsEntity chatRoomsEntity; //  채팅방 ID (fk)
}
