package sky.pro.animalshelter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
     * @param name
     * @return user
     */
    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    /**
     * Метод по получению всех пользователей
     * @return список пользователей
     */

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
