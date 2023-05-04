package connect.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class ReplyDto {
    private int replyNo;
    private String replyContent;
    private String replyDate;
    private int boardNo;

    private int memberNo;
    private String memberName;

}
