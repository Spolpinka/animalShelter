package sky.pro.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.animalshelter.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByNotificationDateTime(LocalDateTime localDateTime);
    List<User> findAllByNotificationDateTimeAndChatId(LocalDateTime localDateTime,long chatId);

}

