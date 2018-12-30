package test.com.utilities;

import java.io.File;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
/*//import java.testng.IReporter;
//import java.testng.IResultMap;
//import java.testng.ISuite;
//import java.testng.ISuiteResult;
//import java.testng.ITestContext;
//import java.testng.ITestResult;
//import java.testng.xml.XmlSuite;
//
//import com.relvantcodes.extentreports.ExtentTest;
//import com.relvantcodes.extentreports.LogStatus;
*/import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.IReporter;
import org.testng.IResultMap;

public class ReportListener implements IReporter {
	private ExtentReports extent;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		String className = new Object() {
		}.getClass().getName();
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String msg = "class " + className + "  ;method =" + methodName;
		TestLogger.debug("started " + msg);

		// @Override
		try {
			TestLogger.debug("Inside method ReportListener.generateReport");
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
			String strDate = formatter.format(date);
			strDate = strDate.replace("/", "_").replace(":", "_").replace("%", "_").replace(".", "_");
			strDate = "Date" + strDate;
			extent = new ExtentReports(ReadConfigData.getPropertyValue("TEST_RESULTS_REPORT_PATH") + File.separator
					+ ReadConfigData.getPropertyValue("TEST_RESULTS_REPORT_NAME") + strDate + ".html", true);
			extent.loadConfig(new File(ReadConfigData.getPropertyValue("TEST_RESULTS_REPORT_CONFIG_FILE_PATH")));

			for (org.testng.ISuite suite : suites) {
				Map<String, ISuiteResult> result = suite.getResults();
				for (ISuiteResult r : result.values()) {
					ITestContext context = r.getTestContext();
					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
					buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				}
			}
		} catch (Exception e) {
			TestLogger.error(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
			// throw new Exception(" \nException in " +msg + "\n"+
			// e.getMessage() +"\n\n" );
		} finally {
			extent.flush();
			extent.close();
		}
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) throws Exception{
		String className = new Object() {
		}.getClass().getName();
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String msg = "class " + className + "  ;method =" + methodName;
		TestLogger.debug("started " + msg);

		try {
			ExtentTest test;
			if (tests.size() > 0) {
				for (ITestResult result : tests.getAllResults()) {
					test = extent.startTest(result.getMethod().getMethodName());
					test.getTest().setStartedTime(getTime(result.getStartMillis()));
					test.getTest().setEndedTime(getTime(result.getEndMillis()));

					for (String group : result.getMethod().getGroups()) {
						test.assignCategory(group);
						String message = "Test" + status.toString().toLowerCase() + "ed";
						if (result.getThrowable() != null) {
							message = result.getThrowable().getMessage();
						}
						test.log(status, message);
						extent.endTest(test);
					}
				}
			}
		} catch (Exception e) {
			TestLogger.error(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
			throw new Exception(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
		}
	}

	private Date getTime(long millis) throws Exception{
		String className = new Object() {
		}.getClass().getName();
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String msg = "class " + className + "  ;method =" + methodName;
		TestLogger.debug("started " + msg);

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			return calendar.getTime();
		} catch (Exception e) {
			TestLogger.error(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
			throw new Exception(" \nException in " + msg + "\n" + e.getMessage() + "\n\n");
		}
	}

}
