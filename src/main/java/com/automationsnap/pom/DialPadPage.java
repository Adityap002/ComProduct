package com.automationsnap.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DialPadPage {

	private static Logger logger = LogManager
			.getLogger(DialPadPage.class.getName());

	protected static WebDriver driver = null;

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

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'0')]")
	private WebElement zero;

	@FindBy(xpath = "//table[@id='keypad-content']//span[contains(text(),'*')]")
	private WebElement star;

	@FindBy(id = "goPhoneTab")
	private WebElement phoneBookTab;

	@FindBy(id = "goKeypadTab")
	private WebElement keyPadTab;

	@FindBy(id = "dialer-preview")
	private WebElement numberBox;

	@FindBy(id = "dialpad-call-button")
	private WebElement callBtn;

	@FindBy(id = "dialpad-cancel-button")
	private WebElement cancelBtn;

	public DialPadPage(WebDriver driver) {
		super();
		DialPadPage.driver = driver;
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

	public void click_0() {
		zero.click();
		logger.debug("Clicked on 0");
	}

	public void click_star() {
		star.click();
		logger.debug("Clicked on *");
	}

	public void phonebook_Click() {
		phoneBookTab.click();
		logger.debug("Clicked on phone book");
	}

	public void keypad_Click() {
		keyPadTab.click();
		logger.debug("Clicked on keypad");
	}

	public void nubmer_Box(String number) {
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

	public void dialNumber(String dialnumber) {

		for (int i = 0; i < dialnumber.length(); i++) {
			switch (dialnumber.charAt(i)) {
			case '1':
				click_1();
				break;
			case '2':
				click_2();
				break;
			case '3':
				click_3();
				break;
			case '4':
				click_4();
				break;
			case '5':
				click_5();
				break;
			case '6':
				click_6();
				break;
			case '7':
				click_7();
				break;
			case '8':
				click_8();
				break;
			case '9':
				click_9();
				break;
			case '0':
				click_0();
				break;
			default:
				logger.debug("Default swith");
				break;
			}
		}
	}
}
