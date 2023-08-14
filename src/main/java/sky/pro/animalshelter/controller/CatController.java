package sky.pro.animalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.exception.CatNotFoundException;
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
    @Operation(
            summary = " Поиск кота по имени",
            responses = {


            @ApiResponse(
                    responseCode = "200",
                    description = "Найденн кот",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation=Cat.class )
                    )

            ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найден"
                    )
    },
    tags = "Cats")
    @ExceptionHandler(CatNotFoundException.class)
    public ResponseEntity<?> handleCatNotFound(){
        return  ResponseEntity.notFound().build();

    }

    @GetMapping("/cat/{name}")
    public Cat findByName(String name) {
        return catService.findByName(name);
    }

    /**
     * Метод по получению кота по id
     * @param id
     * @return кот
     */
    @Operation(
            summary = " Поиск кота по id",
            responses = {


                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден кот",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation=Cat.class ))


                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найден"
                    )
            },
            tags = "Cats")
    @GetMapping("/cat/{id}")
    public Cat findById(long id) {
        return catService.findById(id);
    }

    /**
     * Метод по получению всех котов
     * @return список котов
     */
    @Operation(
            summary = " Поиск котов",
            responses = {


                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные коты",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array=@ArraySchema( schema = @Schema(implementation=Cat.class ))
                            )

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найдены"
                    )
            },
            tags = "Cats")
    @GetMapping("/cat/all")
    public List<Cat> findAll() {
        return catService.findAll();
    }
}
