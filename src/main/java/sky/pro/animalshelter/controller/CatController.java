package sky.pro.animalshelter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.service.CatService;

import java.util.List;

@RestController
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/cat/{name}")
    public Cat findByName(String name) {
        return catService.findByName(name);
    }

    @GetMapping("/cat/{id}")
    public Cat findById(long id) {
        return catService.findById(id);
    }

    @GetMapping("/cat/all")
    public List<Cat> findAll() {
        return catService.findAll();
    }
}
