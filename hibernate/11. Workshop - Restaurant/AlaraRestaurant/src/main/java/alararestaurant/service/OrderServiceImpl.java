package alararestaurant.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alararestaurant.domain.dtosXML.BaseItemDto;
import alararestaurant.domain.dtosXML.BaseOrderDto;
import alararestaurant.domain.dtosXML.OrderDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;

@Service
public class OrderServiceImpl implements OrderService {

	private static final String FILE_PATH = "C:\\Users\\OK\\workspace\\AlaraRestaurant-Workshop\\AlaraRestaurant\\src\\main\\resources\\files\\orders.xml";
	
	private OrderItemRepository orderItemRepository;
	private ItemRepository itemRepository;
	private EmployeeRepository employeeRepository;
	private OrderRepository orderRepository;
	private XmlParser xmlParserImpl;
	private ValidationUtil validationUtilImpl;
	private ModelMapper modelMapper;
	
	@Autowired
	public OrderServiceImpl(OrderItemRepository orderItemRepository, ItemRepository itemRepository, EmployeeRepository employeeRepository, OrderRepository orderRepository, 
			XmlParser xmlParserImpl, ValidationUtil validationUtilImpl, ModelMapper modelMapper) {
			this.orderItemRepository = orderItemRepository;
			this.itemRepository = itemRepository;
			this.employeeRepository = employeeRepository;
			this.orderRepository = orderRepository;
			this.xmlParserImpl = xmlParserImpl;
			this.validationUtilImpl = validationUtilImpl;
			this.modelMapper = modelMapper;
	}
	
	
	
    @Override
    public Boolean ordersAreImported() {
       return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws JAXBException, IOException {
    	BufferedReader bf = readFile();
    	
    	StringBuilder sBuilder = new StringBuilder();
    	String line = "";
    	while((line = bf.readLine()) != null) {
    		sBuilder.append(line).append(System.lineSeparator());
    	}
        return sBuilder.toString();
    }

   

	@Override
    public String importOrders() throws FileNotFoundException, JAXBException {
      BufferedReader bf = this.readFile();
      Unmarshaller unmarshaller = this.xmlParserImpl.getUnamrshaller(OrderDto.class);
      OrderDto orderDto = (OrderDto) unmarshaller.unmarshal(bf);
      
      StringBuilder sb = new StringBuilder();
      for(BaseOrderDto baseOrderDto : orderDto.getAllOrders()) {
    	Order order = this.modelMapper.map(baseOrderDto, Order.class);
    	Employee employee = this.employeeRepository.findByName(baseOrderDto.getEmployeeName());
    	boolean isInvalid = false;		
    		if(employee != null) {
    			order.setEmployee(employee);
    			List<OrderItem> allOrderedItems = new ArrayList<OrderItem>();
    			for(BaseItemDto bItemDto : baseOrderDto.getOrderItems().get(0).getAllItems()) {
    				Item item = this.itemRepository.findByName(bItemDto.getItemName());
    					if(item != null) {
    						OrderItem orderItem = new OrderItem();
    						orderItem.setQuantity(bItemDto.getQuantity());
    						orderItem.setItem(item);
    						allOrderedItems.add(orderItem);
    					} else {
    						isInvalid = true;
    						break;
    					}
    			}
    			
    			if(!isInvalid && this.validationUtilImpl.isValid(order)) {
    				this.orderRepository.saveAndFlush(order);
    				for(OrderItem orderItem : allOrderedItems) {
    					orderItem.setOrder(order);
    						if(this.validationUtilImpl.isValid(orderItem)) {
    							this.orderItemRepository.saveAndFlush(orderItem);
    						}
    				}
    				
    				sb.append(String.format("Order for %s on %s added." , order.getCustomer(), order.getDateTime()))
    				  .append(System.lineSeparator());
    			}
    		} else {
    			sb.append("Invalid data format.").append(System.lineSeparator());
    		}
      }
        return sb.toString();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
    	
    	List<Object[]> ordersByBurgerFlippers = this.orderRepository.getOrdersFinishedByTheBurgerFlippers();
    	StringBuilder sBuilder = new StringBuilder();
    		String currentClientName = "";
    		for(Object order[] : ordersByBurgerFlippers) {
    			String clienName = order[1].toString();
    			
    			if(!currentClientName.equals(clienName)) {
    				sBuilder.append(String.format("Name: {%s}%nOrders:%n Customer:{%s}%n Items:%n", 
    						order[0].toString(), order[1].toString()))
    						.append(String.format("  Name:{%s}%n  Price:{%s}%n  Quantity:{%s}%n",
    								order[2].toString(), order[3].toString(), order[4].toString()))
    						.append(System.lineSeparator());		
    			} else {
    				sBuilder.append(String.format("  Name:{%s}%n  Price:{%s}%n  Quantity:{%s}%n",
							order[2].toString(), order[3].toString(), order[4].toString()))
					.append(System.lineSeparator());	
    			}
    			currentClientName = clienName;
    		}
        return sBuilder.toString();
    }
    
    private BufferedReader readFile() throws FileNotFoundException {
    	File file = new File(FILE_PATH);
    	FileInputStream fis = new FileInputStream(file);
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
		return bufferedReader;
		
	}
}
