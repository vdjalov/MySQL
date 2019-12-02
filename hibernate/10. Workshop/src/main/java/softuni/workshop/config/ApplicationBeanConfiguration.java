package softuni.workshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import softuni.workshop.util.FileUtil;
import softuni.workshop.util.FileUtilImpl;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.ValidatorUtilImpl;
import softuni.workshop.util.XmlParser;
import softuni.workshop.util.XmlParserImpl;

@Configuration
public class ApplicationBeanConfiguration {

    //TODO

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public FileUtil fileUtil(){
    	return new FileUtilImpl();
    }
    
    @Bean
    public XmlParser xmlParser() {
    	return new XmlParserImpl();
    }
    
    @Bean
    public ValidatorUtil validatorUtil(){
    	return new ValidatorUtilImpl();
    }
    
}
