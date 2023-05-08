package connect.web.controller.approval;


import connect.web.domain.approval.ApprovalDto;
import connect.web.service.approval.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    //서류 등록
    @PostMapping("/awrite")
    public boolean approvalWrite(@RequestBody ApprovalDto approvalDto  ){
        log.info("c approvalWrite : "+approvalDto);
        boolean result = approvalService.approvalWrite(approvalDto);
        return true;
    }


/*    //서류 상태출력
    @GetMapping("/approval/list")
    public List<ApprovalDto> approvalDtoList(  ){
        log.info("c approvalDtoList : " );
        List<ApprovalDto> result = approvalService.approvalDtoList(  );
        return result;
    }*/


}
