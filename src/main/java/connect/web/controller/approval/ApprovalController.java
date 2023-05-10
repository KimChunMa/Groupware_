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
        return result;
    }


    //내가 쓴 서류 상태출력
    @GetMapping("/alist")
    public List<ApprovalDto> approvalDtoList( ){
        List<ApprovalDto> result = approvalService.approvalDtoList();
        log.info("c result:::"+result);
        return result;
    }

/*    @GetMapping("/alist")
    public List<ApprovalDto> approvalDtoList( ){
        List<ApprovalDto> result = approvalService.totalApproval();
        log.info("c 모든 게시물출력 result:::"+result);
        return result;
    }*/


}
