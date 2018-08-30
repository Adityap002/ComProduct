package com.automationsnap.Util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automationsnap.baseclass.baseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ListnerClass extends baseClass implements ITestListener {

	public static ExtentReports reports;
	public static ExtentTest test;
	private static Logger logger = LogManager.getLogger(ListnerClass.class
			.getName());

	/**
	 * @see com.beust.testng.ITestListener#onTestFailure(com.beust.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult result) {

		String fileName = ".\\ScreenShot\\"
				+ result.getMethod().getMethodName()
				+ "_"
				+ new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms")
						.format(new Date()) + ".png";
		try {
			takeSnapShot(driver, fileName);
			String file = test.addScreenCapture(fileName);
			test.log(LogStatus.FAIL, result.getMethod().getMethodName()
					+ "test is failed", file);
			test.log(LogStatus.FAIL, result.getMethod().getMethodName()
					+ "test is failed", result.getThrowable().getMessage());
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}

	public void onTestStart(ITestResult result) {
		try {
			test = reports.startTest(result.getMethod().getMethodName());
			test.log(LogStatus.INFO, result.getMethod().getMethodName()
					+ "test is started");
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}

	}

	public void onTestSuccess(ITestResult result) {
		try {
			test.log(LogStatus.PASS, result.getMethod().getMethodName()
					+ "test is passed");
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}

	}

	public void onTestSkipped(ITestResult result) {
		try {
			test.log(LogStatus.SKIP, result.getMethod().getMethodName()
					+ "test is skipped");
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		try {
			logger.info("********Test Class Started**********");
			reports = new ExtentReports(
					".\\Report\\AgentsSnap"
							+ new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date())
							+ "_reports.html", false);
			reports.addSystemInfo("Selenium Version", "3.6.0").addSystemInfo(
					"Platform", "Windows-8");
			reports.loadConfig(new File(".\\src\\main\\resources\\extent-config.xml"));
		} catch (Exception e) {
			logger.info("********Test Case Started but having error**********");
			logger.error(Util.getStackTrace(e));
		}

	}

	public void onFinish(ITestContext context) {
		try {
			logger.info("********Test Class Ended**********");
			reports.endTest(test);
			reports.flush();
		} catch (Exception e) {
			logger.info("********Test Class Finished having error in Finish**********");
			logger.error(Util.getStackTrace(e));
		}
	}
}
