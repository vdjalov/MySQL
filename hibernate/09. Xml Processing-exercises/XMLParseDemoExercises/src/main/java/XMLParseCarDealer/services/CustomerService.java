package XMLParseCarDealer.services;

import java.util.List;
import java.util.Optional;

import XMLParseCarDealer.entities.Customer;

public interface CustomerService {

	public void saveCustomer(Customer customer);
	public Optional<Customer> selectRandomCustomer();
	public List<Customer> getAllCustomersOrderedByBirthDate();
	public List<Object[]> getAllSalesByCustomer();
}
