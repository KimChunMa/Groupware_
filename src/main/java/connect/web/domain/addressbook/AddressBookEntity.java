package connect.web.domain.addressbook;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table( name = "addressbook")
public class AddressBookEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int addr_no ;

    @Column private String addr_name;
    @Column private String addr_phone;
    @Column private String addr_email;
    @Column private int group_no;
    @Column private int reg_member_no;

}
