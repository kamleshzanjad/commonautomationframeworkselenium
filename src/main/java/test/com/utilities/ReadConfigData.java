package test.com.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigData {
	private Properties configFile = null;
	private static ReadConfigData localInstance;

	private ReadConfigData() throws Exception {
//		String className=new Object(){}.getClass().getName();
//		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
//		String msg ="class " + className +"  ;method =" +methodName;
//		TestLogger.debug("started " +msg);
		String msg ="ReadConfigData";
		
		try {
			InputStream file = new FileInputStream("src/main/resources/configuration.properties");
			System.out.println(3);
			configFile = new Properties();
			configFile.load(file);
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}// read config data

	private String getValue(String key) throws Exception {
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		try {
			String keyValue = configFile.getProperty(key);
			if (keyValue == null) {
				TestLogger.error("\n\n\n " + key + "key is not found in conf.\n\n\n");
				return null;
				//throw new Exception("No key found " + key);
			} else
				return keyValue;
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + " \n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + " \n"+ e.getMessage() +"\n\n" );
		}
	}

	public static String getPropertyValue(String key) throws Exception {
		/*String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);*/
		
		//try{
		if (localInstance == null)
			localInstance = new ReadConfigData();
		return localInstance.getValue(key).trim();
		/*} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}*/
	}
}