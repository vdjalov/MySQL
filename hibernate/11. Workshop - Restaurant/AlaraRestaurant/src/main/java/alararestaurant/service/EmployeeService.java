package alararestaurant.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface EmployeeService {

    Boolean employeesAreImported();

    String readEmployeesJsonFile() throws FileNotFoundException, IOException;

    String importEmployees(String employees) throws IOException;
}
