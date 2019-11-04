package console;



import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import consolePrinter.ConsolePrinter;
import dtos.CategoriesByProductsCountDto;
import dtos.ProductsInRangeDto;
import dtos.SeedCategoriesDto;
import dtos.SeedProductsDto;
import dtos.SeedUsersDto;
import dtos.SuccessfullySoldProductsDto;
import dtos.SuccsessfullySoldProductsBuyerDto;
import entities.Category;
import entities.Product;
import entities.User;
import services.CategoryService;
import services.ProductService;
import services.UserService;


@Component
public class ConsoleRun implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	private Gson gson;
	private ModelMapper modelMapper;
	
	public ConsoleRun() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.modelMapper = new ModelMapper();
	}
	public void run(String... args) throws Exception {
//		seedUsersDatabase();
//		seedProductsDatabase();
//		seedCategories();
//		seedRandomCategories();
//		productsInRange();
//		successfullySoldProducts();
		categoriesByProductsCountJSON();
		
		
		
	}


//	Query 3 - Categories By Products Count
	private void categoriesByProductsCountJSON() {
		
		List<CategoriesByProductsCountDto> allCategoriesByProductsCountDtos = new ArrayList<>();
		
		List<Object[]> allCategoriesQuery = this.categoryService.getAllCategoriesSortedByProductsCount();
			for(Object category[] : allCategoriesQuery){
				CategoriesByProductsCountDto categoriesByProductsCountDto = new CategoriesByProductsCountDto();
				categoriesByProductsCountDto.setCategory(category[0].toString());
				categoriesByProductsCountDto.setProductsCount(category[1].toString());
				categoriesByProductsCountDto.setAveragePrice(category[2].toString());
				categoriesByProductsCountDto.setTotalRevenue(category[3].toString());
				allCategoriesByProductsCountDtos.add(categoriesByProductsCountDto);
			}
			
		String finalJSON = this.gson.toJson(allCategoriesByProductsCountDtos);
		ConsolePrinter.println(finalJSON);
		
	}
	//	Successfully sold products to JSON
	private void successfullySoldProducts() {
		List<Object[]> allProducts = this.productService.findAllSuccsessFullySoldProducts();
		String sellerFirstName = "";
		String sellerLastName = "";
		
		
		List<SuccessfullySoldProductsDto> allSuccessfullySoldProductsDtos = new ArrayList<>();
		SuccessfullySoldProductsDto successfullySoldProductsDto = null;
			for(Object object[] : allProducts) {		
				String tempSellerFirstName = checkElement(object[0]);
				String tempSellerLastName = checkElement(object[1]);
				String tempBuyerFirstName = checkElement(object[2]);
				String tempBuyerLastName = checkElement(object[3]);
				String tempProductName = checkElement(object[4]);
				String tempPrice = checkElement(object[5]);
			
					if(sellerFirstName.equals(tempSellerFirstName) && sellerLastName.equals(tempSellerLastName)) {
						 SuccsessfullySoldProductsBuyerDto soldProductsBuyerDto = new SuccsessfullySoldProductsBuyerDto();
						 soldProductsBuyerDto.setBuyerFirstName(tempBuyerFirstName);
						 soldProductsBuyerDto.setBuyerLastName(tempBuyerLastName);
						 soldProductsBuyerDto.setName(tempProductName);
						 soldProductsBuyerDto.setPrice(tempPrice);
						 successfullySoldProductsDto.getBuyers().add(soldProductsBuyerDto);
					} else {
							if(successfullySoldProductsDto != null) {
								allSuccessfullySoldProductsDtos.add(successfullySoldProductsDto);
							}
						 successfullySoldProductsDto = new SuccessfullySoldProductsDto();
						 successfullySoldProductsDto.setFirstName(tempSellerFirstName);
						 successfullySoldProductsDto.setLastName(tempSellerLastName);
						 sellerFirstName = tempSellerFirstName;
						 sellerLastName = tempSellerLastName;
						 SuccsessfullySoldProductsBuyerDto soldProductsBuyerDto = new SuccsessfullySoldProductsBuyerDto();
						 soldProductsBuyerDto.setBuyerFirstName(tempBuyerFirstName);
						 soldProductsBuyerDto.setBuyerLastName(tempBuyerLastName);
						 soldProductsBuyerDto.setName(tempProductName);
						 soldProductsBuyerDto.setPrice(tempPrice);
						 successfullySoldProductsDto.getBuyers().add(soldProductsBuyerDto);
					}
			}
			String finalJSON = this.gson.toJson(allSuccessfullySoldProductsDtos);
		ConsolePrinter.println(finalJSON);
			
			
	}
	
	
	// Validation method - checks if returned object is null
	private String checkElement(Object object) {
		if(object != null) {
			 return object.toString();
		}
		return "null";
	}
	
	
	
	//	 Find all products in range 
	private void productsInRange() {
		List<Object[]> findProjectWithoutABuyer =
				this.productService.findAllProductsWithoutABuyer();
		List<ProductsInRangeDto> productsInRange = new ArrayList<>();
		for(Object object[]: findProjectWithoutABuyer) {
			
			ProductsInRangeDto productsInRangeDto = new ProductsInRangeDto();
			productsInRangeDto.setName(object[0].toString());
			productsInRangeDto.setPrice(object[1].toString());

				if(object[2] == null) {
					if(object[3] == null) {
						productsInRangeDto.setSeller("NoName");
					} else {
						productsInRangeDto.setSeller(object[3].toString());
					}
				} else {
					productsInRangeDto.setSeller(object[2].toString() + " " + object[3]);
				}
				productsInRange.add(productsInRangeDto);
		}
		
		String productsInRangeJson = this.gson.toJson(productsInRange);
		ConsolePrinter.println(productsInRangeJson);
	}
	
	
	// Seeding categories in the database
	private void seedCategories() throws IOException {
		String filePath = "C:\\\\Users\\OK\\Desktop\\08. DB-Advanced-JSON-Processing-Exercises-Resources\\categories.json";
		Path path = new File(filePath).toPath();
		Reader reader = Files.newBufferedReader(path, 
                StandardCharsets.UTF_8);
		
		SeedCategoriesDto[] seedCategoriesDtos = this.gson.fromJson(reader, SeedCategoriesDto[].class);
		
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator categoryValidator = validationFactory.getValidator();
		
		Arrays.stream(seedCategoriesDtos).forEach(seedCat -> {
			Category category = this.modelMapper.map(seedCat, Category.class);
		
			Set<ConstraintViolation<Category>> categoryViolations = categoryValidator.validate(category);
			
				if(categoryViolations.size() == 0) {
					this.categoryService.saveCategory(category);
				} else {
					categoryViolations.forEach(violation -> ConsolePrinter.println(violation.getMessage()));
				}
		});
	}
	
	// Seeding products database
	private void seedProductsDatabase() throws IOException {
		String filePath = "C:\\\\Users\\OK\\Desktop\\08. DB-Advanced-JSON-Processing-Exercises-Resources\\products.json";
		Path path = new File(filePath).toPath();
		Reader reader = Files.newBufferedReader(path, 
                StandardCharsets.UTF_8);
		
		SeedProductsDto[] seedProductsDtos = this.gson.fromJson(reader, SeedProductsDto[].class);
		
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator productValidator = validationFactory.getValidator();
		int counter = 1;
			for(SeedProductsDto seedProductsDto: seedProductsDtos) {
				
				User buyer = this.userService.findRandomUser();
				User seller = this.userService.findRandomUser();
					if(counter % 5 != 0) {
						seedProductsDto.setBuyer(buyer);	
					}
					
				seedProductsDto.setSeller(seller);
				Product product = this.modelMapper.map(seedProductsDto, Product.class);
				Set<ConstraintViolation<Product>> productViolations = productValidator.validate(product);
					if(productViolations.size() == 0) {	
						this.productService.saveProduct(product);
					} else {
						productViolations.forEach(violation -> ConsolePrinter.println(violation.getMessage()));
					}
					counter++;
			}
	}
	
	// Seeding users database
	private void seedUsersDatabase() throws IOException {
		
		String filePath = "C:\\\\Users\\OK\\Desktop\\08. DB-Advanced-JSON-Processing-Exercises-Resources\\users.json";
        Path path = new File(filePath).toPath();
		Reader reader = Files.newBufferedReader(path, 
                StandardCharsets.UTF_8);
        
		SeedUsersDto[] seedUserDtos = gson.fromJson(reader,SeedUsersDto[].class);
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator userValidator = validationFactory.getValidator();
		
			for(SeedUsersDto seedUserDto: seedUserDtos) {
				User user = this.modelMapper.map(seedUserDto, User.class);
				Set<ConstraintViolation<User>> userViolations = userValidator.validate(user);
					if(userViolations.size() == 0) {
						User friend = this.userService.findRandomUser();
					
						if(friend != null) {
							user.getFriends().add(friend);
						}
						this.userService.saveUser(user);
					} else {
						userViolations.forEach(violation -> ConsolePrinter.println(violation.getMessage()));
					}	
			}
	}
	
	
	// Seeding random categories to every product 
	private void seedRandomCategories() {
		List<Product> allProducts = this.productService.findAll();
		
		for(Product product : allProducts) {
			Category category = this.categoryService.getOneRandomCategory();
//			category.getProducts().add(product);
//			this.categoryService.saveCategory(category);
			product.getCategories().add(category);
			this.productService.saveProduct(product);	
		}
		
	}
	
}














