package sky.pro.animalshelter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="user")
public class User {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="name",nullable = false)
    private String name;

    @Column(name="chat_id",nullable = false)
    private long chatId;

    @Column(name="time_of_registration",nullable = false)
    private LocalDateTime timeOfRegistration;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
    public void setTimeOfRegistration(LocalDateTime timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getChatId() {
        return chatId;
    }

    public LocalDateTime getTimeOfRegistration() {
        return timeOfRegistration;
    }
}




