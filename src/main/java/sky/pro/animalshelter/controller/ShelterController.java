package sky.pro.animalshelter.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    @GetMapping("/{name}")
    public Shelter getShelter(@PathVariable("name") String name) {

    }
}

}
