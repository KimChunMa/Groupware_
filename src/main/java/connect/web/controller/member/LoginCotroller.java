package connect.web.controller.member;

import connect.web.service.member.LoginService;
import connect.web.domain.member.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class LoginCotroller {

    @Autowired LoginService loginService;


    @PostMapping("/login")
    public boolean login(@ModelAttribute MemberDto memberDto , HttpServletRequest request ) {
        log.info("login controller : " + memberDto );

        boolean result = loginService.login( memberDto );

        if( result ){
            request.getSession().setAttribute("login" , memberDto.getMemberId() );
            return true;
        }
        return false;
    }

    @GetMapping("/login")
    public MemberDto loginInfo() {
        return loginService.loginInfo();
    }

}
