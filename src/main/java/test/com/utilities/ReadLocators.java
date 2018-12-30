package test.com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadLocators {
	private Properties configFile = null;

	// private static ReadLocators localInstance;
	public ReadLocators(String fileName) throws Exception  {
		String className = new Object() {
		}.getClass().getName();
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String msg = "class " + className + "  ;method =" + methodName;
		TestLogger.debug("started " + msg);

		try {
			InputStream file = new FileInputStream("src/main/resources/propertiesFiles/NoInternet.properties" );			
			configFile = new Properties();
			configFile.load(file);	
			System.out.println("file is found " );
		}catch (FileNotFoundException filee) {
			TestLogger.error(" File is not found in " + msg + "\n" + filee.getMessage() + "\n\n");
		}catch (IOException ioe) {
			TestLogger.error(" IO " + msg + "\n" + ioe.getMessage() + "\n\n");
		}		catch (Exception e) {
			TestLogger.error(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
			throw new Exception(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
		}
	}// read config data

	private String getValue(String key) throws Exception {
		String className = new Object() {
		}.getClass().getName();
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String msg = "class " + className + "  ;method =" + methodName;
		TestLogger.debug("started " + msg);

		try {
			String keyValue = configFile.getProperty(key);
			if( configFile.containsKey(key) ==false){
				TestLogger.error(key + "key is not found in conf.");
			}
			if (keyValue == null) {
				TestLogger.error(key + " value for key is not found in conf.");
				throw new Exception(" value is null for key " + key);
			} else
				System.out.println("keyValue  "  + keyValue);
				return keyValue;
		} catch (Exception e) {
			TestLogger.error(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
			throw new Exception(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
		}
	}

	public String getPropertyValue(String key) throws Exception {
		// if (localInstance == null)localInstance =new ReadLocators();
//		String className = new Object() {
//		}.getClass().getName();
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		String msg = "class " + className + "  ;method =" + methodName;
//		TestLogger.debug("started " + msg);
//		try {
			
			return getValue(key).trim();
			
//		} catch (Exception e) {
//			TestLogger.error(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
//			throw new Exception(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
//		}
	}
}