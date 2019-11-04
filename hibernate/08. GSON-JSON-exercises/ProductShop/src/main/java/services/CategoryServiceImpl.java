package services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Category;
import repositories.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public void saveCategory(Category category) {
		this.categoryRepository.saveAndFlush(category);
	}


	@Override
	public Category getOneRandomCategory() {
		Random random = new Random();
		int categorySize = random.nextInt(this.categoryRepository.findAll().size());
		return this.categoryRepository.findAll().get(categorySize);
	}


	@Override
	public List<Object[]> getAllCategoriesSortedByProductsCount() {
		return this.categoryRepository.categoriesByProductsCount();
	}

}
