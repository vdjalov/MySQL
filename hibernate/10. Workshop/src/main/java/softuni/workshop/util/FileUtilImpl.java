package softuni.workshop.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class FileUtilImpl implements FileUtil {

	@Override
	public BufferedReader returnReadXmlFile(String path) throws FileNotFoundException {
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
		return bufferedReader;
	}

	
}
