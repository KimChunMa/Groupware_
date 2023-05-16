package connect.web.domain.board;

import connect.web.domain.BaseTime;
import connect.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;


@Entity@Table(name="reply")
@Data @NoArgsConstructor@AllArgsConstructor@Builder
public class ReplyEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyNo;
    @Column
    private String replyContent;
    // 작성자 fk
    @ManyToOne @JoinColumn(name="memberNo") @ToString.Exclude
    private MemberEntity memberEntity;
    // 게시물fk
    @ManyToOne @JoinColumn(name="boardNo") @ToString.Exclude
    private BoardEntity boardEntity;

}
