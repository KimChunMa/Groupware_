package connect.web.controller.addressbook;

import connect.web.service.addressbook.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressBookController {

    @Autowired AddressBookService addressBookService;

}
