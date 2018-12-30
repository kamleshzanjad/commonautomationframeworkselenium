package test.com.framework;

import org.openqa.selenium.WebDriver;

import test.com.utilities.TestLogger;

public class FrameworkCommon extends FrameworkBase {
  public enum ModuleName{HomePage};
  public enum FileTypes{xml, xlsx};
  
  public void navigateToApplication(String url) throws Exception{
	  //TestLogger.debug("Inside method in FrameworkCommon.navigateToApplication  " );
	  String className=new Object(){}.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg ="class " + className +"  ;method =" +methodName;
		TestLogger.debug("started " +msg);
	  
	  try{
		  WebDriver d= FrameworkBase.getDriver();
		  //System.out.println("driver is navigateToApplication " + d);
			if(d==null){
				throw new Exception("Driver is null  " );
			}
			d.get(url);
			System.out.println("current url is navigateToApplication " + d.getCurrentUrl());
		}catch(Exception e){
			TestLogger.error(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
			throw new Exception(" \nException in " +msg + "\n"+ e.getMessage() +"\n\n" );
		}
  }
  
}
