package connect.web.controller.approval;


import connect.web.domain.approval.ApprovalDto;
import connect.web.service.approval.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    HttpServletRequest request;


    //서류 등록
    @PostMapping("/awrite")
    public boolean approvalWrite(@RequestBody ApprovalDto approvalDto  ){
        log.info("c approvalWrite : "+approvalDto);
        boolean result = approvalService.approvalWrite(approvalDto);
        return result;
    }




   //STATUS 상태에따른 서류 [2023-05-11 월 작업 ]
    @GetMapping("/getUserRank")
    public List<ApprovalDto> approvalDtoList( ){
        List<ApprovalDto> result = approvalService.approvalDtoList();
        log.info("c status에 따른 게시물출력 result:::"+result);
        return result;
    }

/*    @PostMapping("/getAccept")
    public boolean accept(){
        boolean result = approvalService.accept();
        log.info("c approvalWrite : "+result);
        return result;
    }*/

/*    //내가 쓴 서류 상태출력
    @GetMapping("/alist")
    public List<ApprovalDto> approvalDtoList( ){
        List<ApprovalDto> result = approvalService.approvalDtoList();
        log.info("c result:::"+result);
        return result;
    }*/

/*
    //맴버랭크 빼내기 함수
    @GetMapping("/getUserRank")
    public List<ApprovalDto> getUserRank() {
      //String result = (String)request.getSession().getAttribute("login");
        List<ApprovalDto>result = approvalService.getUserRank();
        log.info("getUserRank getUserRank() result ::: " + result);
        // List<ApprovalDto> result = approvalService.approvalDtos();
        //log.info("c result:::"+result);
        return result;
    }
*/




}
