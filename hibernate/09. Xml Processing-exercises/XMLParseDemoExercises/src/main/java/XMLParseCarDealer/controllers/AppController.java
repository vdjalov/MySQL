package XMLParseCarDealer.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.validation.groups.Default;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import XMLParseCarDealer.consolePrinter.ConsolePrinter;
import XMLParseCarDealer.dtos.carDtos.AllDiscountedSalesInfo;
import XMLParseCarDealer.dtos.carDtos.CarInfo;
import XMLParseCarDealer.dtos.carDtos.ParseCarDto;
import XMLParseCarDealer.dtos.carDtos.ParseMultipleCarsDto;
import XMLParseCarDealer.dtos.carDtos.SaleInfo;
import XMLParseCarDealer.dtos.carsAndPartsDtos.CarDto;
import XMLParseCarDealer.dtos.carsAndPartsDtos.CarsPartsWrapper;
import XMLParseCarDealer.dtos.carsAndPartsDtos.PartDto;
import XMLParseCarDealer.dtos.customerDtos.ParseCustomerDto;
import XMLParseCarDealer.dtos.customerDtos.ParseMultipleCustomersDto;
import XMLParseCarDealer.dtos.customerDtos.SaleByCustomer;
import XMLParseCarDealer.dtos.customerDtos.SalesByCustomerWrapped;
import XMLParseCarDealer.dtos.partDtos.ParseMultiplePartsDto;
import XMLParseCarDealer.dtos.saleDtos.SaleDto;
import XMLParseCarDealer.dtos.supplierDtos.LocalSuppliersCountDto;
import XMLParseCarDealer.dtos.supplierDtos.MultipleLocalSuppliersCountDto;
import XMLParseCarDealer.dtos.supplierDtos.ParseMultipleSuppliersDto;
import XMLParseCarDealer.entities.Car;
import XMLParseCarDealer.entities.Customer;
import XMLParseCarDealer.entities.Part;
import XMLParseCarDealer.entities.Sale;
import XMLParseCarDealer.entities.Supplier;
import XMLParseCarDealer.services.CarServiceImpl;
import XMLParseCarDealer.services.CustomerServiceImpl;
import XMLParseCarDealer.services.PartServiceImpl;
import XMLParseCarDealer.services.SaleService;
import XMLParseCarDealer.services.SaleServiceImpl;
import XMLParseCarDealer.services.SupplierServiceImpl;


@Component
public class AppController implements CommandLineRunner {

	public static final double[] DEFAULT_DISCOUNTS = {0d, 5d, 10d, 20d, 40d, 50d};
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Random random;
	
	@Autowired
    private SupplierServiceImpl supplierService;
	
	@Autowired
	private PartServiceImpl partService;
	
