package connect.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class ReplyDto {
    private int reply_no;
    private String reply_content;
    private String reply_date;
    private int board_no;

    private int member_no;
    private String member_name;

}
