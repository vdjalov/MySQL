package appRun;




import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import dtos.SeedCarsDto;
import dtos.SeedCustomersDto;
import dtos.SeedPartsDto;
import dtos.SeedSaleDto;
import dtos.SeedSuppliersDto;
import entities.Car;
import entities.Customer;
import entities.Part;
import entities.Sale;
import entities.Supplier;
import repositories.CarRepository;
import repositories.CustomerRepository;
import repositories.PartRepository;
import repositories.SaleRepository;
import repositories.SupplierRepository;


@Component
public class ConsoleRun implements CommandLineRunner {

	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	PartRepository partRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	SaleRepository saleRepository;
	
	private final List <Double> discounts = Arrays.asList(0d, 5d, 10d, 15d, 20d, 30d, 40d, 50d); 
	private final String dbBasePath = "C:\\Users\\OK\\workspace\\GSON-JSON-exercise-CarDealer\\DbFiles\\";
	private ModelMapper modelMapper;
	private Gson gson;
	
	
	public ConsoleRun() {
		this.modelMapper = new ModelMapper();
		this.gson = new Gson();
	}
	
	public void run(String... args) throws Exception {
//		SeedSuppliers();
//		SeedParts();
//		seedCars();
//		seedCustomers();
		seedASale();
	}

	
//	Seed a some random sales
	private void seedASale() {
		Random random = new Random();
		double discount = this.discounts.get(random.nextInt(this.discounts.size()));
		Optional<Customer> customer = this.customerRepository.findById((long) random.nextInt(this.customerRepository.findAll().size()));
		Optional<Car> car = this.carRepository.findById((long) random.nextInt(this.carRepository.findAll().size()));
		
			if(customer.get().isYoungDriver()) {
				discount = discount + 5d;
			}
			
		SeedSaleDto seedSaleDto = new SeedSaleDto();
		seedSaleDto.setDiscount(discount);
		seedSaleDto.setCar(car.get());
		seedSaleDto.setCustomer(customer.get());
		
		Sale sale = this.modelMapper.map(seedSaleDto, Sale.class);
		this.saleRepository.saveAndFlush(sale);
	}

//	Seed all customers from list of customers
	private void seedCustomers() throws IOException {
		Reader reader = createPath("customers.json");
		SeedCustomersDto[] seedCustomersDto = this.gson.fromJson(reader, SeedCustomersDto[].class);
		
			for(SeedCustomersDto currentCustomer : seedCustomersDto) {
				Customer customer = this.modelMapper.map(currentCustomer, Customer.class);
				this.customerRepository.saveAndFlush(customer);
			}
	
	}

	//	Seed cars and add 20 random parts
	private void seedCars() throws IOException {
		
		Reader reader = createPath("cars.json");
		SeedCarsDto []seedCarsDto = this.gson.fromJson(reader, SeedCarsDto[].class);
		
			for(SeedCarsDto currentCars:seedCarsDto) {
				Set<Part> parts = getBetweenTenAndTwentyRandomCarParts();
				currentCars.setParts(parts);
				Car car = this.modelMapper.map(currentCars, Car.class);
				this.carRepository.saveAndFlush(car);
			}
	}

	
	

//	Seeding parts with a random supplier 
	private void SeedParts() throws IOException {
		Reader reader = createPath("parts.json");
		SeedPartsDto []seedPartsDto = this.gson.fromJson(reader, SeedPartsDto[].class);
			for(SeedPartsDto currentPart: seedPartsDto) {
				Supplier supplier = getRandomSupplier();
				Part part = this.modelMapper.map(currentPart, Part.class);
				part.setSupplier(supplier);
				this.partRepository.saveAndFlush(part);
			}
	}


//  Seeding suppliers
	private void SeedSuppliers() throws IOException {
		 Reader reader = createPath("suppliers.json");
		 
		 SeedSuppliersDto []seedSuppliersDto = this.gson.fromJson(reader, SeedSuppliersDto[].class);
		for(SeedSuppliersDto currentSupplier : seedSuppliersDto) {
			Supplier supplier = this.modelMapper.map(currentSupplier, Supplier.class);
			this.supplierRepository.saveAndFlush(supplier);
		} 
	}
	
//	Creating the correct path 
	private Reader createPath(String endElement) throws IOException {
		String filePath = this.dbBasePath + endElement;
		Path path = new File(filePath).toPath();
		return Files.newBufferedReader(path, 
                StandardCharsets.UTF_8);
	}
	
//	Get a random supplier from the DB
	private Supplier getRandomSupplier() {
		List<Supplier> allSuppliers = this.supplierRepository.findAll();
		Random random = new Random();
		return allSuppliers.get(random.nextInt(allSuppliers.size()));
	}
	
	
//	Get between 10 and 20 parts and return them
	private Set<Part> getBetweenTenAndTwentyRandomCarParts() {
		List<Part> allParts = this.partRepository.findAll();
		Set<Part> selectedParts = new HashSet<>();
		Random random = new Random();
		int randomNumber = random.nextInt(10) + 10;
			for(int i = 0; i < randomNumber; i++) {
				selectedParts.add(allParts.get(random.nextInt(allParts.size())));
			}
		return selectedParts;
	}
}




















