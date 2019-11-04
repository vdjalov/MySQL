package services;



import java.util.List;

import entities.Category;

public interface CategoryService {
	
		public void saveCategory(Category category);
		public Category getOneRandomCategory();
		public List<Object[]> getAllCategoriesSortedByProductsCount();
}
