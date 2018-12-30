package application.page;

import test.com.framework.FrameworkCommon;
import test.com.utilities.ReadLocators;
import test.com.utilities.TestLogger;

public class FunctionsHomePage extends FrameworkCommon {
	private  String locatorFile ="NoInternet.properties";
	public ReadLocators file;
	
	public FunctionsHomePage() throws Exception{
		//file = new ReadLocators(locatorFile);
	}
//	LABEL_NOINTERNAT =xpath~//span[@jsselect='heading']  #heading
//			DIAGNOSELINK=xpath~//a[@id='diagnose-link']  #color
//			LIST_HEADING=xpath~//li
	
	public String getTestOfFirstLink() throws Exception{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		TestLogger.info("Inside class.method: " + this.getClass().getName() +". " + methodName );
		
		String locator= LocatorsHomePage.LABEL_NOINTERNAT;      ///file.getPropertyValue("LABEL_NOINTERNAT");
		//String locator= file.getPropertyValue("LABEL_NOINTERNAT");
		System.out.println("locator is " + locator);
		return this.getText(locator);
	}
	
	public String getTestOfThirdLink() throws Exception{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		TestLogger.info("Inside class.method: " + this.getClass().getName() +". " + methodName );
		
		String locator = LocatorsHomePage.DIAGNOSELINK;   ;///file.getPropertyValue("DIAGNOSELINK");
		
		//String locator = file.getPropertyValue("DIAGNOSELINK");
		return this.getText(locator);
	}

}
