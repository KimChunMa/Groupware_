package connect.web.service.member;

import connect.web.domain.member.PartDto;
import connect.web.domain.member.PartEntity;
import connect.web.domain.member.PartEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {

    @Autowired PartEntityRepository partEntityRepository;

    public boolean addPart( PartDto partDto ){

        PartEntity partEntity = partEntityRepository.save( partDto.toEntity() );
        if( partEntity.getPartNo() > 0 ){
            return true;
        }
        return false;
    }

    public List<PartDto> getPart() {

        List<PartEntity> partEntityList = partEntityRepository.findAll();

        List<PartDto> list = new ArrayList<>();

        partEntityList.forEach( (p) ->{
            list.add( p.toDto() );
        });

        return list;
    }


}
