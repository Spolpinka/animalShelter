package sky.pro.animalshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.entity.User;
import sky.pro.animalshelter.repository.CatRepository;
import sky.pro.animalshelter.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static sky.pro.animalshelter.service.CatServiceTest.CAT_LIST;


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
        @DisplayName("Получить кота по клички")
        void shouldReturnByName() {

            when(catRepository.findByName(anyString()))
                    .thenReturn(Optional.ofNullable());
            assertEquals(CatService.findByName(anyString()));
        }

        @Test
        @DisplayName("Получение кота по id")
        void shouldReturnCatWhenfindById() { }
        @Test
        @DisplayName("Получить всех котов")
        void shouldReturnfindAll() {
            when(CatRepository.findAll())
                    .thenReturn(CAT_LIST);
            assertIterableEquals(CAT_LIST,catService.findAll());
        }
    }