package connect.web.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity , Integer > {

    // 멤버아이디로 엔티티 찾기 ( 반환타입 : 옵셔널 )
    Optional<MemberEntity> findByMemberId( String memberId );

}
