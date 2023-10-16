package sky.pro.animalshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.animalshelter.entity.User;
import sky.pro.animalshelter.repository.CatRepository;
import sky.pro.animalshelter.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

    class UserServiceTest {
        @Mock
        private UserRepository userRepository;
        private CatRepository catRepository;
    @InjectMocks
        private UserService userService;
        private CatService catService;
        User test= new User(1,"Роман",1, LocalDate.now());
        public static final List<User> USERS_LIST=List.of(

               new User(),
                new User());

        @Test
        @DisplayName("Получить пользователя по имени")
        void shouldReturnByName() {

            when(userRepository.findByName(anyString()))
                    .thenReturn(test);
            assertEquals(test,userRepository.findByName(anyString()));
        }
        @Test
        @DisplayName("Получение пользователя по id")
        void shouldReturnUserWhenfindById() {
            when(userRepository.findByChatId(anyLong()))
                    .thenReturn(test);
            assertEquals(test,userService.getByChatId(anyLong()));
        }
        @Test
        @DisplayName("Получить всех пользователей")
        void shouldReturnfindAll() {
            when(userRepository.findAll())
                    .thenReturn(USERS_LIST);
            assertIterableEquals(USERS_LIST,userService.getAllUsers());
        }
    }