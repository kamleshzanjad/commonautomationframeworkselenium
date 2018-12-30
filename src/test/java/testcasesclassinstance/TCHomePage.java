package testcasesclassinstance;

import java.lang.reflect.Method;
import java.util.HashMap;

import test.com.framework.FrameworkCommon.FileTypes;
import test.com.framework.FrameworkCommon.ModuleName;
import test.com.utilities.ReadConfigData;
import test.com.utilities.TestDataParser;
import test.com.utilities.TestLogger;
import org.testng.annotations.Test;

import application.page.FunctionsHomePage;

import org.testng.annotations.BeforeMethod;
 
import org.testng.annotations.AfterMethod; 
import org.testng.annotations.BeforeClass;
import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass; 
import org.testng.annotations.BeforeTest; 
import org.testng.annotations.AfterTest; 
import org.testng.annotations.BeforeSuite; 
import org.testng.annotations.AfterSuite;

public class TCHomePage {
	private FunctionsHomePage homePage=null;
	
	private String testCaseName =null;
	//private TestDataParser parser= new TestDataParser();
	//private HashMap<String, String> testInput;
	
	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception{
		try{
			TestLogger.classInitialization(this.getClass().getName());
		
			//System.out.println("thread id before Class:  "  +Thread.currentThread().getId() );
			
		}catch(Exception e){
			TestLogger.error("Exception in method in TCHomePage.beforeClass  " + e.getMessage());
			throw new Exception("Exception in method in TCHomePage.beforeClass  " + e.getMessage());
		}
	}
	@AfterClass(alwaysRun=true)
	public void afterClass() throws Exception{
		try{
			TestLogger.classTermination(this.getClass().getName());
			//System.out.println("thread id after Class:  "  +Thread.currentThread().getId() );
		}catch(Exception e){
			TestLogger.error("Exception in method in TCHomePage.afterClass  " + e.getMessage());
			throw new Exception("Exception in method in TCHomePage.afterClass  " + e.getMessage());
		}
	}
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(Method _method) throws Exception{
		try{
			testCaseName=_method.getName();
			//System.out.println("thread id before method:  "  +Thread.currentThread().getId() );
			//System.out.println("  testCaseName  : "+testCaseName);
			///this.initailizeAppliction();
			TestLogger.methodInitialization(testCaseName);
			//testInput = parser.setTestCaseFileProduct(ModuleName.HomePage, FileTypes.xml, testCaseName);
			//System.out.println("emil is "+testInput.get("Email"));
			homePage= new FunctionsHomePage();
		}catch(Exception e){
			TestLogger.error("Exception in method in TCHomePage.beforeMethod  " + e.getMessage());
			throw new Exception("Exception in method in TCHomePage.beforeMethod  " + e.getMessage());
		}
	}
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult testResult) throws Exception{
		try{
			//System.out.println("thread id afterMethod:  "  +Thread.currentThread().getId() );
			TestLogger.logTestResult(testResult);
			TestLogger.methodTermination("TCHomePage.afterMethod");
			
		}catch(Exception e){
			TestLogger.error("Exception in method in TCHomePage.afterMethod  " + e.getMessage());
			throw new Exception("Exception in method in TCHomePage.afterMethod  " + e.getMessage());
		}
	}
	public void initailizeAppliction() throws Exception{
		//System.out.println("thread id initilalizApp:  "  +Thread.currentThread().getId() );
		homePage.navigateToApplication(ReadConfigData.getPropertyValue("URL"));
		//System.out.println("  linke test " + homePage.getTestOfFirstLink());
		//System.out.println("  linke test 2" + homePage.getTestOfThirdLink());
	}
	
	@Test(priority =5, enabled=true, groups={"P1","Regression"})
	public void TC_101_loginFunction(){
		try{
			
			this.initailizeAppliction();//initailizeAppliction();
			System.out.println("  linke test " + homePage.getTestOfFirstLink());
			System.out.println("  linke test 2" + homePage.getTestOfThirdLink());
			
		}catch(Exception e){
			TestLogger.error("Exception in method in TCHomePage.TC_101_loginFunction  " + e.getMessage());
			Assert.fail("Exception in method in TCHomePage.TC_101_loginFunction  " + e.getMessage());
		}
	}
	
	

}
