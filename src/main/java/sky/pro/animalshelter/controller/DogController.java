package sky.pro.animalshelter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.animalshelter.entity.Dog;
import sky.pro.animalshelter.service.DogService;

import java.util.List;

@RestController
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dog/{name}")
    public Dog findByName(String name) {
        return dogService.findByName(name);
    }

    @GetMapping("/dog/{id}")
    public Dog findById(long id) {
        return dogService.findById(id);
    }

    @GetMapping("/dog/all")
    public List<Dog> findAll() {
        return dogService.findAll();
    }
}
