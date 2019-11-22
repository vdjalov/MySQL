package XMLParseCarDealer.beans;



import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import XMLParseCarDealer.services.CarServiceImpl;
import XMLParseCarDealer.services.CustomerServiceImpl;
import XMLParseCarDealer.services.PartServiceImpl;
import XMLParseCarDealer.services.SaleServiceImpl;
import XMLParseCarDealer.services.SupplierServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	@Bean
	Random getRandom(){
		return new Random();
	}
	
	
	@Bean
	SupplierServiceImpl supplierServiceImpl() {
		return new SupplierServiceImpl();
	}
	
	
	@Bean
	PartServiceImpl partServiceImpl() {
		return new PartServiceImpl();
	}
	
	
	@Bean
	CarServiceImpl carServiceImpl() {
		return new CarServiceImpl();
	}
	
	
	@Bean
	CustomerServiceImpl customerServiceImpl() {
		return new CustomerServiceImpl();
	}
	
	
	@Bean
	SaleServiceImpl saleServiceImpl(){
		return new SaleServiceImpl();
	}
	
	
	
	
}
