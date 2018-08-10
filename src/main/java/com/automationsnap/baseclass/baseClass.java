package com.automationsnap.baseclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.automationsnap.Util.DriverInstantiate;
import com.automationsnap.Util.ExcelUtility;
import com.automationsnap.config.Configuration.excelSetUp;

/*******************************************************************
 * Need to extend baseClass with all test script so test script can inherit the
 * base class and able to instantiate browser
 ********************************************************************/

public class baseClass {

	/*******************************************************************
	 * Purpose of base class All tests need a browser, so first we must write a
	 * function that will create a browser ==> setBriverPath()
	 * 
	 * 
	 * All tests need a excel object to get data set from excel file. Method
	 * name is ==> setObject
	 ********************************************************************/
	private static Logger logger = LogManager.getLogger(baseClass.class
			.getName());
	public static WebDriver driver;

	/*******************************************************************
	 * Using TestNG Framework
	 * 
	 * @BeforeClass annotation will be run before the first test method in the
	 *              current class is invoked.
	 ********************************************************************/
	@BeforeClass
	public static void setObject() {
		try {

			ExcelUtility.setExcelFile(excelSetUp.excelLocation
					+ excelSetUp.fileName, "AgentaTest");
			logger.debug("Excel File name is " + excelSetUp.excelLocation
					+ excelSetUp.fileName);
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/*******************************************************************
	 * Using TestNG Framework
	 * 
	 * @BeforeMethod annotation will be run before executing each test method.
	 *               setDriverPath method will execute the Chrome Driver
	 ********************************************************************/
	@BeforeMethod
	public static void setDriverPath() {

		/*******************************************************************
		 * create choptios variable referring ChromeOptions class. With the help
		 * of object we can set lots of capabilities based on our need.
		 * Capabilities means Browser property ==> Set Cookies, SSL
		 * Certificates, Platform, Cache etc
		 ********************************************************************/
		try {
			ChromeOptions choptions = new ChromeOptions();
			choptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// choptions.addArguments("start-maximized");
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

			/*********************************************************************
			 * Above set the Chrome driver property. We can set property
			 * directly in System Environment then no need to set above property
			 * 
			 * Below initialized the chrome driver.
			 * 
			 * and set the dimension of browser
			 **********************************************************************/
			driver = new ChromeDriver(choptions);
			Dimension d = new Dimension(0, 600);
			driver.manage().window().setSize(d);
			logger.debug("chrome driver got initialized");

			/*********************************************************************
			 * An implicit wait tells WebDriver to poll the DOM for a certain
			 * amount of time when trying to find an element or elements if they
			 * are not immediately available. I have set 5 second.
			 ********************************************************************* */
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			logger.debug("set implicit wait");
			logger.debug(driver);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void openApp(WebDriver driver, String application) {
		/*********************************************************************
		 * To check driver not null and then set URL.
		 *********************************************************************/
		try {
			if (driver != null) {
				driver.get(application);
				logger.debug("open : " + application);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void closeBrowser() {
		/*********************************************************************
		 * To close the individual active browser
		 *********************************************************************/

		try {
			driver.close();
			logger.debug("Closeing the browser");
			driver = null;
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@AfterMethod
	public static void quitBrowser() {
		/*********************************************************************
		 * To check if multiple driver is active then quit all the driver.
		 *********************************************************************/
		try {
			if (driver != null) {
				driver.quit();
				logger.debug("chrome driver quit");
			}
			if (DriverInstantiate.ffdriver != null) {
				DriverInstantiate.ffdriver.quit();
				logger.debug("firefox driver quit");
			}
			if (DriverInstantiate.chromedriver != null) {
				DriverInstantiate.chromedriver.quit();
				logger.debug("Chrome2 driver quit");
			}

			driver = null;
			DriverInstantiate.ffdriver = null;
			DriverInstantiate.chromedriver = null;
		} catch (Exception e) {
			logger.error(e);
		}

	}

	/*********************************************************************
	 * Created a method to use switchToIframe. We can create few more based on
	 * common code.
	 * 
	 *********************************************************************/
	public static void switchtoIFrame(WebElement arg) {
		driver.switchTo().frame(arg);
		logger.debug("switching window to Iframe");
	}

	/*********************************************************************
	 * below method is to take screenshot. We have to provide the active driver
	 * and path where we are going to save screenshot with screenshot name.
	 * 
	 * I have 2 approaches to achieves this.************************
	 * 
	 * 1. using screenshot class ====> This method can take full page screenshot
	 * only on Firefox browser. for other browser it wont. to overcome this
	 * problem use 2nd approach.
	 * 
	 * 2. using ashot class ===> for this we need to add dependency of ashot
	 * class.
	 * 
	 *********************************************************************/

	public static void takeSnapShot(WebDriver driver, String fileWithPath)
			throws Exception {

		try {
			// using TakeScreenshot can take snapshot from
			FileUtils
					.copyFile(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE), new File(
							fileWithPath));
			logger.debug("take screenshot for failed result");

			/* 
			 * Below code we can use when 
			 * 
			 * Screenshot screenshot = new AShot().shootingStrategy(
			 * ShootingStrategies.viewportPasting(1000))
			 * .takeScreenshot(driver);
			 * 
			 * // To save the screenshot in desired location
			 * ImageIO.write(screenshot.getImage(), "PNG", new
			 * File(fileWithPath));
			 */
		} catch (Exception e) {
			logger.error(e);
		}
	}
}

