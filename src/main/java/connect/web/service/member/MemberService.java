package connect.web.service.member;

import connect.web.domain.member.MemberDto;
import connect.web.domain.member.MemberEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MemberService {

    @Autowired MemberEntityRepository memberEntityRepository;

    // 1. 회원 등록하기
    public boolean add( MemberDto memberDto ){

        log.info("add service : " + memberDto );

        return false;
    }

}
