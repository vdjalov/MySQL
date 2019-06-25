import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;



public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
		EntityManager em = emf.createEntityManager();
		
		 //removeObjects(em);
		 //containsEmployee(em);
		 //employeesWithSalaryOver50000(em);
		 //employeesFromDepartment(em);
		 //addingANewAddressAndUpdatingEmployee(em);
		 //addressesWithEmployeeCount(em);
		 //getEmployeeWithProject(em);
		 //findLatest10Projects(em);
		 //increaseSalaries(em);
		 //removeTowns(em);
		 //findEmployeesByFirstName(em);
	     //employeesMaximumSalaries(em);
	}
	
	
	// 13. Employees Maximum Salaries
	private static void employeesMaximumSalaries(EntityManager em) {
		String query = "select d.name, max(e.salary) as max_salary\n" + 
					   "from employees as e\n" + 
					   "join departments as d\n" + 
					   "on e.department_id = d.department_id\n" + 
					   "where e.salary not between 30000 and 70000\n" + 
					   "group by e.department_id;";
		
		List<Object[]> allSalariesInRange = 
				em.createNativeQuery(query).getResultList();
		  
		for(int i = 0; i < allSalariesInRange.size(); i++) {
			System.out.printf("%s - (%.2f)%n",
					allSalariesInRange.get(i)[0], allSalariesInRange.get(i)[1]);
		}
		
	}


	// 12. Find Employees by First Name
	private static void findEmployeesByFirstName(EntityManager em) throws IOException {
		System.out.println("Enter pattern");
		String pattern = bf.readLine();
		String query = "select e from Employee as e where e.firstName like '" + pattern + "%'";
		em.createQuery(query, Employee.class)
		  .getResultStream()
		  .forEach(e -> {
			  System.out.printf("%s %s - %s - ($%.2f)%n",
					  e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary());
		  });
		
	}



	// 11. Remove Towns
	private static void removeTowns(EntityManager em) throws IOException {
		System.out.println("Enter town name:");
		String townName = bf.readLine();
		String idQuery = "select t.id from Town as t where t.name = :name";
		em.getTransaction().begin();
		int townId = em.createQuery(idQuery, Integer.class)
					   .setParameter("name", townName)
					   .getSingleResult();
				
		em.getTransaction().commit();
		
		String townQuery = "select a from Address as a where a.town.id = " + townId;
		List<Address> addressesToBeDeleted = em.createQuery(townQuery, Address.class).getResultList();
		String address = "address";
		if(addressesToBeDeleted.size() > 1) {
			address = "addresses";
		}
		
		addressesToBeDeleted.forEach(a -> {
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();
		});
		System.out.printf("%d %s in %s deleted", 
				addressesToBeDeleted.size(), address, townName );
	}


	// 10. Increase Salaries
	private static void increaseSalaries(EntityManager em) {
		String query = "select e from Employee as e where e.department.id in(1, 2, 4, 11)";
		
		BigDecimal increase = new BigDecimal(1.12); 
		List<Employee> eligibleEmployees = em.createQuery(query, Employee.class).getResultList();
			
		  eligibleEmployees.forEach(e -> {
			  em.getTransaction().begin();
			  e.setSalary(e.getSalary().multiply(increase));
			  em.persist(e);
			  em.getTransaction().commit();
		  });
		
	}





	// 9. Find Latest 10 Projects
	private static void findLatest10Projects(EntityManager em) {
		String query = "select p from Project as p order by p.startDate desc";
				  em.createQuery(query,Project.class)	
					.getResultStream()
					.limit(10)
					.sorted((a,b) -> a.getName().compareTo(b.getName()))
					.forEach(p -> {
				System.out.printf("Project name: %s%n"
						+ "\tProject Description: %s%n"
						+ "\tProject Start Date:%s%n"
						+ "\tProject End Date:%s%n",
						p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate());
						
					});;
	}


	// 8. Get Employee with Project
	private static void getEmployeeWithProject(EntityManager em) throws IOException {
		String query =  "select concat(e.first_name, ' ', e.last_name) as full_name, e.job_title,\n" +
						"(select pp.name\n" + 
						"from projects as pp\n" +
						"where pp.project_id = ep.project_id\n" +
						") as `project_name`\n" +
						"from employees as e\n" + 
						"join employees_projects ep on e.employee_id = ep.employee_id\n" +
						"join projects as p on p.project_id = ep.project_id\n" +
						"where e.employee_id = :id\n" +
						"order by project_name;\n";
		
		System.out.println("Enter an employee id:");
		String id = bf.readLine();
		
		try {
			List<Object[]> idQuery = em.createNativeQuery(query, Object.class)
					   .setParameter("id",id)
					   .getResultList();
			for(int i = 0; i < idQuery.size();i++) {
			if(i == 0) {
				System.out.printf("%s - %s%n",idQuery.get(i)[0], idQuery.get(i)[1]);
			}
			System.out.printf("      %s%n",idQuery.get(i)[2]);
}
		} catch(Exception e) {
			System.out.println("Id does not exist.");
		}
		
		
	}



	// 7. Addresses with Employee Count
	private static void addressesWithEmployeeCount(EntityManager em) {
		String query = "select a.address_text, t.name,\n" + 
					   "(select count(employee_id)\n" + 
					   "from employees as ee\n" + 
					   "where ee.address_id = a.address_id\n" + 
					   ") as `number_of_employees`\n" + 
					   "from employees as e\n" +  
				       "join addresses as a\n" +  
					   "on e.address_id = a.address_id\n" + 
					   "join towns as t on a.town_id = t.town_id\n" +
					   "group by a.address_text\n" + 
					   "order by `number_of_employees` desc, a.town_id limit 10";
	
		List <Object[]> allAddresses = em.createNativeQuery(query)
		.getResultList();
		
			for(Object[] o: allAddresses) {
				System.out.printf("%s, %s - %s employees\n", o[0], o[1], o[2]);
			}	
	}


	//6. Adding a New Address and Updating Employee
	private static void addingANewAddressAndUpdatingEmployee(EntityManager em) throws IOException {
		Address address = new Address();
		address.setText("Vitoshka 15");
		em.getTransaction().begin();
		em.persist(address);
		em.getTransaction().commit();
			
		System.out.println("Please enter a last name for an employee:");
		String lastName = bf.readLine();
		Employee employee = new Employee();
		employee.setLastName(lastName);
		employee.setAddress(address);
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
	}


	// 5.	Employees from Department
	private static void employeesFromDepartment(EntityManager em) {
		String query = "select e from Employee as e where e.department = 6 order by e.salary, e.id";
		em.createQuery(query, Employee.class).getResultList()
					.forEach(e -> {
						System.out.println(e.getFirstName() + " " + e.getLastName() + 
								" from Research and Development - $" + e.getSalary());
					});
		
	}


	// 4. Employees with Salary Over 50 000
	private static void employeesWithSalaryOver50000(EntityManager em) {
		String query = "select e.firstName from Employee as e where e.salary > 50000";
		em.createQuery(query, String.class).getResultList()
										   .forEach(firstName -> {
											   System.out.println(firstName);
										   });
	}


	// 3. Contains Employee
	private static void containsEmployee(EntityManager em) throws IOException {
		System.out.println("Enter name to search for in DB:");
		String name = bf.readLine();
		String query = "select e from Employee as e where concat(e.firstName,' ', e.lastName)=:name";
		em.getTransaction().begin();
		try {
		    em.createQuery(query, Employee.class)
		      .setParameter("name", name)
		      .getSingleResult();
			System.out.println("Yes");
			em.getTransaction().commit();
		} catch(Exception e) {
			System.out.println("No");
			em.close();
		}
	
	}


	// 2. Remove Objects
	private static void removeObjects(EntityManager em) {
		String query = "select t from Town as t";
		List<Town> allTowns = em.createQuery(query, Town.class).getResultList();
		allTowns.stream().forEach(town -> {
			if(town.getName().length() < 5){
				em.detach(town);
			} else {
				em.getTransaction().begin();
				town.setName(town.getName().toLowerCase());
				em.persist(town);
				em.getTransaction().commit();
			}
		}); 
		
	}
}
































