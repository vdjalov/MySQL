package softuni.workshop.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.workshop.domain.dtos.employeeDtos.BaseEmployeeDto;
import softuni.workshop.domain.dtos.employeeDtos.EmployeesWithAgeOverTwentyFiveDto;
import softuni.workshop.domain.entities.Employee;
import softuni.workshop.repository.EmployeeRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.FileUtilImpl;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.ValidatorUtilImpl;
import softuni.workshop.util.XmlParser;
import softuni.workshop.util.XmlParserImpl;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String FILE_PATH = "C:\\Users\\OK\\workspace\\workshop-skeleton\\src\\main\\resources\\files\\xmls\\employees.xml";
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private FileUtil fileUtil;
	private ValidatorUtil validatorUtil;
	private XmlParser xmlParser;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public EmployeeServiceImpl() {
		this.fileUtil = new FileUtilImpl();
		this.validatorUtil = new ValidatorUtilImpl();
		this.xmlParser = new XmlParserImpl();
		this.modelMapper = new ModelMapper();
		
	}
	
    public void importEmployees() throws FileNotFoundException, JAXBException {
    	BufferedReader bufferedReader = fileUtil.returnReadXmlFile(FILE_PATH);
    	BaseEmployeeDto baseEmployeeDto = (BaseEmployeeDto) xmlParser.getUnmarshaller(bufferedReader, BaseEmployeeDto.class);
    		
    		baseEmployeeDto.getAllEmployees().forEach(employee -> {
    			Employee currentEmployee = this.modelMapper.map(employee, Employee.class);
    				if(this.validatorUtil.validateObject(currentEmployee)) {
    					this.employeeRepository.saveAndFlush(currentEmployee);	
    				}
    		});
    }


    public boolean areImported() {
       return this.employeeRepository.count() > 0;
    }

    public String readEmployeesXmlFile() throws IOException {
    	BufferedReader bf = this.fileUtil.returnReadXmlFile(FILE_PATH);
    	
    	StringBuilder sb = new StringBuilder();
    	String line = "";
    		
    		while((line = bf.readLine()) != null) {
    			sb.append(line).append(System.lineSeparator());
    		}
    		
        return sb.toString();
    }

    public String exportEmployeesWithAgeAbove() {
     List<Object[]> employees = this.employeeRepository.employeesOverAgeOfTwentyFive();
     
     StringBuilder sBuilder = new StringBuilder();
     	for(Object emp[] : employees) {
     		EmployeesWithAgeOverTwentyFiveDto employeesWithAgeOverTwentyFiveDto = new EmployeesWithAgeOverTwentyFiveDto();
     		employeesWithAgeOverTwentyFiveDto.setFullName(emp[0].toString());
     		employeesWithAgeOverTwentyFiveDto.setAge(emp[1].toString());
     		employeesWithAgeOverTwentyFiveDto.setProjectName(emp[2].toString());
    
     		sBuilder.append(employeesWithAgeOverTwentyFiveDto.toString()).append(System.lineSeparator()); 		
     	}
        return sBuilder.toString();
    }
    
    
    
}









