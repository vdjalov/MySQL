package XMLParseDemoApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import XMLParseDemoApp.entities.Address;
import XMLParseDemoApp.repositories.AddressRepository;

public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	
	public void saveAddress(Address address) {
		this.addressRepository.saveAndFlush(address);
	}


	public List<Address> getAllAddresses() {
		return this.addressRepository.findAll();
	}

}
