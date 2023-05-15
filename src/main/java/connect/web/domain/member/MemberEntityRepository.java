package connect.web.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity , Integer > {

    // 멤버아이디로 엔티티 찾기 ( 반환타입 : 옵셔널 )
    Optional<MemberEntity> findByMemberId( String memberId );


    // 멤버 아이디로 회원번호(PK) 찾기
    public int findByMemberNo(String memberId);

    //멤버이름으로 아이디 찾기
    public MemberEntity findByMemberName(String memberName);
}
