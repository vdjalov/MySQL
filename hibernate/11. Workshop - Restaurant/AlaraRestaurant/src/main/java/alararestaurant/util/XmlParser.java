package alararestaurant.util;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public interface XmlParser {

	Unmarshaller getUnamrshaller(Class clazz) throws JAXBException;
}