	@Autowired
	private CarServiceImpl carService;
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@Autowired
	private SaleServiceImpl saleService;
	
	
	public void run(String... args) throws Exception {
//		seedSuppliers();
//		seedParts();
//		seedCars();
//		seedCustomers();
//		seedRandomSaleRecord();
//		orderedCustomers();
//		carsFromMakeToyota();
//		localSuppliers();
//		carsWithTheirListOfParts();
//		totalSalesByCustomer();
		salesWithDiscounts();
		
	}

	
//	Get all sales with discounts 
	private void salesWithDiscounts() throws JAXBException {
		List<Object[]> allSales = this.carService.getAllCarSalesInfo();
		AllDiscountedSalesInfo allDiscountedSales = new AllDiscountedSalesInfo();
		
		for(Object s[]: allSales) {
			String make = s[0].toString();
			String model = s[1].toString();
			String travelledDistance = s[2].toString();
			String customerName = s[3].toString();
			double discount = Double.valueOf(s[4].toString());
			double price = Double.valueOf(s[5].toString());
			
				if(discount > 0) {
					double priceWithDiscount = price - (price *(discount / 100d));
					CarInfo carInfo = new CarInfo();
					carInfo.setMake(make);
					carInfo.setModel(model);
					carInfo.setTravelledDistance(travelledDistance);
					SaleInfo saleInfo = new SaleInfo();
					saleInfo.setCarInfo(carInfo);
					saleInfo.setCustomerName(customerName);
					saleInfo.setDiscount(discount);
					saleInfo.setPrice(price);
					saleInfo.setPriceWithDiscount(priceWithDiscount);
					allDiscountedSales.getSales().add(saleInfo);
				}
		}
		
		Marshaller marshaller = this.getMarshaller(AllDiscountedSalesInfo.class);
		marshaller.marshal(allDiscountedSales, new File("A:\\Sales-Discounts.xml"));
		
	}



//	Get all customers that have one or more sale, get name and price
	private void totalSalesByCustomer() throws JAXBException {
		List<Object[]> allCustomers = this.customerService.getAllSalesByCustomer();
		SalesByCustomerWrapped sbcWrapped = new SalesByCustomerWrapped();
		
		for(Object cm[] : allCustomers) {
			String customerName = cm[0].toString();
			int boughtCars = Integer.valueOf(cm[1].toString());
			double spentMoney = Double.valueOf(cm[2].toString());
			SaleByCustomer sbc = new SaleByCustomer();
			sbc.setBoughtCars(boughtCars);
			sbc.setCustomerName(customerName);
			sbc.setSpentMoney(spentMoney);
			sbcWrapped.getAllSales().add(sbc);
		}
		
		Marshaller marshaller = this.getMarshaller(SalesByCustomerWrapped.class);
		marshaller.marshal(sbcWrapped, new File("A:\\allSalesByCustomer.xml"));
		
	}


//	Get all cars with their list of parts. Export to XML
	private void carsWithTheirListOfParts() throws JAXBException {
		List<Object[]> allCarsAndParts = this.carService.getAllCarsWithTheirParts();
		CarsPartsWrapper carsPartsWrapper = new CarsPartsWrapper();
		
		long carId = 0;
		CarDto carDto = null;
		for(Object car[]: allCarsAndParts) {
			long currentCarId = Long.valueOf(car[0].toString()); 
			String make = car[1].toString();
			String model = car[2].toString();
			String travelledDistance = car[3].toString();
			String partName = car[4].toString();
			double price = Double.valueOf(car[5].toString());
			
			if(currentCarId == carId) {
				PartDto partDto = new PartDto();
				partDto.setName(partName);
				partDto.setPrice(price);
				carDto.getAllParts().add(partDto);
			} else {
				carId = currentCarId;
				if(carDto != null) {
					carsPartsWrapper.getAllCars().add(carDto);
				}
				
				PartDto partDto = new PartDto();
				partDto.setName(partName);
				partDto.setPrice(price);
				carDto = new CarDto();
				carDto.setMake(make);
				carDto.setModel(model);
				carDto.setTravelledDistance(travelledDistance);
				carDto.getAllParts().add(partDto);
			}
		}
		
		Marshaller marshaller = this.getMarshaller(CarsPartsWrapper.class);
		marshaller.marshal(carsPartsWrapper, new File("A:\\AllCarsAndParts.xml"));
	}


//	Get local suppliers and number of parts they have to offer
	private void localSuppliers() throws JAXBException {
		List<Object[]> allSuppliers = this.supplierService.getLocalSuppliersAndPartCount();
		MultipleLocalSuppliersCountDto suppliers = new MultipleLocalSuppliersCountDto();
		
		for(Object sup[]: allSuppliers) {
			long id = Long.valueOf((sup[0].toString()));
			String companyName = String.valueOf(sup[1]);
			int quantity = Integer.valueOf((String) sup[2]);
			LocalSuppliersCountDto localSupplier = new LocalSuppliersCountDto();
			localSupplier.setId(id);
			localSupplier.setCompanyName(companyName);
			localSupplier.setQuantity(quantity);
			suppliers.getLocalSuppliers().add(localSupplier);
		}
		
		Marshaller marshaller = this.getMarshaller(MultipleLocalSuppliersCountDto.class);
		marshaller.marshal(suppliers, new File("A:\\LocalSuppliers.xml"));
	}



//	Get all cars make Toyota
	private void carsFromMakeToyota() throws JAXBException {
	
		ParseMultipleCarsDto pmcDto = new ParseMultipleCarsDto();
		this.carService.getAllCarsMakeToyota().forEach(car -> {
			ParseCarDto pcDto = this.modelMapper.map(car, ParseCarDto.class);
			pmcDto.getAllCars().add(pcDto);
		});		

		Marshaller marshaller = getMarshaller(ParseMultipleCarsDto.class);
		marshaller.marshal(pmcDto, new File("A:\\AllCarsToyota.xml"));
	}




//	Get customers ordered by birth date 
	private void orderedCustomers() throws JAXBException {
		
		ParseMultipleCustomersDto pmcDto = new ParseMultipleCustomersDto();
		this.customerService.getAllCustomersOrderedByBirthDate().forEach(cm -> {
			ParseCustomerDto pcDto = this.modelMapper.map(cm, ParseCustomerDto.class);
			pmcDto.getAllCustomers().add(pcDto);
		});
		
		Marshaller marshaller = this.getMarshaller(ParseMultipleCustomersDto.class);
		marshaller.marshal(pmcDto, new File("A:\\AllCustomers.xml"));
		
	}



//	Seed sales by randomly selecting Car, Customer and discount
	private void seedRandomSaleRecord() {
		Car car = this.carService.selectRandomCar().get();
		Customer customer = this.customerService.selectRandomCustomer().get();
		
		if(car != null && customer != null) {
			SaleDto saleDto = new SaleDto();
			saleDto.setCar(car);
			saleDto.setCustomer(customer);
			
			double discount = DEFAULT_DISCOUNTS[this.random.nextInt(DEFAULT_DISCOUNTS.length)];
			
				if(customer.isYoungDriver()) { // Need to add another 5% if young driver 
					discount+= 5d;
				}
				
			saleDto.setDiscount(discount);
			Sale sale = this.modelMapper.map(saleDto, Sale.class);
			this.saleService.saveSale(sale);
			ConsolePrinter.println("Sale confirmed!");
			
		} else {
			ConsolePrinter.println("Oooppss... something is messed up...");
		}
		
	}



//	Seed customers from a file on the hard disc
	private void seedCustomers() throws FileNotFoundException, JAXBException {
		String path = "A://09. XML-Processing-Exercises//customers.xml";
		BufferedReader bf = this.readFile(path);
		Unmarshaller unmarshaller = this.getUnmarshaller(ParseMultipleCustomersDto.class);
		
		ParseMultipleCustomersDto parseMultipleCustomers = (ParseMultipleCustomersDto) unmarshaller.unmarshal(bf);
		parseMultipleCustomers.getAllCustomers().forEach(cmr -> {
			Customer customer = this.modelMapper.map(cmr, Customer.class);
			this.customerService.saveCustomer(customer);
		});
		
	}


