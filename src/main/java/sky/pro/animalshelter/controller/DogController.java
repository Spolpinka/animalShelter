package sky.pro.animalshelter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.animalshelter.entity.Dog;
import sky.pro.animalshelter.service.DogService;

import java.util.List;

/**
 * Контроллер для работы с собаками
 * @see Dog
 * @see DogService
 * @author Yana
 * @version 1.0
 */
@RestController
@RequestMapping("/dog")
public class DogController {
    /**
     * Сервис для работы с собаками
     */
    private final DogService dogService;

    /**
     * Конструктор - создание нового объекта
     * @param dogService
     */
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    /**
     * Метод по получению собаки по имени
     * @param name
     * @return dog
     */
    @GetMapping("/dog/{name}")
    public Dog findByName(String name) {
        return dogService.findByName(name);
    }

    /**
     * Метод по получению собаки по id
     * @param id
     * @return dog
     */
    @GetMapping("/dog/{id}")
    public Dog findById(long id) {
        return dogService.findById(id);
    }

    /**
     * Метод по получению всех собак
     * @return список собак
     */
    @GetMapping("/dog/all")
    public List<Dog> findAll() {
        return dogService.findAll();
    }
}
