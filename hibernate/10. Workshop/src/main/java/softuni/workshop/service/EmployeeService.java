package softuni.workshop.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface EmployeeService {

    void importEmployees() throws FileNotFoundException, JAXBException;

    boolean areImported();

    String readEmployeesXmlFile() throws FileNotFoundException, IOException;

    String exportEmployeesWithAgeAbove();
}
