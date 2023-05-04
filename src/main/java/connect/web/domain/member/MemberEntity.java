package connect.web.domain.member;

import connect.web.domain.addressbook.AddressBookEntity;
import connect.web.domain.addressbook.AddressGroupEntity;
import connect.web.domain.board.BoardEntity;
import connect.web.domain.messenger.ChatMessagesEntity;
import connect.web.domain.messenger.ChatParticipantsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
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

    @ManyToOne
    @JoinColumn( name = "part_no")
    @ToString.Exclude
    private PartEntity partEntity;

    @OneToMany( mappedBy = "memberEntity" )
    @Builder.Default
    private List<AddressGroupEntity> addressGroupEntityList = new ArrayList<>();

    @OneToMany( mappedBy = "memberEntity" )
    @Builder.Default
    private List<AddressBookEntity> addressBookEntityList = new ArrayList<>();

    @OneToMany( mappedBy = "memberEntity" )
    @Builder.Default
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany( mappedBy = "memberEntity" )
    @Builder.Default
    private List<ChatMessagesEntity> chatMessagesEntities = new ArrayList<>();

    @OneToMany( mappedBy = "memberEntity")
    @Builder.Default
    private List<ChatParticipantsEntity> chatParticipantsEntities = new ArrayList<>();

}
