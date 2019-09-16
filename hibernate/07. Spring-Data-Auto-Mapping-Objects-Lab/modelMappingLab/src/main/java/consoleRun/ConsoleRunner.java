package consoleRun;


import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import DTOentities.EmployeeDTO;
import DTOentities.EmployeeDTOProjection;
import DTOentities.ManagerDTO;
import entities.Address;
import entities.Employee;
import entities.Manager;
import repositories.AddressRepository;
import repositories.EmployeeRepository;
import repositories.ManagerRepository;


@Component
public class ConsoleRunner implements CommandLineRunner {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public void run(String... args) throws Exception {
		ModelMapper employeeDtoMapper = new ModelMapper();
		ModelMapper managerDtoMapper = new ModelMapper();
		
		
		Manager manager = new Manager();
		manager.setFirstName("No");
		manager.setLastName("Body");
		this.managerRepository.saveAndFlush(manager);
		
		Manager manager1 = new Manager();
		manager1.setFirstName("Jo");
		manager1.setLastName("Biden");
		this.managerRepository.saveAndFlush(manager1);
		
		
		Employee emp = new Employee();	
		emp.setBirthday(new Date());
		emp.setFirst_name("Vlad");
		emp.setLastName("Mad");
		emp.setManager(manager);
		emp.setOnHoliday(true);
		emp.setSalary(0.00);
		
		Employee emp3 = new Employee();	
		emp3.setBirthday(new Date());
		emp3.setFirst_name("Nina");
		emp3.setLastName("Crazy");
		emp3.setManager(manager);
		emp3.setOnHoliday(true);
		emp3.setSalary(0.00);
		
		Employee emp1 = new Employee();	
		emp1.setBirthday(new Date());
		emp1.setFirst_name("Sis");
		emp1.setLastName("Mad");
		emp1.setManager(manager);
		emp1.setOnHoliday(true);
		emp1.setSalary(0.00);
		
		Employee emp2 = new Employee();	
		emp2.setBirthday(new Date());
		emp2.setFirst_name("Joro");
		emp2.setLastName("Trabanta");
		emp2.setManager(manager1);
		emp2.setOnHoliday(true);
		emp2.setSalary(0.00);
		
		Address address = new Address();
		address.setNumber(30);
		address.setStreet("Elgin Rd");
		address.setEmployee(emp);
	
		Set<Address> allAddresses = new HashSet<Address>();
		allAddresses.add(address);
		emp.setAddress(allAddresses);
		
//		this.employeeRepository.saveAndFlush(emp);
//		this.employeeRepository.saveAndFlush(emp1);
//		this.employeeRepository.saveAndFlush(emp2);
//		this.employeeRepository.saveAndFlush(emp3);
//		this.addressRepository.saveAndFlush(address);
		
		
		// 1.	Simple Mapping
		EmployeeDTO empDTO = employeeDtoMapper.map(emp, EmployeeDTO.class);	
		System.out.println(empDTO);
		
		
		// 2.	Advanced Mapping
		Map<Long, ManagerDTO> employeesByManager 
				= new LinkedHashMap<Long,ManagerDTO>();
		
			
			this.employeeRepository.findAll()
				.forEach(e -> {
					long i = e.getManager().getId();
					if(!employeesByManager.containsKey(i)) {
						ManagerDTO managerDto = managerDtoMapper.map(e, ManagerDTO.class);
						EmployeeDTO employeeDto = employeeDtoMapper.map(e, EmployeeDTO.class);
						managerDto.getEmployees().add(employeeDto);
						employeesByManager.put(i, managerDto);
					} else {
						EmployeeDTO employeeDTO = employeeDtoMapper.map(e, EmployeeDTO.class);
						employeesByManager.get(i).getEmployees().add(employeeDTO);
					}
				});
			
			employeesByManager.entrySet().stream()
				.forEach(m -> {
					// Print managers
					System.out.println(m.getValue().getFirstName() + " " + m.getValue().getLastName() + " | Employees: " + m.getValue().getEmployees().size());
						m.getValue().getEmployees()
							.forEach(e -> {
					System.out.println("    - " + e.getFirstName() + " " + e.getLastName() + " " + e.getSalary());
							});
				});
			
			
		// 	3.	Projection
			ModelMapper modelMapperProjection = new ModelMapper();
			this.employeeRepository.findAllEmployeesBornBefore1990()
				.forEach(e -> {
				
					EmployeeDTOProjection employeeDTOProjection = modelMapperProjection.map(e, EmployeeDTOProjection.class);
					System.out.println(employeeDTOProjection);
				});
			
			
	}




















}
