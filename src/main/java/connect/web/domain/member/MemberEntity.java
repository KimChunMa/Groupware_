package connect.web.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table( name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int member_no;

    @Column private String member_id;
    @Column private String member_pwd;
    @Column private String member_name;
    @Column private String member_phone;
    @Column private String member_email;
    @Column private String member_profile;
    @Column private char member_rank;
    @Column private int part_no;


}
