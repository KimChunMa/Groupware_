package connect.web.domain.approval;

import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.PartEntity;
import lombok.*;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Table( name = "approval")
public class ApprovalEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int approvalNo;

    @Column private String approvalWriter;
    @Column private String approvalTitle;
    @Column private String approvalContent;
    @Column private String approvalStatus;
    @Column private String approvalData;

    @ManyToOne
    @JoinColumn( name = "memberNo")
    @ToString.Exclude
    private MemberEntity memberEntity;


    //Entity -> Dto하기 (출력용) => {결재pk번호, 작성자, 제목, 내용, 결재단계, 날짜}

    public  ApprovalDto approvalDto(){
        return ApprovalDto.builder()
                .approvalNo(this.approvalNo)
                .approvalWriter(this.approvalWriter)
                .approvalTitle(this.approvalTitle)
                .approvalContent(this.approvalContent)
                .approvalStatus(this.approvalStatus)
                .approvalData(this.approvalData)
                .build();

    }


}