package com.agentaDemo.baseclass;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.agentaDemo.Util.DriverInstantiate;
import com.agentaDemo.Util.ExcelUtility;
import com.agentaDemo.config.Configuration.excelSetUp;

public class baseClass {

	public static WebDriver driver;
	private static Logger logger = Logger.getLogger(baseClass.class.getName());
	
	@BeforeClass
	public static void setObject() {
		try {
			ExcelUtility.setExcelFile(excelSetUp.excelLocation + excelSetUp.fileName, "AgentaTest");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public static void setDriverPath() {
		ChromeOptions choptions = new ChromeOptions();
		choptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		choptions.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver(choptions);
		System.out.println("Chrome driver instantiate");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
	}

	public static void openApp(WebDriver driver, String application) {
		if (driver != null) {
			driver.get(application);
		}
	}

	public static void closeBrowser() {
		driver.close();
		logger.debug("Closeing the browser");
		driver = null;
	}
	
	@AfterClass
	public static void quitBrowser(){
		
		if(driver != null){
			driver.quit();
			System.out.println("chrome driver quit");
		}
		if(DriverInstantiate.ffdriver != null){
			DriverInstantiate.ffdriver.quit();
			System.out.println("firefox driver quit");
		}
		if(DriverInstantiate.chromedriver != null){
			DriverInstantiate.chromedriver.quit();
			System.out.println("Chrome2 driver quit");
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

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(fileWithPath);

		// Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

	}
}
