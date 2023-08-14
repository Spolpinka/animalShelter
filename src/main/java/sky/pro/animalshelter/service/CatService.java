package sky.pro.animalshelter.service;

import org.springframework.stereotype.Service;
import sky.pro.animalshelter.entity.Cat;
import sky.pro.animalshelter.repository.CatRepository;

import java.util.List;

@Service
public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Cat findByName(String name) {
        return catRepository.findByName(name);
    }

    public Cat findById(long id) {
        return catRepository.findById(id);
    }

    public List<Cat> findAll() {
        return catRepository.findAll();
    }
}
