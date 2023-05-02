package connect.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
    private String bdate;
    private int bview;

    private int mno;
    private int part_no;
}
