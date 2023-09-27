package sky.pro.animalshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.entity.Dog;
import sky.pro.animalshelter.repository.CatRepository;
import sky.pro.animalshelter.repository.DogRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class DogServiceTest {
    @Mock
    private DogRepository dogRepository;
    private DogService dogService;
    Dog Archy = new Dog(1, "Archy", 5,12, "Нашли в возрасте 6 месяцев", 1);
    public static final List<Dog> DOGS_LIST=List.of(

            new Dog(2, "Тим", 2, 11, "Отдали бывшие хозяева", 2),
            new Dog(3,"Бобик",4,15,   "Нашли в лесу",3));

    @Test
    @DisplayName("Получить собаку по клички")
    void shouldReturnByName() {

        when(dogRepository.findByName(anyLong()))
                .thenReturn(Optional.ofNullable(Archy));
        assertEquals(Archy,DogService.findByName(anyLong()));
    }

    @Test
    @DisplayName("Получение собаку по id")
    void shouldReturnCatWhenfindById() {

        when(dogRepository.findByName(anyLong()))
                .thenReturn(Optional.ofNullable(Archy));
        assertEquals(Archy,dogService.findByName(anyLong()));
    }


    @Test
    @DisplayName("Получить всех собак")
    void shouldReturnfindAll() {
        when(dogRepository.findAll())
                .thenReturn(DOGS_LIST);
        assertIterableEquals(DOGS_LIST,dogService.findAll());
    }
}