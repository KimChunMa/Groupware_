package connect.web.service.addressbook;

import connect.web.domain.addressbook.AddressGroupEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressGroupService {

    @Autowired AddressGroupEntityRepository addressGroupEntityRepository;

}
