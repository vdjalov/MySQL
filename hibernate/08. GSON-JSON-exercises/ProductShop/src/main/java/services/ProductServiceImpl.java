package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Product;
import repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	
	public void saveProduct(Product product) {
		this.productRepository.saveAndFlush(product); 
	}


	@Override
	public List<Object[]> findAllProductsWithoutABuyer() {
		return this.productRepository.findAllProductsWithoutABuyer();
	}


	@Override
	public List<Object[]> findAllSuccsessFullySoldProducts() {
		return this.productRepository.findAllSuccsessFullySoldProducts();
	}


	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}


}
