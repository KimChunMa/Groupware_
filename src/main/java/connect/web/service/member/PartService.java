package connect.web.service.member;

import connect.web.domain.member.PartEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService {

    @Autowired PartEntityRepository partEntityRepository;

}
