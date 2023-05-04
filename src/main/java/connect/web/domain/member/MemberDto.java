package connect.web.domain.member;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class MemberDto {

    private int member_no;
    private String member_id;
    private String member_pwd;
    private String member_name;
    private String member_phone;
    private String member_email;
    private MultipartFile member_profile;
    private char member_rank;

    // ----------------------------------------------------------------



    public MemberEntity toEntity () {
        return MemberEntity.builder()
                .member_id( this.member_id )
                .member_pwd( this.member_pwd )
                .member_name( this.member_name )
                .member_phone( this.member_phone )
                .member_email( this.member_email )
                .member_rank( this.member_rank )
                .member_profile( this.member_profile.getOriginalFilename() )
                .build();
    }
}

