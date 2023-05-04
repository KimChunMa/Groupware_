package connect.web.controller.board;

import connect.web.domain.board.BoardDto;
import connect.web.domain.board.BoardEntity;
import connect.web.domain.member.PartDto;
import connect.web.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 2. 부서 출력
    @GetMapping("/part/list")
    public List<PartDto> partList(){
        List<PartDto> result = boardService.partList();
        return result;
    }
}
