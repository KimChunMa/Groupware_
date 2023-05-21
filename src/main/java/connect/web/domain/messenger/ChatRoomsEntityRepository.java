package connect.web.domain.messenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomsEntityRepository extends JpaRepository<ChatRoomsEntity,Integer> {
    @Query(value="select r.chat_room_id , r.uuid_file , r.name , r.cdate , m.content " +
                 " from Chat_Rooms r , Chat_Messages m where r.chat_room_id= :chatRoomId  " +
                 " order by m.cdate desc limit 1; ",nativeQuery = true)
    ChatRoomsEntity findByChatRoomId(int chatRoomId);
}
