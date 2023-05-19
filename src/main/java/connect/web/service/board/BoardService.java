package connect.web.service.board;

import connect.web.domain.board.*;
import connect.web.domain.member.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    // 5. [김동혁] 개별 게시물 출력
    @Transactional
    public BoardDto getboard(int boardNo){
        String ip = request.getRemoteAddr();
        Object o = request.getSession().getAttribute(ip+boardNo);
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(boardNo);
        if(o==null&& optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDto boardDto = boardEntity.toDto();
            request.getSession().setAttribute(ip+boardNo,1);
            request.getSession().setMaxInactiveInterval(60*60*24);
            boardEntity.setBoardView(boardEntity.getBoardView()+1);
            return boardDto;
        } else if (o!=null && optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDto boardDto = boardEntity.toDto();
            return boardDto;
        }
        return null;
    }

    // 6. [김동혁] 개별 게시물 삭제
    public boolean delete(int boardNo){
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(boardNo);
        if(optionalBoardEntity.isPresent()){
            boardEntityRepository.delete(optionalBoardEntity.get());
            return true;
        }
        return false;
    }

    // 7. [김동혁] 개별 게시물 수정
    @Transactional
    public boolean update(BoardDto boardDto){ // 1. boarddto 인수로 받아와서
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(boardDto.getBoardNo());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardEntity.setBoardTitle(boardDto.getBoardTitle());
            boardEntity.setBoardContent(boardDto.getBoardContent());
            return true;
        }
        return false;
    }

    // 8. [김동혁] 댓글작성
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    @Transactional
    public boolean postReply(@RequestBody ReplyDto replyDto){ log.info("postReply : " + replyDto);
        // 로그인 확인
        String o = (String) request.getSession().getAttribute("login");
        MemberEntity memberEntity = memberEntityRepository.findByMemberId(o).get();
        // 댓글 달 게시물 호출
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(replyDto.getBoardNo());
        if(!optionalBoardEntity.isPresent()){ return false;}
        BoardEntity boardEntity = optionalBoardEntity.get();
        // 댓글 작성
        ReplyEntity replyEntity = replyEntityRepository.save(replyDto.toEntity());
        if (replyEntity.getReplyNo()<1){return false;}
        // 댓글과 회원의 양방향관계
        replyEntity.setMemberEntity(memberEntity);
        memberEntity.getReplyEntityList().add(replyEntity);
        // 댓글과 게시물의 양방향관계
        replyEntity.setBoardEntity(optionalBoardEntity.get());
        boardEntity.getReplyEntityList().add(replyEntity);
        return true;
    }
    @Transactional
    public boolean getReply(){ // 게시물 출력 시 대체
        return true;
    }
}

