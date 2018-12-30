package test.com.framework;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;

import test.com.utilities.CaptureScreenShot;
import test.com.utilities.TestLogger;

public class WebDriverListenerClassInstance implements IInvokedMethodListener, ISuiteListener {

	private WebDriver driver = null;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		//TestLogger.info("Inside method WebDriverListenerClassInstance.beforeInvocation");
		// Thread.currentThread().getId()

		if (method.getTestMethod().isBeforeClassConfiguration()) {
			String browserName = method.getTestMethod().getTestClass().getXmlTest().getParameter("browserName");
			try {
				driver = WebDriverFactory.createInstance(browserName);
				//System.out.println("driver is " + driver);
				WebDriverManager.setDriver(driver);

			} catch (Exception e) {
				TestLogger.error("Exception in WebDriverListenerClassInstance.beforeInvocation.  " + e);
			}
		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		//TestLogger.info("Inside method WebDriverListenerClassInstance.afterInvocation");
		try {
			if (driver != null) {
				if (testResult.getStatus() == ITestResult.FAILURE) {
					CaptureScreenShot.CaptureScreenShot(driver, method.getTestMethod().getMethodName());
				}
			}

		} catch (Exception e) {
			TestLogger
					.error("Exception in cathing screenshot in WebDriverListenerClassInstance.afterInvocation.  " + e);
		}
		if (method.getTestMethod().isAfterClassConfiguration()) {
			try {
				if (driver != null) {
					driver.quit();
				}
			} catch (Exception e) {
				TestLogger.error(
						"Exception in closing WebDriver in WebDriverListenerClassInstance.afterInvocation.  " + e);
			}
		}
	}
	
	@Override
	public void onStart(ISuite suite){		
		try{
			TestLogger.info("Inside method WebDriverListenerClassInstance.onStart. ");
			//DBConnect.createDataBaseConnection();
			//DBConnect.openDataBaseConnection();
			
		}catch(Exception e){
			TestLogger.error(
					"Exception in creating DB connection in WebDriverListenerClassInstance.onStart.  " + e);
		}
	}
	
	@Override
	public void onFinish(ISuite suite){		
		try{
			TestLogger.info("Inside method WebDriverListenerClassInstance.onFinish. ");
			//DBConnect.createDataBaseConnection();
			//DBConnect.closeDataBaseConnection();
			
		}catch(Exception e){
			TestLogger.error(
					"Exception in closing DB connection in WebDriverListenerClassInstance.onFinish.  " + e);
		}
		try{
			//FrameworkBase.killProcess("chromedriver");
			
		}catch(Exception e){
			TestLogger.error(
					"Exception in killing chromedriver process in WebDriverListenerClassInstance.onFinish.  " + e);
		}
	}
	
	
}//eod class
