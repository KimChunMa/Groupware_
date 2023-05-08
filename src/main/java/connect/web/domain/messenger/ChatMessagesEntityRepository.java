package connect.web.domain.messenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessagesEntityRepository extends JpaRepository<ChatMessagesEntity, Integer> {
    List<ChatMessagesEntity> findAllByChatRoomId(int chatRoomId);
}
