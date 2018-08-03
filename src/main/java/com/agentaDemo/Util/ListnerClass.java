package com.agentaDemo.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.agentaDemo.baseclass.baseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ListnerClass extends baseClass implements ITestListener {

	protected static WebDriver driver;
	protected static ExtentReports reports;
	protected static ExtentTest test;

	/**
	 * @see com.beust.testng.ITestListener#onTestFailure(com.beust.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult result) {

		String fileName = ".\\ScreenShot\\"
				+ result.getMethod().getMethodName() + "_" + new SimpleDateFormat(
						"yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + ".png";
		try {
			takeSnapShot(fileName);
			String file = test.addScreenCapture(fileName);
			test.log(LogStatus.FAIL, result.getMethod().getMethodName()
					+ "test is failed", file);
			test.log(LogStatus.FAIL, result.getMethod().getMethodName()
					+ "test is failed", result.getThrowable().getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName()
				+ "test is started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getMethod().getMethodName()
				+ "test is passed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getMethod().getMethodName()
				+ "test is skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		reports = new ExtentReports(".\\Report\\AgentsSnap" +  new SimpleDateFormat(
				"yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "_reports.html",
				false);
		reports.addSystemInfo("Selenium Version", "3.6.0").addSystemInfo(
				"Platform", "Windows-8");

	}

	@Override
	public void onFinish(ITestContext context) {
		reports.endTest(test);
		reports.flush();
	}
}
