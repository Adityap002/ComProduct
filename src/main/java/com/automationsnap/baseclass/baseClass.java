package com.automationsnap.baseclass;

import java.io.File;
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

public class baseClass {
	private static Logger logger = LogManager.getLogger(baseClass.class.getName());
	public static WebDriver driver;

	@BeforeClass
	public static void setObject() {
		try {
			
			ExcelUtility.setExcelFile(excelSetUp.excelLocation
					+ excelSetUp.fileName, "AgentaTest");
			logger.debug("Excel File name is " + excelSetUp.excelLocation
					+ excelSetUp.fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public static void setDriverPath() {
		ChromeOptions choptions = new ChromeOptions();
		choptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//choptions.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver(choptions);
		Dimension d = new Dimension(0,600);
		driver.manage().window().setSize(d);
		logger.debug("chrome driver got initialized");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.debug("set implicit wait");
		logger.debug(driver);

	}

	public static void openApp(WebDriver driver, String application) {
		if (driver != null) {
			driver.get(application);
			logger.debug("open : " + application);
		}
	}

	public static void closeBrowser() {
		driver.close();
		logger.debug("Closeing the browser");
		driver = null;
	}

	@AfterMethod
	public static void quitBrowser() {

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

	}

	public static void switchtoIFrame(WebElement arg) {
		driver.switchTo().frame(arg);
		logger.debug("switching window to Iframe");
	}

	public static void takeSnapShot(WebDriver driver, String fileWithPath)
			throws Exception {

		// using TakeScreenshot can take snapshot from
		FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File(fileWithPath));
		logger.debug("take screenshot for failed result");

		/*Screenshot screenshot = new AShot().shootingStrategy(
				ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		// To save the screenshot in desired location
		ImageIO.write(screenshot.getImage(), "PNG", new File(fileWithPath));*/

	}
}
