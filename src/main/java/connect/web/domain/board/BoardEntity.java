package connect.web.domain.board;

import connect.web.domain.BaseTime;
import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.PartEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Entity @Table(name="board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardEntity extends BaseTime {
    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int boardNo;
    @Column(nullable = false) private String boardTitle;
    @Column(columnDefinition = "longtext") private String boardContent;
    @Column @ColumnDefault("0")
    private int boardView;

    @ManyToOne
    @JoinColumn(name="memberNo")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name="partNo")
    @ToString.Exclude
    private PartEntity partEntity;
}

