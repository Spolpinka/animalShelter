package sky.pro.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.animalshelter.entity.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    Cat findByName(String name);

    Cat findById(long id);
}
