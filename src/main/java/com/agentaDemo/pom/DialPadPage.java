package com.agentaDemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DialPadPage {

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

	public void click_1(WebDriver driver) {
		one.click();
	}

	public void click_2(WebDriver driver) {
		two.click();
	}
	
	public void click_3(WebDriver driver) {
		three.click();
	}
	
	public void click_4(WebDriver driver) {
		four.click();
	}

	public void click_5(WebDriver driver) {
		five.click();
	}
	
	public void click_6(WebDriver driver) {
		six.click();
	}
	
	public void click_7(WebDriver driver) {
		seven.click();
	}

	public void click_8(WebDriver driver) {
		eight.click();
	}
	
	public void click_9(WebDriver driver) {
		nine.click();
	}
	
	public void phonebook_Click(WebDriver driver) {
		phoneBookTab.click();
	}
	
	public void keypad_Click(WebDriver driver) {
		keyPadTab.click();
	}
	
	public void nubmer_Box(WebDriver driver, String number) {
		numberBox.sendKeys(number);
	}
	
	public void callBtn_Click(WebDriver driver) {
		callBtn.click();
	}
	
	public void cancelBtn_Click(WebDriver driver) {
		cancelBtn.click();
	}

}
