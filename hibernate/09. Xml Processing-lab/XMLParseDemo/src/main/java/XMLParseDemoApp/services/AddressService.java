package XMLParseDemoApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import XMLParseDemoApp.entities.Address;

@Service
public interface AddressService {

	void saveAddress(Address address);
	List<Address> getAllAddresses();
}
