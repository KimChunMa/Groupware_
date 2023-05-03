package connect.web.domain.addressbook;


import connect.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Table( name = "addressbook")
public class AddressBookEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int addr_no ;

    @Column private String addr_name;
    @Column private String addr_phone;
    @Column private String addr_email;

    @ManyToOne
    @JoinColumn( name = "group_no")
    @ToString.Exclude
    private AddressGroupEntity addressGroupEntity;

    @ManyToOne
    @JoinColumn( name = "member_no")
    @ToString.Exclude
    private MemberEntity memberEntity;

}
