package controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.directory.SearchControls;

import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



import entities.Ingredient;
import entities.Shampoo;
import entities.Size;
import net.bytebuddy.asm.Advice.Enter;
import repository.IngredientRepository;
import repository.LabelRepository;
import repository.ShampooRepository;

@Component
public class ConsoleRunner implements CommandLineRunner {

	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private ShampooRepository shampooRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	private BufferedReader bf;
	
	public ConsoleRunner() {
		this.bf = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run(String... args) throws Exception {
		//this.getBySubtitleValue();
		//System.out.println(this.GetAllBySize());
		//System.out.println(getShampoosBySizeOrLabelIdOrderByPrice());
		//System.out.println(getAllByPriceHigherThanOrderDesc());
		//System.out.println(getAllByNameStartingWithLetter());
		//System.out.println(getAllIngredientsThatAreInTheList());
		//System.out.println(getAllShampoosWithPriceLowerThanTheGiven());
		//searchByAListOfIngredients();
		//selectShampoosByIngredientCountLessThan();
		//selectIngredientNameAndShampooBrandByName();
		//deleteIngredientsByName();
		//updateAllIngredientsCostWith10Percent();
			updateAllIncredientsInTheListByGivenInputPercentage();
		
	}
	
	
	// 12. Update Ingredients by Names
	private void updateAllIncredientsInTheListByGivenInputPercentage() throws NumberFormatException, IOException {
		System.out.println("Please enter a percentage increase:");
		double percentageIncrease = Double.parseDouble("1." + this.bf.readLine()); // To be fixed for high percentages! 
		
		System.out.println("Please enter an ingredient name. Double enter to exit.");
		List<String> ingredientsNames = new ArrayList<>();
		String ingredientName = "";
		while((ingredientName = this.bf.readLine()) != null && !ingredientName.isEmpty()) {
			ingredientsNames.add(ingredientName);
		}
		this.ingredientRepository
			.updateIngredientPriceByGivenNameAndPercentageIncrease(percentageIncrease, ingredientsNames);
	}

	//11. Update Ingredients by price
	private void updateAllIngredientsCostWith10Percent() {
		this.ingredientRepository.increaseCostsBy10PercentOnEveryIngredient();
		
	}

	//10.Delete Ingredients by name
	private void deleteIngredientsByName() throws IOException {
		System.out.println("Please enter an igredient name:");
		String ingredientName = bf.readLine();
		this.ingredientRepository.deleteIngredientByName(ingredientName);
		
		
		
	}

	
	
	// 9.Select Ingredient Name and Shampoo Brand By Name
	private void selectIngredientNameAndShampooBrandByName() throws IOException {
		System.out.println("Enter a shampoo brand:");
		String brand = bf.readLine();
		
		String result = this.ingredientRepository.findIngredientsSum(brand);
		System.out.println(result);
	}

	// 8.Select Shampoos by Ingredients Count
	private void selectShampoosByIngredientCountLessThan() throws NumberFormatException, IOException {
		System.out.println("Please enter a shampoo count:");
		int count = Integer.valueOf(this.bf.readLine());
		
			this.shampooRepository.searchByShampooCountLessThan(count)
			    .forEach(s -> System.out.println(s));
	}

	// 7.Select Shampoos by Ingredients
	private void searchByAListOfIngredients() throws IOException {
		System.out.println("Enter ingredients:");
		
		List<String> ingredients = new ArrayList<>();
		String input = "";
			while((input = bf.readLine()) != null && !input.isEmpty()) {
				ingredients.add(input);
			}
		this.shampooRepository.searchByIgredients(ingredients)
			.forEach(s -> System.out.println(s));
		
		
	}

	// Find all by price lower than an input price
	private int getAllShampoosWithPriceLowerThanTheGiven() throws NumberFormatException, IOException {
		System.out.println("Please enter price:");
		
		List<Shampoo> result = this.shampooRepository.findByPriceLessThan(new BigDecimal(this.bf.readLine()))
			.stream()
			.collect(Collectors.toList());
	
		return result.size();
	}

	//Print all ingredients in the list 
	private String getAllIngredientsThatAreInTheList() {
			StringBuilder sBuilder = new StringBuilder();
			List<String> ingredients = new ArrayList<String>();
			Collections.addAll(ingredients, "Lavender", "Herbs", "Apple");
			
					this.ingredientRepository.findAll()
						.stream()
						.filter(ingredient -> ingredients.contains(ingredient.getName()))
						.sorted((a, b) -> Double.compare(a.getPrice().doubleValue(), b.getPrice().doubleValue()))
						.forEach(ingredient -> sBuilder.append(ingredient.getName()).append("\n"));
										
		return sBuilder.toString();
	}

	// Get all ingredients Starting with a chosen letter
	private String getAllByNameStartingWithLetter() throws IOException {
		StringBuilder sBuilder = new StringBuilder();
		System.out.println("Please enter a letter:");
		String letter = this.bf.readLine();
		
		this.ingredientRepository.findAll()
			.stream()
			.filter(ingredient -> ingredient.getName().startsWith(letter.toUpperCase()))
			.forEach(ingredient -> sBuilder.append(ingredient.getName()).append("\n"));
		
		return sBuilder.toString();
	}

	// Get by price higher than given price order desc
	private String getAllByPriceHigherThanOrderDesc() throws IOException {
		StringBuilder sBuilder = new StringBuilder();
		System.out.println("Please enter price:");
		double price = Double.valueOf(this.bf.readLine());
		
		this.shampooRepository.findAll()
						      .stream()
						      .filter(shampoo -> shampoo.getPrice().doubleValue() > price)
						      .sorted((a, b) -> Double.compare(b.getPrice().doubleValue(), a.getPrice().doubleValue()))
							  .forEach(shampoo -> {
								  sBuilder.append(shampoo.getBrand()).append(" ")
								  	      .append(shampoo.getSize().toString()).append(" ")
								  	      .append(shampoo.getPrice()).append("lv.\n");
							  });
		return sBuilder.toString();
	}

	// Shampoos by Size or Label_Id order asc
	private String getShampoosBySizeOrLabelIdOrderByPrice() throws NumberFormatException, IOException {
		StringBuilder sBuilder = new StringBuilder();
		
		System.out.println("Please enter size:");
		String size = bf.readLine();
		System.out.println("Please enter label ID:");
		long labelId = Long.valueOf(this.bf.readLine());
		
		Size finalSize = null;
		if(size.equalsIgnoreCase("small")) {
			finalSize = Size.SMALL;
		} else if(size.equalsIgnoreCase("medium")) {
			finalSize = Size.MEDIUM;
		} if(size.equalsIgnoreCase("large")) {
			finalSize = Size.LARGE;
		} 
		
		this.shampooRepository.findByLabelIdOrSizeOrderByPriceAsc(labelId, finalSize)
							  .forEach(shampoo -> sBuilder.append(shampoo.getBrand())
									  					  .append(" ")
									  					  .append(shampoo.getSize().toString())
									  					  .append(" ")
									  					  .append(shampoo.getPrice())
									  					  .append("lv.\n"));
		
		return sBuilder.toString();
	}

	

	//Get all by Size
	public String GetAllBySize() throws IOException {
		StringBuilder sBuilder = new StringBuilder();
		System.out.println("Enter size(default \"large\"):");
		String size = this.bf.readLine();
		Size finalSize = Size.LARGE;
			if(size.equalsIgnoreCase("small")) {
				finalSize = Size.SMALL;
			} else if(size.equalsIgnoreCase("medium")) {
				finalSize = Size.MEDIUM;
			} 
			
		this.shampooRepository.findBySizeOrderById(finalSize)
			.forEach(shampoo -> {
				sBuilder.append(shampoo.getBrand()).append(" ")
						.append(shampoo.getSize().toString()).append(" ")
						.append(shampoo.getPrice()).append("lv").append(System.lineSeparator());
			});;
			return sBuilder.toString();
	}
	// Get all By subtitle value 
	public void getBySubtitleValue() throws IOException{
		
		System.out.println("Please enter a subtitle:");
		String subtitle = this.bf.readLine(); // input the subtitle; 
		this.labelRepository.findBySubtitle(subtitle)
				.forEach(a -> {
					System.out.printf("ID: %d%nTitle: %s%nSubtitle: %s%n",
							a.getId(), a.getTitle(), a.getSubtitle());
				});
	}

}
