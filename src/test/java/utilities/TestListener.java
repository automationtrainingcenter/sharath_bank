package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TestListener extends GenericHelper implements ISuiteListener, ITestListener{

	//suite tag started
	@Override
	public void onStart(ISuite suite) {
		report = new ExtentReports(getFilePath("reports", "report.html"));
	}

	//suite tag ended
	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		report.close();
	}

	//@test method started
	@Override
	public void onTestStart(ITestResult result) {
		test = report.startTest(result.getName());
	}

	//@Test method passed
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "test passed");
		report.endTest(test);
	}

	//@Test method failed
	@Override
	public void onTestFailure(ITestResult result) {
		Object currentClass = result.getInstance();
		WebDriver driver = ((BrowserHelper) currentClass).getDriver();
		test.log(LogStatus.FAIL, "test failed");
		test.log(LogStatus.INFO, "screenshot added "+test.addScreenCapture(ScreenshotHelper.captureScreenShot(driver, "screenshots", result.getName())));
		report.endTest(test);
	}

	//@Test method skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "test skipped");
		report.endTest(test);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	//test tag started
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	//test tag ended
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
