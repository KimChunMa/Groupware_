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

    public ReplyDto todto(){
        return ReplyDto.builder()
                .replyNo(this.replyNo).replyContent(this.replyContent)
                .replyDate(this.cdate.toLocalDate().toString())
                .memberNo(this.memberEntity.getMemberNo())
                .memberName(this.memberEntity.getMemberName())
                .build();
    }
}
