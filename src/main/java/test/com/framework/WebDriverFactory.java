package test.com.framework;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import test.com.utilities.ReadConfigData;
import test.com.utilities.TestLogger;

public class WebDriverFactory {
	static File resourceDirectory = new File("src/main/resources");
	static final String DOWNLOAD_FILE_PATH = resourceDirectory.getAbsolutePath() + "downloadfile";

	static WebDriver createInstance(String browserName) throws Exception{
		TestLogger.info("Inside method: WebDriverFactory.createInstance");
		/*String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);*/
		String msg= "WebDriverFactory.createInstance";
		
		try{
			WebDriver driver =null;
			switch(browserName){
			case "chrome" :
				driver= initializeChromeDriver();
				break;				
			case "ie" :
				driver= initializeIEDriver();
				break;				
			case "phantom" :
				driver= initializePhantomJSDriver();
				break;				
			 default :
				driver= initializeChromeDriver();				
			}
			
			driver.manage().deleteAllCookies();
			return driver;
		}catch(Exception e){
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
		
		
	}

	private static WebDriver initializeChromeDriver() throws Exception {
		TestLogger.info("Inside method: WebDriverFactory.initializeChromeDriver");
		/*String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);*/
		
		String msg= "WebDriverFactory.initializeChromeDriver";
		
		
		try {
			System.out.println("1");
			System.out.println("path " + ReadConfigData.getPropertyValue("CHROME_DRIVER_PATH"));
			System.setProperty("webdriver.chrome.driver", ReadConfigData.getPropertyValue("CHROME_DRIVER_PATH"));
			HashMap<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", "false");
			chromePrefs.put("download.default_directory", DOWNLOAD_FILE_PATH);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("prefs", chromePrefs);

			options.addArguments("disable-infobars");
			options.addArguments("start-maximized");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("--enable-automation");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			System.out.println("driver is " );
			return new ChromeDriver(cap);
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}

	private static WebDriver initializeIEDriver() throws Exception {
	TestLogger.info("Inside method: WebDriverFactory.initializeIEDriver");
		/*String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);*/
		
		String msg= "WebDriverFactory.initializeIEDriver";
		
		try {
			System.setProperty("webdriver.ie.driver", ReadConfigData.getPropertyValue("IE_DRIVER_PATH"));
			HashMap<String, Object> chromePrefs = new HashMap<>();

			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//cap.setCapability("InternetExplorerDriver - INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);

			cap.setCapability("ignoreProtectedModeSettings", true);
			cap.setCapability("requireWindowFocus", true);
			cap.setCapability("enablePersistentHover", false);
			cap.setJavascriptEnabled(true);

			return new InternetExplorerDriver(cap);
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}

	private static WebDriver initializePhantomJSDriver() throws Exception {
		TestLogger.info("Inside method: WebDriverFactory.initializePhantomJSDriver");
		/*String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);*/
		
		String msg= "WebDriverFactory.initializePhantomJSDriver";
		
		try {

			String path = ReadConfigData.getPropertyValue("PHANTOMGHOST_DRIVERPATH");
			File src = new File(path);

			System.setProperty("phantomjs.binary.path", src.getAbsolutePath());

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setJavascriptEnabled(true);

			cap.setCapability("takeScreenshot", true);
			cap.setCapability("PhantomJSDRIVERSERVICE_PHANTOMJS_EXECUTABLE_PATH_PROPERTY", path);
			cap.setCapability("PhantomJSDRIVERSERVICE_PHANTOMJS_cli_args",
					new String[] { "--webdriver-loglevle =NONE" });

			return new PhantomJSDriver(cap);
		} catch (Exception e) {
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}

}
