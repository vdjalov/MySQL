package alararestaurant.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import alararestaurant.domain.dtos.JSON.ItemsAndCategoryImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;

@Service
public class ItemServiceImpl implements ItemService {

private static final String FILE_PATH = "C:\\Users\\OK\\workspace\\AlaraRestaurant-Workshop\\AlaraRestaurant\\src\\main\\resources\\files\\items.json";
	
	private ItemRepository itemRepository;
	private CategoryRepository categoryRepository;
	private ValidationUtil validationUtilImpl;
	private Gson gson;
	private ModelMapper modelMapper;
	
	
	@Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository,
			ValidationUtil validationUtilImpl, Gson gson, ModelMapper modelMapper) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
		this.validationUtilImpl = validationUtilImpl;
		this.gson = gson;
		this.modelMapper = modelMapper;
	}

	@Override
    public Boolean itemsAreImported() {   
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
       File file = new File(FILE_PATH);
       FileInputStream fis = new FileInputStream(file);
       BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
       
       StringBuilder sb = new StringBuilder();
       String line = "";
       while((line = bf.readLine()) != null) {
    	   sb.append(line).append(System.lineSeparator());
       }
        return sb.toString();
    }

    @Override
    public String importItems(String items) {
    	ItemsAndCategoryImportDto[] dtos = this.gson.fromJson(items, ItemsAndCategoryImportDto[].class);
    	StringBuilder sBuilder = new StringBuilder();
    		for(ItemsAndCategoryImportDto dto: dtos) {
    			String categoryName = dto.getCategory();
    		   	Category category = this.categoryRepository.findByName(categoryName);
    		   	
    		   	if(category == null) {
    		   		category = new Category();
    		   		category.setName(categoryName);
    		   		Item item = this.modelMapper.map(dto, Item.class);
    		   		
    		   			if(this.validationUtilImpl.isValid(item) && this.validationUtilImpl.isValid(category)) {
    		   				this.categoryRepository.saveAndFlush(category);
    		   				item.setCategory(category);
    		   				this.itemRepository.saveAndFlush(item);
    		   				sBuilder.append(String.format("Record %s successfully imported.", dto.getName())).append(System.lineSeparator());
    		   			} else {
    		   				sBuilder.append("Invalid data format.").append(System.lineSeparator());
    		   			}
    		   	} else if(validationUtilImpl.isValid(this.modelMapper.map(dto, Item.class))){
    		   		Item item = this.modelMapper.map(dto, Item.class);
    		   		item.setCategory(category);
    		   		if(this.itemRepository.findByName(item.getName()) == null) {
    		   			this.itemRepository.saveAndFlush(item);
        		   		sBuilder.append(String.format("Record %s successfully imported.", dto.getName())).append(System.lineSeparator());
    		   		} else {
    		   			sBuilder.append("Invalid data format.").append(System.lineSeparator());
    		   		}
    		   	} else {
    		   		sBuilder.append("Invalid data format.").append(System.lineSeparator());
    		   	}
    		}
    	
        return sBuilder.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
