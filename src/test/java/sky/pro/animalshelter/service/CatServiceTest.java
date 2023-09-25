package sky.pro.animalshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.repository.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class CatServiceTest {
    @Mock
    private CatRepository catRepository;
    private CatService catService;
    Cat Lev = new Cat(1, "Лев", 5, 4, "Нашли в возрасте 6 месяцев", 1);
public static final List<Cat> CAT_LIST=List.of(

     new Cat(2, "Вася", 2, 3, "Отдали бывшие хозяева", 2),
new Cat(3,"Том",4,5,   "Нашли в лесу",3));

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