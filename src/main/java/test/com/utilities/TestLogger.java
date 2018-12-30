package test.com.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.testng.ITestResult;

public class TestLogger {
	static{
		System.setProperty("logfilename", "Log_"+(new SimpleDateFormat("dd-MMM-yyy HH mm ss").format(new Date()))	);
	}
	
	//private static final Logger log= Logger.getLogger(new Throwable.getStackTrace()[1].getClassName());
	private static final Logger log= Logger.getLogger(new Throwable().getStackTrace()[0].getClassName());
	public static void debug(String message){log.debug(message);}
	public static void info(String message){log.info(message);}
	public static void error(String message){log.error(message);}
	public static void error(String message, Exception e ){log.error(message, e);}
	
	public static void classInitialization(String message ){
		log.info("\n Test script execution of class: '" + message +"' has started");
	}
	public static void classTermination(String message ){
		log.info("\n Test script execution of class: '" + message +"' has ended");
	}
	
	public static void methodInitialization(String message ){
		log.info("\n Test script execution of method: '" + message +"' has started");
	}
	public static void methodTermination(String message ){
		log.info("\n Test script execution of method: '" + message +"' has ended");
	}
	
	public static void logTestResult(ITestResult result){
		String status =null;
		switch(result.getStatus()){
		case ITestResult.SUCCESS:
			status="Passed";
			break;
		case ITestResult.FAILURE:
			status="Failed";
			break;
		case ITestResult.SKIP:
			status="Skipped";
			break;
		default:
			break;
			
		}
		log.info(" Test Run status is :" + status);
	}
	
	

}
