package sky.pro.animalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.entity.User;
import sky.pro.animalshelter.service.UserService;

import java.util.List;

/**
 * Контроллер для работы с пользователями
 * @see User
 * @see UserService
 * @author Yana
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Сервис для работы с пользователями
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод по получению пользователя по имени
     * @param name имя пользователя
     * @return user
     */
    @Operation(
            summary = " Поиск пользователя",
            responses = {


                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденны пользователи",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation= User.class )
                            )

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найдены"
                    )
            },
            tags = "User")
    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    /**
     * Метод по получению всех пользователей
     * @return список пользователей
     */
    @Operation(
            summary = " Поиск пользователей",
            responses = {


                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденны пользователи",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array=@ArraySchema( schema = @Schema(implementation= User.class ))
                            )

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найдены"
                    )
            },
            tags = "User")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
