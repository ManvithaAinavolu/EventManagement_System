package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ReusableFunctions;

public class EventContactpage {
	WebDriver driver;
	ReusableFunctions rf;

	// Constructor initializing WebDriver and ReusableFunctions
	public EventContactpage(WebDriver driver) {
		this.driver = driver;
		rf = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this); // Initializes all @FindBy elements
	}

	// Locating elements on the Contact Us page
	@FindBy(xpath = "//a[text()='Contact Us']")
	public WebElement Contact;

	@FindBy(id = "contact_name")
	public WebElement name;

	@FindBy(id = "contact_email")
	public WebElement email;

	@FindBy(id = "contact_subject")
	public WebElement subject;

	@FindBy(id = "contact_message")
	public WebElement message;

	@FindBy(id = "message")
	public WebElement send;

	@FindBy(id = "mesgtab")
	public WebElement confrim;

	@FindBy(id = "contactMessageErr")
	public WebElement err;

	// Method to fill in contact details
	public void enter_Contactdetails(String Name, String Email, String Subject, String Message) throws InterruptedException {
		Thread.sleep(2000); // Adding a wait for page readiness
		rf.clickElement(Contact); // Clicks the Contact Us tab
		System.out.println("Clicked the element");

		rf.waitForElementToBeVisible(name); // Wait for name input to appear
		rf.enterText(name, Name);
		rf.enterText(email, Email);
		rf.enterText(subject, Subject);
		rf.enterText(message, Message);
		Thread.sleep(2000);
	}

	// Method to click on Send button
	public void clickSend() throws InterruptedException {
		rf.clickElement(send); // Clicks the send button
		Thread.sleep(2000);
	}

	// Method to capture confirmation message after form submission
	public String getConfirmMessage() throws IOException {
		rf.waitForElementToBeVisible(confrim); // Waits for confirmation tab to appear

		String res = confrim.getText(); // Gets confirmation message text

		// Takes and saves a screenshot of the confirmation message
		rf.takescreenshot("target/screenshots/confirmMessage.png");
		System.out.println("Screenshot saved in resources");

		return res;
	}

	// Method to capture error message if message field is empty
	public String getErrorMessage() {
		rf.waitForElementToBeVisible(err); // Wait for error message element
		String res = err.getText(); // Gets error message text
		return res;
	}
}
