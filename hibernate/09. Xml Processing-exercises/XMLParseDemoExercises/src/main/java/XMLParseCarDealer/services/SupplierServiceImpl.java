package XMLParseCarDealer.services;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XMLParseCarDealer.entities.Supplier;
import XMLParseCarDealer.repositories.SupplierRepository;


@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public void saveAndFlushSupplier(Supplier supplier) {
		this.supplierRepository.saveAndFlush(supplier);
	}

	
	@Override
	public Supplier getRandomSupplier() {
	Random rnd = new Random();
	int bound = this.supplierRepository.findAll().size();
	Supplier supplier = this.supplierRepository.findAll().get(rnd.nextInt(bound));
		return supplier;
	}


	@Override
	public List<Object[]> getLocalSuppliersAndPartCount() {
		return this.supplierRepository.getLocalSuppliersAndPartCount();
	}
	
	
	
	
	

}
