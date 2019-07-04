package accountSystem;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import entities.Account;
import entities.User;
import services.AccountServiceImpl;
import services.UserServiceImpl;


@Component
public class ConsoleRunner implements CommandLineRunner {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private AccountServiceImpl accountService;
	
	
	public void run(String... args) throws Exception {
			
			User user = new User();
			user.setAge(25);
			user.setUsername("John");
			
			User AnotherUser = new User();
			AnotherUser.setAge(22);
			AnotherUser.setUsername("Ivan");
			
			Account account = new Account();
			account.setBalance(new BigDecimal("200"));
			account.setUser(user);
			
			Account anotherAccount = new Account();
			anotherAccount.setBalance(new BigDecimal("0"));
			anotherAccount.setUser(AnotherUser);
//			
//			this.userService.registerUser(AnotherUser);
//			this.userService.registerUser(user);
//			this.accountService.registerAccount(anotherAccount);
//			this.accountService.registerAccount(account);
			
			this.accountService.withdrawMoney(new BigDecimal("100"), 2L);
		
			
	}

}
