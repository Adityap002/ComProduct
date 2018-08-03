package com.agentaDemo.Util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	private static WebDriverWait wait;
	private static JavascriptExecutor je;
	private static Logger logger = Logger.getLogger(Util.class
			.getName());

	// Instantiate wait object
	private static void instantiateWaitObject(WebDriver driver) {
		wait = new WebDriverWait(driver, 40);
		logger.debug("set the explicit time as 40 sec in wait object");
	}

	// Below is the syntax to check for the element presence using
	// WebDriverWait. Here we need to pass locator and wait time as the
	// parameters to the below method. Here it is checking that an element is
	// present on the DOM of a page or not. That does not necessarily mean that
	// the element is visible. ExpectedConditions will return true once the
	// element is found in the DOM.
	public void isElementPresent_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		logger.debug("explicit wait ==> is Element present ");
	}

	// Below is the syntax for checking an element is visible and enabled such
	// that we can click on the element. We need to pass wait time and the
	// locator as parameters.
	public void isElementClickable_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.debug("explicit wait ==> is Element clickable ");
	}

	// Below is the syntax to check if the element is present on the DOM of a
	// page and visible. Visibility means that the element is not just displayed
	// but also should also has a height and width that is greater than 0.
	public void isElementVisible_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.debug("explicit wait ==> is Element Visible");
	}

	// Below is the syntax which is used for checking that an element is either
	// invisible or not present on the DOM.
	public void isElementInVisible_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		logger.debug("explicit wait ==> is Element in Visible");
	}

	public void isFrameToBeAvailable_Wait(WebDriver driver, String locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		logger.debug("explicit wait ==> is Frame To Be Available");
	}

	// Below is the syntax which is used to check if the element is enabled or
	// not
	public boolean isElementEnabled(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		logger.debug("is Elment enable");
		return element.isEnabled();
	}

	// Below is the syntax which is used to check if the element is displayed or
	// not. It returns false when the element is not present in DOM.
	public boolean isElementDisplayed(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		logger.debug("is Elment Displayed");
		return element.isDisplayed();
	}

	// Below is the syntax which is used to check if the element is Selected or
	// not. It returns false when the element is not Selected.
	public boolean isElementSelected(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		logger.debug("is Elment Selected");
		return element.isSelected();
	}
	
	public void scrollInto(WebDriver driver, WebElement element){
		je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void scrollDownTillBottom(WebDriver driver){
		je = ((JavascriptExecutor) driver);
		je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
}
