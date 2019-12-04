package alararestaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alararestaurant.domain.entities.Category;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	@Autowired
    public CategoryServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
		
		this.categoryRepository = categoryRepository;
	}

	
	@Override
    public String exportCategoriesByCountOfItems() {
     
		StringBuilder sBuilder = new StringBuilder();
		String currentCategoryName = "";
		List<Object[]> allIems = this.categoryRepository.categriesByCountOfItems();
		
		for(Object item[] : allIems) {
			Optional<Category> category = this.categoryRepository.findById(Integer.valueOf(item[0].toString()));
			String categoryName = category.get().getName();
				if(currentCategoryName.equals(categoryName)) {
					sBuilder.append(String.format("--Item Name:{%s}%n--Item Price:{%.2f}%n",
							item[1], item[2]))
							.append(System.lineSeparator());
				} else {
					sBuilder.append(String.format("Category:{%s}%n--Item Name:{%s}%n--Item Price:{%.2f}%n",
							categoryName, item[1], item[2]))
						    .append(System.lineSeparator());
				}
			currentCategoryName = categoryName;
		};
        return sBuilder.toString();
    }
}
