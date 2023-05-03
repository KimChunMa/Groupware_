package connect.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardEntity {
}
