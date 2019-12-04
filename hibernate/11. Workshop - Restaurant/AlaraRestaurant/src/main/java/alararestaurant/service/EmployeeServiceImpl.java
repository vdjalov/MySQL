package alararestaurant.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import alararestaurant.domain.dtos.JSON.EmployeeAndPositionImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final String FILE_PATH = "C:\\Users\\OK\\workspace\\AlaraRestaurant-Workshop\\AlaraRestaurant\\src\\main\\resources\\files\\employees.json";
	
	private PositionRepository positionRepository;
	private EmployeeRepository employeeRepository;
	private FileUtil fileUtilImpl;
	private ValidationUtil validationUtilImpl;
	private Gson gson;
	private ModelMapper modelMapper;
	
	@Autowired
	public EmployeeServiceImpl(PositionRepository positionRepository, EmployeeRepository employeeRepository, 
			FileUtil fileUtilImpl, ValidationUtil validationUtilImpl, Gson gson, ModelMapper modelMapper) {
			this.positionRepository = positionRepository;
			this.employeeRepository = employeeRepository;
			this.fileUtilImpl = fileUtilImpl;
			this.validationUtilImpl = validationUtilImpl;
			this.gson = gson;
			this.modelMapper = modelMapper;
	}
	
    @Override
    public Boolean employeesAreImported() {
       return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws FileNotFoundException, IOException {
    	return this.fileUtilImpl.readFile(FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) throws IOException {
    	
       EmployeeAndPositionImportDto [] allEmployees = this.gson.fromJson(employees, EmployeeAndPositionImportDto[].class);
       StringBuilder sBuilder = new StringBuilder();	
       
       		for(EmployeeAndPositionImportDto employeeDto: allEmployees) {
       			String positionName = employeeDto.getPosition();
       			Position currentPosition = this.positionRepository.findByName(positionName);
       			
       				if(currentPosition == null) {
       					currentPosition = new Position();
       					currentPosition.setName(employeeDto.getPosition());
       					if(this.validationUtilImpl.isValid(currentPosition)
       							&& this.validationUtilImpl.isValid(this.modelMapper.map(employeeDto, Employee.class))) {
       						this.positionRepository.saveAndFlush(currentPosition);
       						Employee employee = this.modelMapper.map(employeeDto, Employee.class);
       						employee.setPosition(currentPosition);
       						this.employeeRepository.saveAndFlush(employee);
       						sBuilder.append(String.format("Record %s succsessfully imported.", employeeDto.getName()))
       							    .append(System.lineSeparator());
       					} else {
       						sBuilder.append("Invalid data format!").append(System.lineSeparator());
       						
       					}
       				} else if(this.validationUtilImpl.isValid(this.modelMapper.map(employeeDto, Employee.class))) {
       					Employee employee = this.modelMapper.map(employeeDto, Employee.class);
       					employee.setPosition(currentPosition);
       					this.employeeRepository.saveAndFlush(employee);
       					sBuilder.append(String.format("Record %s succsessfully imported.", employeeDto.getName()))
						    .append(System.lineSeparator());
       				} else {
       					sBuilder.append("Invalid data format!").append(System.lineSeparator());
					}
       		}
       
        return sBuilder.toString();
    }
}











