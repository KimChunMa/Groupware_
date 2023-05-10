package connect.web.domain.approval;

import connect.web.domain.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalEntityRepository  extends JpaRepository<ApprovalEntity, Integer > {

    //
    @Query( value = "select * from approval where member_no = :pk" ,nativeQuery = true )
    List<ApprovalEntity> findByMember( @Param("pk") int pk);

/*    @Query*/


}
