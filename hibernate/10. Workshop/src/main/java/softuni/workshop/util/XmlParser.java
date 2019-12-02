package softuni.workshop.util;

import java.io.BufferedReader;

import javax.xml.bind.JAXBException;



public interface XmlParser {

	public Object getUnmarshaller(BufferedReader bf, Class clazz) throws JAXBException;
}
