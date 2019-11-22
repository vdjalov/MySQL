package XMLParseCarDealer.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XMLParseCarDealer.entities.Customer;
import XMLParseCarDealer.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private Random random;
	
	@Override
	public void saveCustomer(Customer customer) {
		this.customerRepository.saveAndFlush(customer);
	}


	@Override
	public Optional<Customer> selectRandomCustomer() {
		int size = this.customerRepository.findAll().size();
		int id = this.random.nextInt(size);
		return this.customerRepository.findById((long)id);
	}


	@Override
	public List<Customer> getAllCustomersOrderedByBirthDate() {
		return this.customerRepository.getAllCustomersOrderedByBirthDate();
	}


	@Override
	public List<Object[]> getAllSalesByCustomer() {
		return this.customerRepository.getTotalSalesByCustomer();
	}

}
