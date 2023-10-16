package sky.pro.animalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.entity.Dog;
import sky.pro.animalshelter.exception.DogNotFoundException;
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
     * @return dog
     */
    @Operation(
            summary = " Поиск кота по имени",
            responses = {


                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденн кот",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation= Dog.class )
                            )

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найден"
                    )
            },
            tags = "Dog")
    @ExceptionHandler(DogNotFoundException.class)
    public ResponseEntity<?> handleDogNotFound() {
        return ResponseEntity.notFound().build();
    }
        @GetMapping("/dog/{name}")
    public Dog findByName(String name) {
        return dogService.findByName(name);
    }

    /**
     * Метод по получению собаки по id
     * @param id
     * @return dog
     */
    @Operation(
            summary = " Поиск кота по id",
            responses = {


                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден кот",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation=Dog.class )
                            )

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найден"
                    )
            },
            tags = "Dog")
    @GetMapping("/dog/{id}")
    public Dog findById(long id) {
        return dogService.findById(id);
    }

    /**
     * Метод по получению всех собак
     * @return список собак
     */
    @Operation(
            summary = " Поиск котов",
            responses = {


                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные коты",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array=@ArraySchema( schema = @Schema(implementation=Dog.class ))
                            )

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найдены"
                    )
            },
            tags = "Dog")
    @GetMapping("/dog/all")
    public List<Dog> findAll() {
        return dogService.findAll();
    }
}
