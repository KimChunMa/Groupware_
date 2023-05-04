package connect.web.controller.member;

import connect.web.domain.member.MemberDto;
import connect.web.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired MemberService memberService;

    // 1. 회원 등록하기
    @PostMapping("/add")
    public boolean add( @ModelAttribute MemberDto memberDto ) {
        log.info("add controller : " + memberDto );
        return memberService.add(memberDto);
    }
}
