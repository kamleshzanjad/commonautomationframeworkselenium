package test.com.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import test.com.utilities.ReadConfigData;
import test.com.utilities.TestLogger;

public class FrameworkBase {
	protected static final int ONE_SECOND = 1;
	public static WebDriver dr= null;
	
	public static void setDriver() throws Exception {
		//TestLogger.debug("Inside method -FrameworkBase.setDriver ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		try {
			dr= WebDriverManager.getDriver();
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}


	public static WebDriver getDriver() throws Exception {
		//TestLogger.debug("Inside method -FrameworkBase.getDriver ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		try {
			//System.out.println("driver is getDriver fbbase " + WebDriverManager.getDriver());
			return WebDriverManager.getDriver();
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}

	protected void clickWebElement(String locator) throws Exception {
		//TestLogger.debug("Inside method -FrameworkBase.clickWebElement ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		WebElement element = null;
		try {
			element = locateElement(locator);
			if (element != null) {
				element.click();
			} else {
				TestLogger.error("Element is null for locator:  " + locator);
				throw new Exception("Element is null for locator:  " + locator);
			}
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}
	
	protected void doubleClickWebElement(String locator) throws Exception {
		//TestLogger.debug("Inside method -FrameworkBase.doubleClickWebElement ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		WebElement element = null;
		try {
			element = locateElement(locator);
			
			if (element != null) {
				Actions act= new Actions(dr);
				act.moveToElement(element).doubleClick().perform();
				
			} else {
				TestLogger.error("Element is null for locator:  " + locator);
				throw new Exception("Element is null for locator:  " + locator);
			}
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}
	
	protected void rightClickWebElement(String locator) throws Exception {
		//TestLogger.debug("Inside method -FrameworkBase.rightClickWebElement ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		WebElement element = null;
		try {
			element = locateElement(locator);
			if (element != null) {
				Actions act= new Actions(dr);
				act.moveToElement(element).contextClick().perform();
			} else {
				TestLogger.error("Element is null for locator:  " + locator);
				throw new Exception("Element is null for locator:  " + locator);
			}
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}

	private By getLocatorString(String locator) throws Exception {
		//TestLogger.debug("Inside method -FrameworkBase.getLocatorString ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		try {
			String separator = "~";
			String array[] = locator.split(separator, 2);
			By by = null;
			switch (array[0]) {
			case "xpath":
				by = By.xpath(array[1]);
				break;
			case "css":
				by = By.cssSelector(array[1]);
				break;
			default:
				by = By.xpath(array[1]);
			}
			return by;
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}

	protected WebElement locateElement(String locator) throws Exception {
		//TestLogger.debug("Inside method -FrameworkBase.locateElement ");
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		try {
			WebElement element = null;
			By by = this.getLocatorString(locator);
			String errMessage = null;
			int numberOfAttempts = Integer
					.parseInt(ReadConfigData.getPropertyValue("WEBDRIVER_EXPLICIT_WAIT_TIME_IN_SECOND"));
			for(int i=0; i<numberOfAttempts; i++){
			try {
               element = getDriver().findElement(by);
				//element = dr.findElement(by);
               if(element!=null){
            	   return element;
               }
			} catch (StaleElementReferenceException se) {
			} catch (NoSuchElementException ne) {
			}
			}
			return element;

		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}




protected String getText(String locator) throws Exception {
	//TestLogger.debug("Inside method -FrameworkBase.getText ");
	String className=new Object(){}.getClass().getName();
	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	String msg ="class " + className +"  ;method =" +methodName;
	TestLogger.debug("started " +msg);
	
	WebElement element = null;
	try {
		element = locateElement(locator);
		if (element != null) {
			return element.getText();
		} else {
			TestLogger.error("Element is null for locator:  " + locator);
			throw new Exception("Element is null for locator:  " + locator);
		}
	} catch (Exception e) {
		TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
	}
}
protected void setText(String locator, String value) throws Exception {
	//TestLogger.debug("Inside method -FrameworkBase.setText ");
	
	String className=new Object(){}.getClass().getName();
	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	String msg ="class " + className +"  ;method =" +methodName;
	TestLogger.debug("started " +msg);
	
	
	WebElement element = null;
	try {
		element = locateElement(locator);
		if (element != null) {
			 element.sendKeys(value);
		} else {
			TestLogger.error("Element is null for locator:  " + locator);
			throw new Exception("Element is null for locator:  " + locator);
		}
	} catch (Exception e) {
		TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
	}
}

protected String getAttributeValue(String locator, String attributeValue) throws Exception {
	//TestLogger.debug("Inside method -FrameworkBase.getAttributeValue ");
	
	String className=new Object(){}.getClass().getName();
	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	String msg ="class " + className +"  ;method =" +methodName;
	TestLogger.debug("started " +msg);
	
	WebElement element = null;
	try {
		element = locateElement(locator);
		if (element != null) {
			return element.getAttribute(attributeValue);
		} else {
			TestLogger.error("Element is null for locator:  " + locator);
			throw new Exception("Element is null for locator:  " + locator);
		}
	} catch (Exception e) {
		TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
	}
}


}
