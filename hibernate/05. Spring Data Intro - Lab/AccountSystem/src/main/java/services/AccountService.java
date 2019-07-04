package services;

import java.math.BigDecimal;

import entities.Account;

public interface AccountService {

	void withdrawMoney(BigDecimal amount, Long id);
	void transferMoney(BigDecimal amount, Long id);
	void registerAccount(Account account);
	
}
