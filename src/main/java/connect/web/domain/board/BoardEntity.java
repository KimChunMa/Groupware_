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
    private int board_no;
    @Column(nullable = false) private String board_title;
    @Column(columnDefinition = "longtext") private String board_content;
    @Column @ColumnDefault("0")
    private int board_view;

    @ManyToOne
    @JoinColumn(name="member_no")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name="part_no")
    @ToString.Exclude
    private PartEntity partEntity;
}
