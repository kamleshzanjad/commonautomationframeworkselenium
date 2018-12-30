package test.com.framework;

import org.openqa.selenium.WebDriver;

import test.com.utilities.TestLogger;

public class WebDriverManager {
	
	private static ThreadLocal<WebDriver> webDriver =new ThreadLocal<WebDriver>();
	public static WebDriver getDriver(){
		//TestLogger.info("Inside Method: WebDriverManager.getDriver ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		TestLogger.debug("Inside class =" + className +"  ;method =" +methodName);
		///System.out.println("driver is navigateToApplication " + webDriver.get());
		return webDriver.get();		
	}
	
	public static void setDriver(WebDriver driver){
		//TestLogger.info("Inside Method: WebDriverManager.setDriver ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		TestLogger.debug("Inside class =" + className +"  ;method =" +methodName);
		webDriver.set(driver);
		
	}
	

}
