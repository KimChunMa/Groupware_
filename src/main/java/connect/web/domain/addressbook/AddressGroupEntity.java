package connect.web.domain.addressbook;

import connect.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Table( name = "addressGroup")
public class AddressGroupEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int groupNo;

    @Column private String groupName;
    @Column private char groupType;

    @ManyToOne
    @JoinColumn( name = "memberNo")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @OneToMany( mappedBy = "addressGroupEntity")
    @Builder.Default
    private List<AddressBookEntity> addressBookEntityList = new ArrayList<>();


}

