package alararestaurant.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ItemService {

    Boolean itemsAreImported();

    String readItemsJsonFile() throws FileNotFoundException, IOException;

    String importItems(String items);
}
