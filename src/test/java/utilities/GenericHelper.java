package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class GenericHelper {
	
	static ExtentReports report;
	static ExtentTest test;
	
	/*
	 * this method returns absolute path of the file you specified in the folder name we specified
	 * in project root folder
	 */
	public static String getFilePath(String folderName, String fileName) {
		return System.getProperty("user.dir")+File.separator+folderName+File.separator+fileName;
	}


	public String readProperty(String properyName) {
		String propertyValue = null;
		try {
			FileInputStream fis = new FileInputStream(getFilePath("resources", "config.properties"));
			Properties prop = new Properties();
			prop.load(fis);
			propertyValue =  prop.getProperty(properyName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return propertyValue;
	}

}
