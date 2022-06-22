package utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseclass.BaseClass;

public class Listeners extends BaseClass implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReport(); // static class inside utilities/ExtentReportNG
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Pass");
		
	}

	public void onTestFailure(ITestResult result) {
	extentTest.get().fail(result.getThrowable());
	String faileMethod=result.getMethod().getMethodName();
	try {
		extentTest.get().addScreenCaptureFromPath(takeScreenShot(faileMethod), result.getMethod().getMethodName());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//extentTest.get().addScreenCaptureFromPath(takeScreenShot(faileMethod), result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
