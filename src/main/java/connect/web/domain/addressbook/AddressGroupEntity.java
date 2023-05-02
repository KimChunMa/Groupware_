package connect.web.domain.addressbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table( name = "address_group")
public class AddressGroupEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int group_no;

    @Column private String group_name;
    @Column private char group_type;
    @Column private int reg_member_no;



}

