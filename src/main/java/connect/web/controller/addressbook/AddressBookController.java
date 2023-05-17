package connect.web.controller.addressbook;

import connect.web.domain.addressbook.AddressBookDto;
import connect.web.service.addressbook.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired AddressBookService addressBookService;

    @PostMapping("")
    public boolean addAddressBook( @RequestBody AddressBookDto addressBookDto ){
        return addressBookService.addAddressBook(addressBookDto);
    }

    @GetMapping("")
    public List<AddressBookDto> getAddressBook(){
        return addressBookService.getAddressBook();
    }


}
