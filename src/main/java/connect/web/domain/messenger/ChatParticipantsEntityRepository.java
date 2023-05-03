package connect.web.domain.messenger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatParticipantsEntityRepository extends JpaRepository<ChatParticipantsEntity, Integer> {
}
