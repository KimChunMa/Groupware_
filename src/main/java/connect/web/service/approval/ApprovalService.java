package connect.web.service.approval;

import connect.web.domain.approval.ApprovalDto;
import connect.web.domain.approval.ApprovalEntity;
import connect.web.domain.approval.ApprovalEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ApprovalService {

    @Autowired
    private ApprovalEntityRepository approvalEntityRepository;

    //서류 등록

    @Transactional
    public boolean approvalWrite( ApprovalDto approvalDto){
        log.info("c approvalWrite"+approvalDto);

        ApprovalEntity approvalEntity =
                approvalEntityRepository.save(approvalDto.toApprovalEntity());

        if(approvalEntity.getApprovalNo() >=1 ){
            return true;
        }
        return false;
    }


/*    //서류 상태출력
    @Transactional
    public List<ApprovalDto> approvalDtoList(  ){
        log.info("c approvalDtoList : " );

        List<ApprovalDto> ApprovalDtoList = ApprovalEntityRepository.findAll();

        List<ApprovalDto> list = new ArrayList<>();
        ApprovalDtoList.forEach((e) -> {
            list.add(new ApprovalDto(
                    e.getApproval_no(),
                    e.getApproval_writer(),
                    e.getApproval_title(),
                    e.getApproval_content(),
                    e.getApproval_status(),
                    e.getApproval_data()
            ));
        });
                return list;

    } //출력 e*/

}//class e
