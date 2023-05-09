package connect.web.domain.messenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomsEntityRepository extends JpaRepository<ChatRoomsEntity,Integer> {
    @Query(value="select * from chat_rooms where chat_room_id = :chatRoomId",nativeQuery = true)
    ChatRoomsEntity findByChatRoomId(int chatRoomId);
}
