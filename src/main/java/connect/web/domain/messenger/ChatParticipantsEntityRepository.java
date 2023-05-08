package connect.web.domain.messenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatParticipantsEntityRepository extends JpaRepository<ChatParticipantsEntity, Integer> {
    List<ChatParticipantsEntity> findByMemberNo(int memberNo);
}
