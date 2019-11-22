package XMLParseCarDealer.services;

import java.util.List;

import XMLParseCarDealer.entities.Supplier;

public interface SupplierService {

	public void saveAndFlushSupplier(Supplier supplier);
	public Supplier getRandomSupplier();
	public List<Object[]> getLocalSuppliersAndPartCount();
}
