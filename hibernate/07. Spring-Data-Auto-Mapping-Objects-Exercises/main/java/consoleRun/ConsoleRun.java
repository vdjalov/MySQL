package consoleRun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import DTO.AllGamesDto;
import DTO.GameDetailsDto;
import DTO.GameRegisterDto;
import DTO.UserRegisterDto;
import entities.Game;
import entities.User;
import printer.ConsolePrinter;
import services.GameService;
import services.UserService;


@Component
public class ConsoleRun implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;
	
	private ModelMapper modelMapper;
	private BufferedReader bufferedReader;
	
	public ConsoleRun (){
		this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		this.modelMapper = new ModelMapper();
	
	}
	
	
	public void run(String... args) throws Exception {
		ConsolePrinter.println("Please enter a command:[press \"ENTER\" to exit]:");
		String input[] = this.bufferedReader.readLine().split("[|]+");
		
		while(!input[0].trim().isEmpty()) {
			String command = input[0];
			
			switch(command) {
			case "RegisterUser":
				String email = input[1];
				String password = input[2];
				String repeatPassword = input[3];
				String fullName = input[4];
				if(password.equals(repeatPassword)){
					registerUser(email, password, fullName);
				} else {
					ConsolePrinter.println("Password and repeat password must be the same!");
				}
				break;
			case "LoginUser":
				 email = input[1];
				 User user = this.userService.findByEmail(email);
				if(user != null && (!user.isLogged())) {
					user.setLogged(true);
					this.userService.saveUser(user);
					ConsolePrinter.println("Successfully logged in " + input[2]);
				} else {
					if(user == null) {
						ConsolePrinter.println("User does not exist.");
					} else {
						ConsolePrinter.println("User already logged in.");
					}
				};
				break;
			case "LogoutUser":
				email = input[1];
				user = this.userService.findByEmail(email);
				if(user != null && user.isLogged()) {
					user.setLogged(false);
					this.userService.saveUser(user);
					ConsolePrinter.println("Successfully logged out " + user.getFullName());
				} else {
					if(user == null) {
						ConsolePrinter.println("User does not exist.");	
					} else {
						ConsolePrinter.println("Cannot log out. No user was logged in.");
					}
				}
				break;
			case "AddGame":
				String title = input[1];
				double price = Double.valueOf(input[2]);
				double size = Double.valueOf(input[3]);
				String trailer = input[4];
				String imageURL = input[5];
				String description = input[6];
				SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
				Date releaseDate = format.parse(input[7]);
				registerGame(title, price, size, trailer, imageURL, description, releaseDate);
				break;
			case "EditGame":
				long id = Long.valueOf(input[1]);
				Optional<Game> currentGame = this.gameService.findGameById(id);
					if(currentGame.isPresent()) {
						editGame(currentGame, input);
						ConsolePrinter.println("Edited " + currentGame.get().getTitle());
					} else {
						ConsolePrinter.println("Game id does not exist.");
					}
				break;
			case "DeleteGame":
				id = Long.valueOf(input[1]);
				currentGame = this.gameService.findGameById(id);
					if(currentGame.isPresent()) {
						this.gameService.deleteGameById(id);
						ConsolePrinter.println("Deleted " + currentGame.get().getTitle());
					} else {
						ConsolePrinter.println("Wrong id.");
					}
				break;
			case "AllGames":
				showAllGamesDetails();
				break;
			case "DetailGame":
				title = input[1];
				showGameDetailsByTitle(title);
				break;
				default: ConsolePrinter.println("Command does not exist.");
			}
			ConsolePrinter.println("Please enter a command:[press \"ENTER\" to exit]:");
			input = this.bufferedReader.readLine().split("[|]+");
		}
	}

	// Show a game details by title
	private void showGameDetailsByTitle(String title) {
		Game game =this.gameService.checkIfGameExistsInTheDatabase(title);  
		if(game != null) {
			GameDetailsDto gameDetailsDto = this.modelMapper.map(game, GameDetailsDto.class);
			ConsolePrinter.println(gameDetailsDto.toString());
		} else {
			ConsolePrinter.println("Wrong game title.");
		}
	}

	// All GAMES
	private void showAllGamesDetails() {
		this.gameService.getAllGames()
		.forEach(game -> {
			AllGamesDto allGamesDto = this.modelMapper.map(game, AllGamesDto.class);
			ConsolePrinter.println(allGamesDto.toString());
		});
		
	}

	// Edit game method
	private void editGame(Optional<Game> currentGame, String[] input) throws ParseException {
		for(int i = 2; i < input.length ;i++) {
			String split[] = input[i].split("[=]+");
			String property = split[0];
				switch(property) {
					case "title":
						String title = split[1];
						currentGame.get().setTitle(title);
						break;
					case "price":
						double price = Double.valueOf(split[1]);
						currentGame.get().setPrice(price);
						break;
					case "size":
						double size = Double.valueOf(split[1]);
						currentGame.get().setSize(size);
						break;
					case "trailer":
						String trailer = split[1];
						currentGame.get().setTrailer(trailer);
						break;
					case "imageURL":
						String imageURL = split[1];
						currentGame.get().setImage(imageURL);
						break;
					case "description":
						String description = split[1];
						currentGame.get().setDescription(description);
						break;
					case "releaseDate":
						SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
						Date date = sdf.parse(split[1]);
						currentGame.get().setReleaseDate(date);
						break;
					default: ConsolePrinter.println("Wrong property.");	
				}
		}
		this.gameService.registerGame(currentGame.get());
		
	}


	// Register a game
	private void registerGame(String title, double price, double size, String trailer, String imageURL,
			String description, Date releaseDate) {
		GameRegisterDto gameRegisterDto = new GameRegisterDto();
		gameRegisterDto.setTitle(title);
		gameRegisterDto.setPrice(price);
		gameRegisterDto.setSize(size);
		gameRegisterDto.setTrailer(trailer);
		gameRegisterDto.setImage(imageURL);
		gameRegisterDto.setDescription(description);
		gameRegisterDto.setReleaseDate(releaseDate);
		Game game = this.modelMapper.map(gameRegisterDto, Game.class);
	
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator gameValidator = validationFactory.getValidator();
		Set<ConstraintViolation<Game>> gameViolations = gameValidator.validate(game);
		
		if(gameViolations.size() > 0) {
			gameViolations.forEach(violation -> {
				ConsolePrinter.println(violation.getMessage());
			});
		} else if (this.gameService.checkIfGameExistsInTheDatabase(title)!= null) {
			ConsolePrinter.println("Game already exists.");
		} else {
			this.gameService.registerGame(game);
			ConsolePrinter.println("Added game " + title);
		}
		
		
	}


	// Register user
	private void registerUser(String email, String password, String fullName) {
		UserRegisterDto userDto = new UserRegisterDto();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setFullName(fullName);
		
		User user = this.modelMapper.map(userDto, User.class);
		
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validationFactory.getValidator();
		Set<ConstraintViolation<User>> userViolations = validator.validate(user);
		
		if(userViolations.size() > 0) {
			userViolations.forEach(violation -> {
				ConsolePrinter.println(violation.getMessage());
			});
		} else {
			if(checkIfAdmin() == 0) {
				user.setAdmin(true);
			} 
			
			if(this.userService.findIfEmailIsAlreadyRegistered(email)) {
				ConsolePrinter.println("User already registered!");
			} else {
				this.userService.saveUser(user);
				ConsolePrinter.println(fullName + " was registered");
			}
		}
	}


	// Supplementary method checking if a the user is the first
	private int checkIfAdmin() {	
		return this.userService.findUsersSize();
	}

	
	

}
