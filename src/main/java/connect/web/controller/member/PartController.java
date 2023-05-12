package connect.web.controller.member;

import connect.web.domain.member.PartDto;
import connect.web.service.member.PartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired PartService partService;


    @PostMapping("")
    public boolean addPart(@RequestBody PartDto partDto ){
        return partService.addPart( partDto );
    }

    @GetMapping("")
    public List<PartDto> getPart() {
        return partService.getPart();
    }



}
