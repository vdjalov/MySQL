package services;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Account;
import repositories.AccountRepository;
import repositories.UserRepository;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accounts;
	
	@Autowired
	private UserRepository usersRepository;
	
	public void withdrawMoney(BigDecimal withdrawalAmount, Long id) {
		Optional<Account> currentAcc = this.accounts.findById(id);
		
			if(currentAcc.isPresent()) {
					if(usersRepository.findById(id).isPresent()) {
						BigDecimal finalBalance = 
								currentAcc.get().getBalance().subtract(withdrawalAmount);
						if(finalBalance.doubleValue() >= 0) { 	//Correcting the withdrawal account initial account
							currentAcc.get().setBalance(finalBalance);
						
							System.out.println(currentAcc.get().getBalance());
						} else {
							System.out.println("Not enough balance!");
						}
					}else {
						System.out.println("User does not exist!");
					}
			} else {
				System.out.println("No such account Id!");
			}
	}

	
	// Adding money to the account
	public void transferMoney(BigDecimal withdrawalAmount, Long id) {
		Optional<Account> currentAcc = this.accounts.findById(id);
		
		if(currentAcc.isPresent()) {
				if(usersRepository.findById(id).isPresent()) {
					BigDecimal finalBalance = 
							currentAcc.get().getBalance().add(withdrawalAmount);
					if(finalBalance.doubleValue() >= 0) {
						currentAcc.get().setBalance(finalBalance); 	//Correcting the withdrawal account initial account
					
						System.out.println(currentAcc.get().getBalance());
					} else {
						System.out.println("Not enough balance!");
					}
				}else {
					System.out.println("User does not exist!");
				}
		} else {
			System.out.println("No such account Id!");
		}
	}

	public void registerAccount(Account account) {
		this.accounts.save(account);
		
	}

}
