package sky.pro.animalshelter.service;

import org.springframework.stereotype.Service;
import sky.pro.animalshelter.entity.Dog;
import sky.pro.animalshelter.repository.DogRepository;

import java.util.List;

@Service
public class DogService {
    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog findByName(String name) {
        return dogRepository.findByName(name);
    }

    public Dog findById(long id) {
        return dogRepository.findById(id);
    }

    public List<Dog> findAll() {
        return dogRepository.findAll();
    }
}
