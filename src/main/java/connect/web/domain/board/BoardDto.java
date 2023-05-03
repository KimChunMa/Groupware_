package connect.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BoardDto {
    private int board_no;
    private String board_title;
    private String board_content;
    private String board_date;
    private int board_view;

    private int member_no;
    private int part_no;
}
