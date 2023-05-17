package connect.web.service.addressbook;

import connect.web.domain.addressbook.AddressGroupDto;
import connect.web.domain.addressbook.AddressGroupEntityRepository;
import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AddressGroupService {

    @Autowired AddressGroupEntityRepository addressGroupEntityRepository;
    @Autowired HttpServletRequest request;
    @Autowired MemberEntityRepository memberEntityRepository;

    // 로그인 한 세션 찾기 ( 아이디 )
    String memberId = (String)request.getSession().getAttribute("login");

    // 멤버 엔티티 찾기
    public MemberEntity getMember() {
        return memberEntityRepository.findByMemberName(memberId);
    }


    public boolean addGroup(AddressGroupDto addressGroupDto ){



        return false;
    }

    public List<AddressGroupDto> getGroup(){
        return null;
    }

}
