package sky.pro.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.animalshelter.entity.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findByName(String name);

    Dog findById(long id);
}
