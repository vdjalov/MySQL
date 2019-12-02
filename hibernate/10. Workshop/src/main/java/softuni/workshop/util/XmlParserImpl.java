package softuni.workshop.util;

import java.io.BufferedReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlParserImpl implements XmlParser {

	@Override
	public Object getUnmarshaller(BufferedReader bf, Class clazz) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return unmarshaller.unmarshal(bf);
	}
}
