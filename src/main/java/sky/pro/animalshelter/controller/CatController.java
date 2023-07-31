package sky.pro.animalshelter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.service.CatService;

import java.util.List;

/**
 * Контроллер для работы с котами
 * @see Cat
 * @see CatService
 * @see RestController
 * @author Yana
 * @version 1.0
 */
@RestController
@RequestMapping("/cat")
public class CatController {
    /**
     * Сервис для работы с котами
     */
    private final CatService catService;

    /**
     * Конструктор - создание нового объекта
     * @param catService
     */
    public CatController(CatService catService) {
        this.catService = catService;
    }

    /**
     * Метод по получению кота по имени
     * @param name
     * @return кот
     */
    @GetMapping("/cat/{name}")
    public Cat findByName(String name) {
        return catService.findByName(name);
    }

    /**
     * Метод по получению кота по id
     * @param id
     * @return кот
     */
    @GetMapping("/cat/{id}")
    public Cat findById(long id) {
        return catService.findById(id);
    }

    /**
     * Метод по получению всех котов
     * @return список котов
     */
    @GetMapping("/cat/all")
    public List<Cat> findAll() {
        return catService.findAll();
    }
}
