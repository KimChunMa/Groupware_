package connect.web.service.addressbook;

import connect.web.domain.addressbook.AddressGroupDto;
import connect.web.domain.addressbook.AddressGroupEntity;
import connect.web.domain.addressbook.AddressGroupEntityRepository;
import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.MemberEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressGroupService {

    @Autowired AddressGroupEntityRepository addressGroupEntityRepository;
    @Autowired HttpServletRequest request;
    @Autowired MemberEntityRepository memberEntityRepository;

    // 로그인 한 세션 찾기 ( 아이디 )


    // 멤버 엔티티 찾기
    public MemberEntity getMember() {

        String memberId = (String)request.getSession().getAttribute("login");

        if( memberId != null ){
            return memberEntityRepository.findByMemberName( memberId );
        }
        return null;
    }


    @Transactional
    public byte addGroup(AddressGroupDto addressGroupDto ){

        MemberEntity memberEntity = getMember();

        if( memberEntity == null){
            log.info("로그인 안됨");
            return 1;
        }

        AddressGroupEntity addressGroupEntity = addressGroupEntityRepository.save( addressGroupDto.toEntity() );

        if( addressGroupEntity.getGroupNo() > 0 ){
            // 주소록그룹 < - > 멤버 양방향 설정
            addressGroupEntity.setMemberEntity( memberEntity );
            memberEntity.getAddressGroupEntityList().add( addressGroupEntity );

            return 0;
        }

        return 2;
    }

    public List<AddressGroupDto> getGroup(){

        // 사용자가 정의한 그룹 가져오기
        List<AddressGroupEntity> addressGroupEntityList = addressGroupEntityRepository.findGroupList( getMember().getMemberNo() );

        return addressGroupEntityList.stream().map(
                o -> o.toDto()
        ).collect(Collectors.toList());
    }

}
