package sky.pro.animalshelter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dog_adopter")
public class DogAdopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "dog_id", nullable = false)
    private long dogId;

    @Column(name = "is_adopted", nullable = false)
    private boolean isAdopted;

    //boolean на наличие отчетов до 2 дней включительно
    @Column(name = "is_reported_2_days", nullable = false)
    private boolean isReported2Days;

    //длительность испытательного периода, изначально 30 дней, может быть продлен на 14 или 30 дней
    @Column(name = "trial_period", nullable = false)
    private int trialPeriod;

}
