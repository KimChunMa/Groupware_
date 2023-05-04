package connect.web.service.addressbook;

import connect.web.domain.addressbook.AddressBookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressBookService {

    @Autowired AddressBookEntityRepository addressBookEntityRepository;

}
