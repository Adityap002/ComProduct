package com.agentaDemo.Util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class DriverInstantiate {

	public static WebDriver chromedriver;
	public static WebDriver ffdriver;
	private static Logger logger = Logger.getLogger(DriverInstantiate.class
			.getName());

	public static void newDriver(String browser) {
		if (browser.equalsIgnoreCase("ff")) {
			FirefoxOptions ffoptions = new FirefoxOptions();
			ffoptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ffoptions.addArguments("start-maximized");
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			ffdriver = new FirefoxDriver();
			ffdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			logger.debug("firefox driver instantiate");
		}
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions choptions = new ChromeOptions();
			choptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			choptions.addArguments("start-maximized");
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			chromedriver = new ChromeDriver(choptions);
			chromedriver.manage().timeouts()
					.implicitlyWait(5, TimeUnit.SECONDS);
			logger.debug("Chrome driver instantiate");
		}
	}

}
