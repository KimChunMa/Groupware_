package connect.web.service.approval;

import connect.web.domain.approval.ApprovalDto;
import connect.web.domain.approval.ApprovalEntity;
import connect.web.domain.approval.ApprovalEntityRepository;
import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.MemberEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import static connect.web.domain.approval.ApprovalEntityRepository.*;

@Slf4j
@Service
public class ApprovalService {

    //서류작성 ApprovalEntityRepository
    @Autowired
    private ApprovalEntityRepository approvalEntityRepository;

    //서류작성 MemberEntityRepository
    @Autowired
    private MemberEntityRepository memberEntityRepository;

    @Autowired
    HttpServletRequest request;

    //서류 등록
    @Transactional
    public boolean approvalWrite( ApprovalDto approvalDto){


        String memberId =(String)request.getSession().getAttribute("login");
            log.info("로그인세션확인");
            log.info(memberId);

        Optional<MemberEntity> memberEntityOptional = memberEntityRepository.findByMemberId( memberId );

        if(memberEntityOptional.isPresent()){
            MemberEntity memberEntity = memberEntityOptional.get();
            log.info(memberEntity+"");

            ApprovalEntity approvalEntity = approvalDto.toApprovalEntity();

            approvalEntity.setMemberEntity(memberEntity);

            ApprovalEntity approvalEntity2 = approvalEntityRepository.save(approvalEntity);

            if(approvalEntity2.getApprovalNo() >=1 ){
                return true;
            }


        }
        return false;
    }


    //서류 상태출력
    @Transactional
    public List<ApprovalDto> approvalDtoList( int approvalNo ){
        log.info("c approvalDtoList : " );



        List<ApprovalDto> list = new ArrayList<>();
/*        ApprovalDtoList.forEach((e) -> {
            list.add(new ApprovalDto(
                    e.getApproval_no(),
                    e.getApproval_writer(),
                    e.getApproval_title(),
                    e.getApproval_content(),
                    e.getApproval_status(),
                    e.getApproval_data()
            ));
        });*/
                return list;

    } //출력 e

}//class e
