package co.istad.bidin.ecommerceite;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class EcommerceIteApplication  {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceIteApplication.class, args);
    }

}
