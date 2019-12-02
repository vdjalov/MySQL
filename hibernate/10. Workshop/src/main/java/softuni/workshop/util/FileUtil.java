package softuni.workshop.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public interface FileUtil {

  public BufferedReader returnReadXmlFile(String path) throws FileNotFoundException;
  
  
}
