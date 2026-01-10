package test.com.stockify;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StockifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockifyApplication.class, args);
    }

    @Bean
    public CommandLineRunner debugEntities(EntityManagerFactory emf) {
        return args -> {
            System.out.println("\n========== ENTIDADES JPA DETECTADAS ==========");
            emf.getMetamodel().getEntities().forEach(entity -> {
                System.out.println("✓ Entity: " + entity.getName());
                System.out.println("  Class: " + entity.getJavaType());
                System.out.println("  Table: " + entity.getJavaType().getAnnotation(jakarta.persistence.Table.class));
                System.out.println("---");
            });
            System.out.println("==============================================\n");
        };
    }
}
