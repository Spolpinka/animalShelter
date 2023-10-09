package sky.pro.animalshelter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@EqualsAndHashCode(of = "chatId")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "time_of_registration", nullable = false)
    private LocalDateTime timeOfRegistration;

    //поле для определения, к какому приюту относится, dog or cat
    @Column(name = "is_dog")
    private boolean isDog;
    private boolean dog;

    public User(int id, String name, int chatId, LocalDate now) {
    }

    public User() {

    }


    @Override
    public String toString() {
        return "User{" +
                "id = " + id +
                ", chatId = " + chatId +
                ", name = " + name +
                ", timeOfRegistration =" +
                timeOfRegistration.getDayOfMonth() + "-" +
                timeOfRegistration.getMonthValue() + "-" +
                timeOfRegistration.getYear() + " " +
                timeOfRegistration.getHour() + ":" +
                timeOfRegistration.getMinute() + ":" +
                timeOfRegistration.getSecond() +
                ", приют для " + (isDog ? "собак" : "кошек");
    }

    public boolean isDog() {
        return dog;
    }

    public void setDog(boolean dog) {
        this.dog = dog;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeOfRegistration(LocalDateTime now) {
    }
}
