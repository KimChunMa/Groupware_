package connect.web.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartEntityRepository extends JpaRepository<PartEntity , Integer> {
}
