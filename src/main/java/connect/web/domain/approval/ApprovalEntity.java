package connect.web.domain.approval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Table( name = "approval")
public class ApprovalEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int approval_no;

    @Column private String approval_writer;
    @Column private String approval_title;
    @Column private String approval_content;
    @Column private byte approval_status;
    @Column private String approval_data;


}