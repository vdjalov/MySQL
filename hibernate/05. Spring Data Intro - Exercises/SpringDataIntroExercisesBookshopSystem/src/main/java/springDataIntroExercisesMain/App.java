package springDataIntroExercisesMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"repositories", "services", "entities", "springDataIntroExercisesMain", "enums"})
@EntityScan(basePackages = {"repositories", "services", "entities", "springDataIntroExercisesMain", "enums"})
@ComponentScan(basePackages = {"repositories", "services", "entities", "springDataIntroExercisesMain", "enums"})
public class App {
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
}
