package test.com.framework;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;

import test.com.utilities.CaptureScreenShot;
import test.com.utilities.TestLogger;

public class WebDriverListenerTestInstance implements IInvokedMethodListener, ISuiteListener {

	//private WebDriver driver = null;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		//TestLogger.info("Inside method WebDriverListenerTestInstance.beforeInvocation method name + " );
		// Thread.currentThread().getId()

		if (method.isTestMethod()) {
			String browserName = method.getTestMethod().getXmlTest().getParameter("browserName");
			try {
				WebDriver driver_Test = WebDriverFactory.createInstance(browserName);
				//System.out.println("driver is beforeInvocation " + driver_Test);
				WebDriverManager.setDriver(driver_Test);

			} catch (Exception e) {
				TestLogger.error("Exception in WebDriverListenerTestInstance.beforeInvocation.  " + e);
			}
		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		//TestLogger.info("Inside method WebDriverListenerTestInstance.afterInvocation");

		if (method.isTestMethod()) {
			try {
			TestLogger.info("Inside method " + method.getTestMethod().getMethodName());
			WebDriver driver_Test= WebDriverManager.getDriver();
			
				if (driver_Test != null) {
					if (testResult.getStatus() == ITestResult.FAILURE) {
						CaptureScreenShot.CaptureScreenShot(driver_Test, method.getTestMethod().getMethodName());
					}
					driver_Test.quit();
				}
			} catch (Exception e) {
				TestLogger.error(
						"Exception in closing WebDriver in WebDriverListenerTestInstance.afterInvocation.  " + e);
			}
		}
	}
	
	@Override
	public void onStart(ISuite suite){		
		try{
			//TestLogger.info("Inside method WebDriverListenerTestInstance.onStart. ");
			//DBConnect.createDataBaseConnection();
			//DBConnect.openDataBaseConnection();
			
		}catch(Exception e){
			TestLogger.error(
					"Exception in creating DB connection in WebDriverListenerTestInstance.onStart.  " + e);
		}
	}
	
	@Override
	public void onFinish(ISuite suite){		
		try{
			//TestLogger.info("Inside method WebDriverListenerTestInstance.onFinish. ");
			//DBConnect.createDataBaseConnection();
			//DBConnect.closeDataBaseConnection();
			
		}catch(Exception e){
			TestLogger.error(
					"Exception in closing DB connection in WebDriverListenerTestInstance.onFinish.  " + e);
		}
		try{
			//FrameworkBase.killProcess("chromedriver");
			
		}catch(Exception e){
			TestLogger.error(
					"Exception in killing chromedriver process in WebDriverListenerTestInstance.onFinish.  " + e);
		}
	}
	
	
}//eod class
