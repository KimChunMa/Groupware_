package connect.web.controller.member;

import connect.web.service.member.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartController {

    @Autowired PartService partService;

}
