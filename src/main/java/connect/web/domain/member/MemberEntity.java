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
    private int memberNo;

    @Column private String memberId;
    @Column private String memberPwd;
    @Column private String memberName;
    @Column private String memberPhone;
    @Column private String memberEmail;
    @Column private String memberProfile;
    @Column private char memberRank;

    @ManyToOne
    @JoinColumn( name = "partNo")
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

    // 1. 출력용 [ 세션 ]
    public MemberDto toDto() {
        return MemberDto.builder()
                .memberNo( this.memberNo )
                .memberId( this.memberId )
                .memberName( this.memberName )
                .memberPhone( this.memberPhone )
                .memberEmail( this.memberEmail )
                .memberRank( this.memberRank )
                .partNo( this.partEntity.getPartNo() )
                .build();
    }


}
