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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
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

    public MemberEntity getMember(){
        String memberId =(String)request.getSession().getAttribute("login");
        log.info("로그인세션확인");
        log.info(memberId);

        Optional<MemberEntity> memberEntityOptional = memberEntityRepository.findByMemberId( memberId );

        if(memberEntityOptional.isPresent()) {
            MemberEntity memberEntity = memberEntityOptional.get();
            return memberEntity;
        }
        return null;
    }

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
            log.info("엔티티화되었는지");
            log.info(approvalEntity+"");

            approvalEntity.setMemberEntity(memberEntity);
            log.info("memberentity도 approvalentity에 저장되었는지");
            log.info(approvalEntity+"");
            log.info(approvalEntity.getMemberEntity().getMemberId()+"");


            ApprovalEntity approvalEntity2 = approvalEntityRepository.save(approvalEntity);
            log.info("memberentity2도 approvalentity에 저장되었는지");
            log.info(approvalEntity2+"");

            if(approvalEntity2.getApprovalNo() >=1 ){
                return true;
            }

        }
        return false;
    }


/*    @Transactional
    public boolean accept(){

        boolean result = approvalEntityRepository.update(approvalNo);
        return result;

    }*/

    //서류 상태출력
    //STATUS 상태에따른 서류
    @Transactional
    public List<ApprovalDto> approvalDtoList( ){

        List<ApprovalDto>list = new ArrayList<>();

        int memberRank = getMember().getMemberRank(); //세션의 회원의 랭크 찾았음

        String status = "0";

        if(memberRank == 3){ //대리일경우 [사원의 서류 열람 가능] 즉 approval_status값이 0일경우에만 보이는 것임
            status = "0" ;

        }else if( memberRank == 4){ //과장일경우 [대리가 승인한 경우의 서류 열람가능]
            status = "1" ;

        }else if( memberRank == 6){ //팀장일경우 [과장이 승인한 경우의 서류 열람가능]
            status= "2";

        }else if( memberRank == 9){ //서류반려했을겨우
            status="9";

        }

        List<ApprovalEntity> approvalEntityList = approvalEntityRepository.findByWatch( status );
        //String statuss = (approvalEntityRepository.findByStatus(5)); // 멤버 pk를이용한  approval_status 찾음

        approvalEntityList.forEach((o)->{
            list.add(o.approvalDto());
        });
        return list;
    }




/*
    //맴버랭크 빼내기 함수 + 성봉이랑 같이 함
    @Transactional
    public List<ApprovalDto> getUserRank() {
        char result= getMember().getMemberRank();

        log.info("s result "+result);
       //String result = approvalEntityRepository.getUserRank();
        log.info("getUserRank getUserRank() result ::: " + result);

        List<ApprovalDto> result = approvalService.approvalDtos();
        log.info("c result:::"+result);
        return result;
        return null;
    }
*/


/*
    //멤버랭크 꺼내기
    @Transactional
    public List<ApprovalDto> approvalDtos(){

        List<ApprovalDto>List = new ArrayList<>();

        List<ApprovalEntity> approvalEntities = approvalEntityRepository.findByRank(getMember().getMemberRank(), getMember().getPartEntity().getPartNo() ); // 랭크 빼는 함수 확인

        log.info("s approvalEntities"+approvalEntities);

        approvalEntities.forEach((o)->{
            List.add(o.approvalDto());
        });

        log.info(List+"");
        return List;
    }
*/






    //전체출력함수
/*    @Transactional
    public List<ApprovalDto>totalApproval(){

        List<ApprovalDto>Alllist = new ArrayList<>();

        List<ApprovalEntity> approvalAllList = approvalEntityRepository.findAll();

        log.info("s approvalAllList 모든 게시물출력"+approvalAllList);

        approvalAllList.forEach((o)->{
            Alllist.add(o.approvalDto());
        });
        return Alllist;
    }*/


}//class e
