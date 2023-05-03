package connect.web.domain.messenger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessagesEntityRepository extends JpaRepository<ChatMessagesEntity, Integer> {
}
