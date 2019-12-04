package alararestaurant.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface OrderService {

    Boolean ordersAreImported();

    String readOrdersXmlFile() throws FileNotFoundException, JAXBException, IOException;

    String importOrders() throws FileNotFoundException, JAXBException;

    String exportOrdersFinishedByTheBurgerFlippers();
}
