package connect.web.domain.addressbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressGroupEntityRepository extends JpaRepository<AddressGroupEntity , Integer> {
}
