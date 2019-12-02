package softuni.workshop.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface ProjectService {

    void importProjects() throws FileNotFoundException, JAXBException;

    boolean areImported();

    String readProjectsXmlFile() throws FileNotFoundException, JAXBException, IOException;

    String exportFinishedProjects();
}
