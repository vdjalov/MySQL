package softuni.workshop.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface CompanyService {

    void importCompanies() throws FileNotFoundException, JAXBException;

    boolean areImported();

    String readCompaniesXmlFile() throws FileNotFoundException, IOException;
}
