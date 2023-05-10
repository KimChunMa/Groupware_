package connect.web.domain.member;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class MemberDto {

    private int memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private MultipartFile memberProfile;
    private char memberRank;
    private int partNo;

    // ----------------------------------------------------------------



    public MemberEntity toEntity () {
        return MemberEntity.builder()
                .memberId( this.memberId )
                .memberPwd( this.memberPwd )
                .memberName( this.memberName )
                .memberPhone( this.memberPhone )
                .memberEmail( this.memberEmail )
                .memberRank( this.memberRank )
                .memberProfile( this.memberProfile.getOriginalFilename() )
                .partEntity( PartEntity.builder()
                            .partNo( this.partNo )
                            .build() )
                .build();
    }
}

