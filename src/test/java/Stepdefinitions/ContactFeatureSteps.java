package Stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.libraryClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EventContactpage;

public class ContactFeatureSteps extends libraryClass {
	EventContactpage ecp;
	private static final Logger logger = LogManager.getLogger(libraryClass.class);
	@Given("user is on Contact Us section")
	public void user_is_on_contact_us_section() {
	    ecp=new EventContactpage(driver);
	    logger.info("Started Contact feature:");
	}

	@When("user enters contact name {string} {string} {string} {string}")
	public void user_enters_contact_name(String name, String email,String subject,String message) throws InterruptedException {
		Thread.sleep(2000);
		ecp.enter_Contactdetails(name,email,subject,message);
		logger.info("Data entered");
	}


	@When("user clicks Send Message")
	public void user_clicks_send_message() throws InterruptedException {
	    ecp.clickSend();
	    logger.info("Message sent");
	}

	@Then("contact confirmation {string} should be shown")
	public void contact_confirmation_should_be_shown(String string) throws InterruptedException, IOException {
	    Thread.sleep(3000);
	    String res=ecp.getConfirmMessage();
	    logger.info(res);
	    assertEquals(string, res);
	    
	}

	@When("user enters contact details {string} {string} {string}")
	public void user_enters_contact_name(String name, String email,String subject) throws InterruptedException {
		Thread.sleep(2000);
		ecp.enter_Contactdetails(name,email,subject,"");
		
	}
	@When("leaves the contact message field blank")
	public void leaves_the_contact_message_field_blank() {
		logger.info("Data entered without message");
	}

	@Then("contact error {string} should be shown")
	public void contact_error_should_be_shown(String string) {
		String actualError = ecp.getErrorMessage();
	    logger.info("Error Message: " + actualError);
	    assertEquals(actualError, string);
	}

}
