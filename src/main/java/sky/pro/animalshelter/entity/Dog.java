package sky.pro.animalshelter.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "dog")
@EqualsAndHashCode(of = "id")
public class Dog {
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

    public Dog(int i, String archy, int i1, int i2, String s, int i3) {
    }
}
