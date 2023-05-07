package connect.web.controller.member;

import connect.web.domain.member.LoginService;
import connect.web.domain.member.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginCotroller {

    @Autowired LoginService loginService;

    @PostMapping("/login")
    public boolean login( @ModelAttribute MemberDto memberDto ) {
        return loginService.login( memberDto );
    }

}
