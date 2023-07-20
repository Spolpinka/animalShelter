package sky.pro.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.animalshelter.entity.NotificationTask;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
}
