package sky.pro.animalshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.mockito.Mockito.when;



    @ExtendWith(MockitoExtension.class)

    class UserServiceTest {
        @Mock
        private UserRepository userRepository;
        private UserService userService;
        User test= new User(1,"Роман",1, LocalDate.now());
        public static final List<User> USERS_LIST=List.of(

                new User[](2, "Вася", 2, LocalDate.),
                new User(3,"Том",4,5,   "Нашли в лесу",3));

        @Test
        @DisplayName("Получить кота по клички")
        void shouldReturnByName() {

            when(catRepository.findByName(anyLong()))
                    .thenReturn(Optional.ofNullable(Lev));
            assertEquals(Lev,CatService.findByName(anyLong()));
        }

        @Test
        @DisplayName("Получение кота по id")
        void shouldReturnCatWhenfindById() {

            when(catRepository.findByName(anyLong()))
                    .thenReturn(Optional.ofNullable(Lev));
            assertEquals(Lev,catService.findByName(anyLong()));
        }


        @Test
        @DisplayName("Получить всех котов")
        void shouldReturnfindAll() {
            when(CatRepository.findAll())
                    .thenReturn(CAT_LIST);
            assertIterableEquals(CAT_LIST,catService.findAll());
        }
    }