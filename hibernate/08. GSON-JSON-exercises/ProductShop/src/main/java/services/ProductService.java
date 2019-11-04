package services;

import java.util.List;

import entities.Product;

public interface ProductService {

	public void saveProduct(Product product);
	public List<Object[]> findAllProductsWithoutABuyer();	
	public List<Object[]> findAllSuccsessFullySoldProducts();
	public List<Product> findAll();
	
}
