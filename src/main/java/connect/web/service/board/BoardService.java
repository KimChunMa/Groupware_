package connect.web.service.board;

import connect.web.domain.board.BoardDto;
import connect.web.domain.board.BoardEntity;
import connect.web.domain.board.BoardEntityRepository;
import connect.web.domain.board.PageDto;
import connect.web.domain.member.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private PartEntityRepository partEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;

    // 1. [김동혁] 부서 등록
    public boolean partWrite(@RequestBody BoardDto boardDto) {
        // 1. 입력받은 partName을 DTO에서 entity로 바꾸고 save
        log.info("Part boardDto : " + boardDto);
        PartEntity entity = partEntityRepository.save(boardDto.toPartEntity());
        // 2. 만일 생성된 엔티티의 pk값이 1보다 크면 성공
        if (entity.getPartNo() >= 1) {
            return true;
        }
        return false;
    }

    // 2. [김동혁] 게시물 쓰기를 위한 부서 출력
    public List<PartDto> partList() {
        List<PartEntity> partEntityList = partEntityRepository.findAll();
        List<PartDto> list = new ArrayList<>();
        partEntityList.forEach((e) -> {
            list.add(new PartDto(e.getPartNo(), e.getPartName()));
        });
        return list;
    }
    // 3. [김동혁] 게시글 쓰기
    @Autowired
    private HttpServletRequest request;
    @Transactional
    public byte write(BoardDto boardDto){log.info("service write boardDto : " + boardDto);
        // 1. 부서 엔티티 찾기
        Optional<PartEntity> partEntityOptional = partEntityRepository.findById(boardDto.getPartNo());
        if (!partEntityOptional.isPresent()){return 1;}
        PartEntity partEntity = partEntityOptional.get();
        // 2. 로그인 세션 가져와서 로그인 확인하기 (애초에 로그인 해야 페이지로 넘어가지긴 하지만 만약을 위해 )
        String o = (String) request.getSession().getAttribute("login");
        MemberEntity memberEntity = memberEntityRepository.findByMemberId(o).get();
        // 3. 게시물 쓰기
        BoardEntity boardEntity = boardEntityRepository.save(boardDto.toBoardEntity());
        if(boardEntity.getBoardNo()<1){return 2;}
        // 4. 양방향 관계
        partEntity.getBoardEntityList().add(boardEntity);
        boardEntity.setPartEntity(partEntity);
        // 5. 양방향 관계
        boardEntity.setMemberEntity(memberEntity);
        memberEntity.getBoardEntityList().add(boardEntity);

        return 3;
    }

    // 4. [김동혁] 게시물 출력
    @Transactional
    public PageDto getList(PageDto pageDto){
        Pageable pageable = PageRequest.of(pageDto.getPage()-1, 5 , Sort.by(Sort.Direction.DESC , "board_no"));
        // 현재 페이지번호[0시작] , 페이지당 표시할 게시물 수 (5개) , 게시물 번호 순으로 내림차순
        Page<BoardEntity> entityPage =
                boardEntityRepository.findBySearch(pageDto.getPartNo() , pageDto.getKey() , pageDto.getKeyword() , pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();
        entityPage.forEach((b)->{boardDtoList.add(b.toDto());});
        pageDto.setBoardDtoList(boardDtoList);
        pageDto.setTotalPage(entityPage.getTotalPages());
        return pageDto;
    }
}

