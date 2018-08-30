package com.automationsnap.Util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationsnap.pom.LoginPage;
import com.automationsnap.pom.MainPage;

public class Util  {

	private static WebDriverWait wait;
	private static JavascriptExecutor je;
	private static Logger logger = LogManager.getLogger(Util.class.getName());

	public Util(WebDriver driver) {
		wait = new WebDriverWait(driver, 40);
	}
	
	public Util() {
		
	}

	/*************************************************************
	 * Below is the syntax to check for the element presence using
	 * WebDriverWait. Here we need to pass locator and wait time as the
	 * parameters to the below method. Here it is checking that an element is
	 * present on the DOM of a page or not. That does not necessarily mean that
	 * the element is visible. ExpectedConditions will return true once the
	 * element is found in the DOM.
	 *************************************************************/
	public void isElementPresent_Wait(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		logger.debug("explicit wait ==> is Element present ");
	}

	/*************************************************************
	 * Below is the syntax for checking an element is visible and enabled such
	 * that we can click on the element. We need to pass wait time and
	 * thelocator as parameters.
	 *************************************************************/

	public void isElementClickable_Wait(WebElement locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.debug("explicit wait ==> is Element clickable ");
	}

	/*************************************************************
	 * Below is the syntax to check if the element is present on the DOM of a
	 * page and visible. Visibility means that the element is not just displayed
	 * but also should also has a height and width that is greater than 0.
	 *************************************************************/

	public void isElementVisible_Wait(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.debug("explicit wait ==> is Element Visible");
	}

	/*************************************************************
	 * Below is the syntax which is used for checking that an element is either
	 * invisible or not present on the DOM.
	 * *************************************************************/

	public void isElementInVisible_Wait(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		logger.debug("explicit wait ==> is Element in Visible");
	}

	public void isFrameToBeAvailable_Wait(String locator) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		logger.debug("explicit wait ==> is Frame To Be Available");
	}
	
	public void istextToBePresentInElement_Wait(WebElement element, String text ) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		logger.debug("explicit wait ==> is Frame To Be Available");
	}

	/*************************************************************
	 * Below is the syntax which is used to check if the element is enabled or
	 * not
	 * *************************************************************/
	public boolean isElementEnabled(WebDriver driver, WebElement element) {
		logger.debug("is Elment enable");
		return element.isEnabled();
	}

	/**********************************************************************
	 * Below is the syntax which is used to check if the element is displayed or
	 * not. It returns false when the element is not present in DOM.
	 * ********************************************************************/

	public boolean isElementDisplayed(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		logger.debug("is Elment Displayed");
		return element.isDisplayed();
	}

	/**********************************************************************
	 * Below is the syntax which is used to check if the element is Selected or
	 * not. It returns false when the element is not Selected.
	 * ********************************************************************/

	public boolean isElementSelected(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		logger.debug("is Elment Selected");
		return element.isSelected();
	}

	/*************************************************************
	 * Create method to scroll down and stop once get the locator
	 * 
	 * @param need
	 *            to set driver.
	 * @param Provide
	 *            locator till where scroll should stop scrolling
	 *************************************************************/
	public void scrollInto(WebDriver driver, WebElement element) {
		je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/*************************************************************
	 * Create method to scroll down till bottom
	 * 
	 * @param need
	 *            to set driver.
	 *************************************************************/

	public void scrollDownTillBottom(WebDriver driver) {
		je = ((JavascriptExecutor) driver);
		je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public static String getStackTrace(Throwable t) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw, true);
	    t.printStackTrace(pw);
	    pw.flush();
	    sw.flush();
	    return sw.toString();
	}
	
	public static void logout(MainPage mainPage,LoginPage loginPage, String signoutreasonUser1) throws InterruptedException {
		mainPage.signOut_Click();

		mainPage.select_SignOut_ReasonCode(signoutreasonUser1);
		
		loginPage.isUserLogOut();
	}
}
