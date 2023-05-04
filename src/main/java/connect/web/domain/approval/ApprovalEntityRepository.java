package connect.web.domain.approval;

import connect.web.domain.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalEntityRepository  extends JpaRepository<ApprovalEntity, Integer > {

}
