package appRun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"repositories"})
@EntityScan(basePackages = {"entities"})
@ComponentScan(basePackages = {"appRun", "services"})
public class App {
    public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    	
//       Any other code here will be executed after the ConsoleRun.class logic has been executed    	
//    	System.out.println("After execution");
    }
}
