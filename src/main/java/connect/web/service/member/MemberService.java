package connect.web.service.member;

import connect.web.domain.member.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class MemberService {

    @Autowired MemberEntityRepository memberEntityRepository;
    @Autowired PartEntityRepository partEntityRepository;

    // 1. 회원 등록하기
    public boolean add( MemberDto memberDto ){
        
        // 1. 입력된 partNo 로 엔티티 찾기
        Optional<PartEntity> optionalPartEntity = partEntityRepository.findById( memberDto.getPartNo() );

        if( optionalPartEntity.isPresent() ){ // 만약에 입력받은 부서값이 존재하면
            // 전달받은 DTO 를 엔티티로 변환 후 저장
            MemberEntity memberEntity = memberEntityRepository.save( memberDto.toEntity() );
            if( memberEntity.getMemberNo() > 0 ){ // 만약 저장된 getMemberNo 값이 0 이상일경우 -> 저장성공

                // * member <-> part 양방향 저장
                memberEntity.setPartEntity( optionalPartEntity.get() );
                optionalPartEntity.get().getMemberEntityList().add( memberEntity );

                return true;
            }
        }


        // 2. 데이터 저장 후 controller return
        return false;
    }

}
