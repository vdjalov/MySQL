package alararestaurant.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtilImpl implements FileUtil {

	@Override
	public String readFile(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		StringBuilder sBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
		String line = "";
			while((line = bufferedReader.readLine()) != null) {
				sBuilder.append(line).append(System.lineSeparator());
			}
		
		return sBuilder.toString();
	}

}
