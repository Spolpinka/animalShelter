package sky.pro.animalshelter.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "cat")
@EqualsAndHashCode(of = "id")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String nickname;

    private int age;

    private double weight;

    private String history;

    //для фото
    private long image;
}
