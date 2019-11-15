package XMLParseDemoApp.beans;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import XMLParseDemoApp.services.AddressServiceImpl;
import XMLParseDemoApp.services.UserServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public UserServiceImpl userServicesImpl() {
		return new UserServiceImpl();
	}
	
	@Bean
	public AddressServiceImpl addressServiceImpl() {
		return new AddressServiceImpl();
	}
	
	
	@Bean
	public BufferedReader bufferedReader(){
		return new BufferedReader(new InputStreamReader(System.in));
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
