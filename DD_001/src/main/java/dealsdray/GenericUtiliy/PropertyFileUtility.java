package dealsdray.GenericUtiliy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * this class contain methods related to property file
 * @author OR
 *
 */
public class PropertyFileUtility {
/**
 * this methods is use to read data from properties file
 * @param key
 * @return
 * @throws IOException
 */
	public String toReadDatafromProperty(String key) throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.property");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;

	}

}