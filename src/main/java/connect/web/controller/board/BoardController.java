package connect.web.controller.board;

import connect.web.domain.board.BoardDto;
import connect.web.domain.board.BoardEntity;
import connect.web.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/board")
public class BoardController {
    @Autowired private BoardService boardService;

    // 1. 부서(Part등록)
    @PostMapping("/part/write")
    public boolean partWrite(@RequestBody BoardDto boardDto){
        log.info("Part boardDto : " + boardDto);
        boolean result = boardService.partWrite(boardDto);
        return result;
    }
}
