package sky.pro.animalshelter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Серверная часть приюта для животных \"Animal Shelter\"",
                version = "0.0.3",
                description = "Приют для животных из приюта",
                contact = @Contact(
                        name = "Александр, Татьяна, Яна",
                        email = "a421243266@gmail.com",
                        url = "https://github.com/Spolpinka/animalShelter"
                )
        )
)
@SpringBootApplication
public class AnimalShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalShelterApplication.class, args);
    }

}