	//	Seed Cars from a file on HDD
	private void seedCars() throws FileNotFoundException, JAXBException {
		String path = "A://09. XML-Processing-Exercises//cars.xml";
		BufferedReader bf = this.readFile(path);
		Unmarshaller unmarshaller = this.getUnmarshaller(ParseMultipleCarsDto.class);
		
		ParseMultipleCarsDto parseMultipleCars = (ParseMultipleCarsDto) unmarshaller.unmarshal(bf);
		
		parseMultipleCars.getAllCars().forEach(carDto -> {
			int numberOfParts = this.random.nextInt(10) + 10;
		
				for(int i = 0; i < numberOfParts; i++) {
					Part part = this.partService.getRandomPart();
					if(!carDto.getParts().contains(part)) { // Not working. Sets throw a error for duplicate key. Need a fix. 
						carDto.getParts().add(part);
					}
				}
			
				Car finalCar = this.modelMapper.map(carDto, Car.class);
				this.carService.saveCar(finalCar);
				
		});
		
		
	}


//	Seed parts from file on HDD
	private void seedParts() throws FileNotFoundException, JAXBException {
		String path = "A://09. XML-Processing-Exercises//parts.xml";
		BufferedReader bf = this.readFile(path);
		Unmarshaller unmarshaller = this.getUnmarshaller(ParseMultiplePartsDto.class);
		
		ParseMultiplePartsDto parseMultipleParts = (ParseMultiplePartsDto) unmarshaller.unmarshal(bf);
		
		parseMultipleParts.getAllParts().forEach(part -> {
			Supplier randomSupplier = this.supplierService.getRandomSupplier();
			Part currentPart = this.modelMapper.map(part, Part.class);
			currentPart.setSupplier(randomSupplier);
			this.partService.savePart(currentPart);
		});
		
	}

//	Seeding suppliers from a file
	private void seedSuppliers() throws FileNotFoundException, JAXBException {
	
		String path = "A://09. XML-Processing-Exercises//suppliers.xml";
		BufferedReader bf = this.readFile(path);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ParseMultipleSuppliersDto.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ParseMultipleSuppliersDto parseMultipleSuppliersDtos = (ParseMultipleSuppliersDto) unmarshaller.unmarshal(bf);
		
		parseMultipleSuppliersDtos.getAllSuppliers().forEach(supplier -> {
			Supplier currentSupplier = this.modelMapper.map(supplier, Supplier.class);
			this.supplierService.saveAndFlushSupplier(currentSupplier);
		});
		
		
		
	}

//	Method returning a read File from HDD by a bf
	private BufferedReader readFile (String path) throws FileNotFoundException{
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);
		return new BufferedReader(new InputStreamReader(fileInputStream));
		
	}
	
	
//	Get Unmarshaller
	private Unmarshaller getUnmarshaller (Class clazz) throws FileNotFoundException, JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return unmarshaller;
		
	}
	
	
//  Get Marshaller
	private Marshaller getMarshaller(Class clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jaxbContext.createMarshaller();
		return marshaller;
		
	}
	
	
	
	
}
