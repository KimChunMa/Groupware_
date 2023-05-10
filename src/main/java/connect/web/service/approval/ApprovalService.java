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


    //서류 상태출력
    //내가 쓴 서류 상태출력
    @Transactional
    public List<ApprovalDto> approvalDtoList( ){

        List<ApprovalDto>list = new ArrayList<>();

        log.info("확인");
        log.info(getMember().getMemberNo()+"ㅇㅀㅇㅀㅇㅀ");

        List<ApprovalEntity> approvalEntityList = approvalEntityRepository.findByMember( getMember().getMemberNo() );

        log.info("s approvalEntityList "+approvalEntityList+"");

        approvalEntityList.forEach((o)->{
            list.add(o.approvalDto());
        });

        log.info("s list "+list+"");

        return list;
    }
/*
    //멤버랭크 꺼내기
    @Transactional
    public List<ApprovalDto>approvalDtos(){
        List<ApprovalDto>List = new ArrayList<>();

        List<ApprovalEntity> approvalEntities = approvalEntityRepository.findByRank(getMember().getMemberNo()); // 랭크 빼는 함수 확인

        log.info("s approvalEntities"+approvalEntities);

        approvalEntities.forEach((o)->{
            List.add(o.approvalDto());
        });

        log.info(List+"");
        return null;
    }*/

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
