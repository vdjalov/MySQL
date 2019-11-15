package XMLParseDemoApp.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import XMLParseDemoApp.dtos.AddressDto;
import XMLParseDemoApp.dtos.MultipleAdressesDto;
import XMLParseDemoApp.entities.Address;
import XMLParseDemoApp.entities.User;
import XMLParseDemoApp.services.AddressServiceImpl;
import XMLParseDemoApp.services.UserServiceImpl;


@Component
public class AppController implements CommandLineRunner {

	@Autowired
	private BufferedReader bufferedReader;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private AddressServiceImpl addressServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public void run(String... args) throws JAXBException, IOException {
//		addAndExportSingleUserToXML();
//		addAndExportSingleAddressToXML();
//		exportAllAdressesFromTheDbToAnXML();
//		importAnXmlFileIntoAJavaObject();
		importMultipleObjectsFromXml();
		
	}
	
	
//	Import multiple objects from xml 
	private void importMultipleObjectsFromXml() throws FileNotFoundException, JAXBException {
		// TODO Auto-generated method stub
		File file = new File("A://AllAdresses.xml");
		FileInputStream fis = new FileInputStream(file);
		BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
		
		JAXBContext jaxbContext = JAXBContext.newInstance(MultipleAdressesDto.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		MultipleAdressesDto MultipleAddressesDto =  (MultipleAdressesDto) unmarshaller.unmarshal(bf);
	
		
		System.out.println(MultipleAddressesDto.getAddresses().size());
	}



// We are going to use the addressDto class
	private void importAnXmlFileIntoAJavaObject() throws FileNotFoundException, JAXBException {
		File file = new File("A://address.xml");
		FileInputStream fis = new FileInputStream(file);
		BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
		
		JAXBContext jaxbContext = JAXBContext.newInstance(AddressDto.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		AddressDto addressDto = (AddressDto) unmarshaller.unmarshal(bf);
	
		
		System.out.println(addressDto.getCity());
	}

	//	Exporting all addresses in the db to a xml
	private void exportAllAdressesFromTheDbToAnXML() throws JAXBException {
		MultipleAdressesDto multipleAdressessDto = new MultipleAdressesDto();
		this.addressServiceImpl.getAllAddresses()
			.forEach(address -> multipleAdressessDto.getAddresses().add(address));
		
		JAXBContext context = JAXBContext.newInstance(MultipleAdressesDto.class);
		Marshaller marshaller = context.createMarshaller();
		
		marshaller.marshal(multipleAdressessDto, new File("A:\\AllAdresses.xml"));
		
	}


//	add address to db and export it to an xml
	private void addAndExportSingleAddressToXML() throws IOException, JAXBException {
		Address address = new Address();
		System.out.println("Please insert a coutry name:");	
		address.setCountry(this.bufferedReader.readLine());
		
		System.out.println("Please insert a city name:");
		address.setCity(this.bufferedReader.readLine());
		
		JAXBContext context = JAXBContext.newInstance(Address.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		OutputStream outputStream = new FileOutputStream("A:\\address.xml");
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		
		marshaller.marshal(address, bufferedWriter);
		this.addressServiceImpl.saveAddress(address);
	}



//	Adding user to db and exporting user to xml
	private void addAndExportSingleUserToXML() throws JAXBException, IOException {
		User user = new User();
		System.out.println("Please insert a first name:");
		user.setFirstName(this.bufferedReader.readLine());
		
		System.out.println("Please insert a last name:");
		user.setLastName(this.bufferedReader.readLine());
		
		System.out.println("Please insert a age:");
		user.setAge(Double.parseDouble(this.bufferedReader.readLine()));
		this.userServiceImpl.saveUser(user);
		
		JAXBContext context = JAXBContext.newInstance(User.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(user, new File("A:\\users.xml"));
		
		
	}

}
