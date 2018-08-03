package com.agentaDemo.pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DialPadPage {
	
	private static Logger logger = Logger.getLogger(DialPadPage.class.getName());
	
	public WebDriver driver = null;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'1')]")
	private WebElement one;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'2')]")
	private WebElement two;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'3')]")
	private WebElement three;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'4')]")
	private WebElement four;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'5')]")
	private WebElement five;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'6')]")
	private WebElement six;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'7')]")
	private WebElement seven;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'8')]")
	private WebElement eight;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'9')]")
	private WebElement nine;

	
	@FindBy(id="goPhoneTab")
	private WebElement phoneBookTab;
	
	@FindBy(id="goKeypadTab")
	private WebElement keyPadTab;
	
	@FindBy(id="dialer-preview")
	private WebElement numberBox;
	
	@FindBy(id="dialpad-call-button")
	private WebElement callBtn; 
	
	@FindBy(id="dialpad-cancel-button")
	private WebElement cancelBtn; 
	
	public DialPadPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void click_1() {
		one.click();
		logger.debug("Clicked on 1");
	}

	public void click_2() {
		two.click();
		logger.debug("Clicked on 2");
	}
	
	public void click_3() {
		three.click();
		logger.debug("Clicked on 3");
	}
	
	public void click_4() {
		four.click();
		logger.debug("Clicked on 4");
	}

	public void click_5() {
		five.click();
		logger.debug("Clicked on 5");
	}
	
	public void click_6() {
		six.click();
		logger.debug("Clicked on 6");
	}
	
	public void click_7() {
		seven.click();
		logger.debug("Clicked on 7");
	}

	public void click_8() {
		eight.click();
		logger.debug("Clicked on 8");
	}
	
	public void click_9() {
		nine.click();
		logger.debug("Clicked on 9");
	}
	
	public void phonebook_Click() {
		phoneBookTab.click();
		logger.debug("Clicked on phone book");
	}
	
	public void keypad_Click() {
		keyPadTab.click();
		logger.debug("Clicked on keypad");
	}
	
	public void nubmer_Box( String number) {
		numberBox.sendKeys(number);
		logger.debug("Clicked on numberBox");
	}
	
	public void callBtn_Click() {
		callBtn.click();
		logger.debug("Clicked on callBtn");
	}
	
	public void cancelBtn_Click() {
		cancelBtn.click();
		logger.debug("Clicked on cancelBtn");
	}

}
