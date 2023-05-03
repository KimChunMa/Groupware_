package connect.web.domain.addressbook;

import connect.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Table( name = "address_group")
public class AddressGroupEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int group_no;

    @Column private String group_name;
    @Column private char group_type;

    @ManyToOne
    @JoinColumn( name = "member_no")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @OneToMany( mappedBy = "addressGroupEntity")
    @Builder.Default
    private List<AddressBookEntity> addressBookEntityList = new ArrayList<>();


}

