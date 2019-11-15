package XMLParseDemoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"XMLParseDemoApp.repositories"})
@EntityScan(basePackages = {"XMLParseDemoApp.entities"})
@ComponentScan(basePackages = {"XMLParseDemoApp.controllers", "XMLParseDemoApp.beans"})
public class App {
    public static void main( String[] args ){
       SpringApplication.run(App.class, args);
    
       
    }
}
