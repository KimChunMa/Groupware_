package connect.web.service.addressbook;

import connect.web.domain.addressbook.AddressBookDto;
import connect.web.domain.addressbook.AddressBookEntityRepository;
import connect.web.domain.addressbook.AddressGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    @Autowired AddressBookEntityRepository addressBookEntityRepository;

    public boolean addAddressBook(AddressBookDto addressBookDto ){
        return false;
    }

    public List<AddressBookDto> getAddressBook(){
        return null;
    }

}
