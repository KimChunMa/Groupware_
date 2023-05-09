package connect.web.service.board;

import connect.web.domain.board.BoardDto;
import connect.web.domain.board.BoardEntity;
import connect.web.domain.board.BoardEntityRepository;
import connect.web.domain.member.MemberEntityRepository;
import connect.web.domain.member.PartDto;
import connect.web.domain.member.PartEntity;
import connect.web.domain.member.PartEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private PartEntityRepository partEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;

    // 1. [김동혁] 부서 등록
    public boolean partWrite(@RequestBody BoardDto boardDto){
        // 1. 입력받은 partName을 DTO에서 entity로 바꾸고 save
        log.info("Part boardDto : " + boardDto);
        PartEntity entity = partEntityRepository.save(boardDto.toPartEntity());
        // 2. 만일 생성된 엔티티의 pk값이 1보다 크면 성공
        if(entity.getPartNo()>=1){return true;}
        return false;
    }
    
    // 2. [김동혁] 게시물 쓰기를 위한 부서 출력
    public List<PartDto> partList(){
        List<PartEntity> partEntityList = partEntityRepository.findAll();
        List<PartDto> list = new ArrayList<>();
        partEntityList.forEach((e)->{
            list.add(new PartDto(e.getPartNo() , e.getPartName()));
        }); return list;
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
        // 2.
        Object o = (Object)request.getSession().getAttribute("login");
        // int memberNo = memberEntityRepository.findByMemberId()
    }
