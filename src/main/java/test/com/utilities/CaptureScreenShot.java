package test.com.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.TakesScreenshot;

public class CaptureScreenShot {
	public static  void CaptureScreenShot(WebDriver driver, String testcaseName) throws Exception {
		//TestLogger.info("Inside                   " );
		String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
		
		try{
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File trg=new File(ReadConfigData.getPropertyValue("SCREENSHOTS_FILE_DIRECTORY_PATH").trim()+"SS_" 
			+ testcaseName +"_"+ System.currentTimeMillis()
			+ 	ReadConfigData.getPropertyValue("SCREENSHOTS_FILE_FORMAT").trim()
			);
					FileUtils.copyFile(src,trg);
		
		}catch(IOException e){
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
	}

}
