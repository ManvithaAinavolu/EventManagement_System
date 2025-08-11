package Stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BaseClass.libraryClass;
import Utilities.ReusableFunctions;
import datadriven.excelReadingBooking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EventBookingpage;

public class BookingFeatureSteps extends libraryClass {
	EventBookingpage ebp;
	ReusableFunctions rf;
	excelReadingBooking eb;
	
	  private static final Logger logger = LogManager.getLogger(libraryClass.class);
	@Given("user is on Event Booking page")
	public void user_is_on_event_booking_page() {
//		initializeBrowser();      // 1. Initialize driver FIRST
//	    openApplication();        //2. Open your URL

	    ebp = new EventBookingpage(driver); // 3. THEN initialize the Page Object
	    rf=new ReusableFunctions(driver);
	    eb=new excelReadingBooking();
	    
	    logger.info("User is on Event Management Page");
		
	}

	@When("user fills booking form with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
	public void user_fills_booking_form_with(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13) throws InterruptedException {
		System.out.println("entered ....");
		Thread.sleep(2000);
		ebp.fillBookingForm(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13);
	}

	@When("user clicks Book Now")
	public void user_clicks_book_now() {
		ebp.clickBookNow();
		
	  
	}

	@Then("booking confirmation {string} is displayed")
	public void booking_confirmation_is_displayed(String string) throws IOException {
		ebp.getConfirmationMessage();
		System.out.println("Booked the event Successfylly");
		
	   
	}

	@When("user fills all fields except First Name")
	public void user_fills_all_fields_except_first_name() throws InterruptedException {
		 ebp.fillBookingForm(
			        "",                 // Missing First Name
			        "Smith",
			        "9876543210",
			        "alice@example.com",
			        "Wedding",
			        "2025-08-01",
			        "18:00",
			        "100",
			        "Veg",
			        "123 Main St",
			        "Chennai",
			        "600001",
			        "Grand wedding event"
			    );
	}

	@Then("validation error {string} should be shown")
	public void validation_error_should_be_shown(String string) throws IOException {
	  String err=ebp.getValidationError();
	  assertEquals(string, err);
	  rf.takescreenshot("target/screenshots/MissingFirstName.png");
	}

	@When("user enters invalid booking email {string}")
	public void user_enters_invalid_booking_email(String string) {
	  ebp.enterInvalidEmail(string);
	}

	@When("fills other booking fields correctly")
	public void fills_other_booking_fields_correctly() throws InterruptedException {
		 ebp.fillBookingForm(
			        "John",                 
			        "Smith",
			        "9876543210",
			        "",						//Invalid Email
			        "Wedding",
			        "2025-08-01",
			        "18:00",
			        "100",
			        "Veg",
			        "123 Main St",
			        "Chennai",
			        "600001",
			        "Grand wedding event"
			    );
	}
	
	@Then("validation error {string} for email id should be shown")
	public void validation_error_email_should_be_shown(String string) throws IOException {
		String xpath = "//*[contains(text(),'" + string + "')]";
		 List<WebElement> errorElements = driver.findElements(By.xpath(xpath));
		 rf.takescreenshot("target/screenshots/Submit_booking_form_with_invalid_email.png");
		    // Assert that at least one such element is found
		   assertTrue(errorElements.size() > 0, "Expected error message not displayed");
		  

	}

	@When("user enters {string} as Guest Count")
	public void user_enters_as_guest_count(String string) {
	  ebp.enterInvalidCount(string);
	}

	@Given("user has filled the booking form")
	public void user_has_filled_the_booking_form() throws InterruptedException {
		 ebp.fillBookingForm(
			        "John",                 
			        "Smith",
			        "9876543210",
			        "manvitha@gmail.com",
			        "Wedding",
			        "2025-08-01",
			        "18:00",
			        "",                 //invalid guest count
			        "Veg",
			        "123 Main St",
			        "Chennai",
			        "600001",
			        "Grand wedding event"
			    );
	}
	@Then("validation error {string} for guest should be shown")
	public void validation_error_guest_should_be_shown(String string) throws IOException {
		String xpath = "//*[contains(text(),'" + string + "')]";
		 List<WebElement> errorElements = driver.findElements(By.xpath(xpath));
		 rf.takescreenshot("target/screenshots/Submit_booking_form_with_zero_guests.png");
		    // Assert that at least one such element is found
		   assertTrue(errorElements.size() > 0, "Expected error message not displayed");
		   

	}
	@When("user clicks Reset")
	public void user_clicks_reset() {
	ebp.clickReset();
	}

	@Then("all booking form fields should be cleared")
	public void all_booking_form_fields_should_be_cleared() {
	  ebp.areAllFieldsCleared();
	  
	}
	@When("data is taken from excel file")
	public void send_excel() throws IOException, InterruptedException {
		logger.info("reading excel data");
	String filePath="src/test/resources/Book1.xlsx";
	
		 String sheetName = "Booking"; // Sheet name in Excel
		    int totalRows = eb.getRowCount(filePath, sheetName);

		    for (int i = 1; i <= totalRows; i++) {
		        String firstName = eb.getCellData(filePath, sheetName, i, 0);
		        String lastName = eb.getCellData(filePath, sheetName, i, 1);
		        String phone = eb.getCellData(filePath, sheetName, i, 2);
		        String email = eb.getCellData(filePath, sheetName, i, 3);
		        String eventType = eb.getCellData(filePath, sheetName, i, 4);
		        String eventDate = eb.getCellData(filePath, sheetName, i, 5);
		        String eventTime = eb.getCellData(filePath, sheetName, i, 6);
		        String guestCount = eb.getCellData(filePath, sheetName, i, 7);
		        String serviceType = eb.getCellData(filePath, sheetName, i, 8);
		        String address = eb.getCellData(filePath, sheetName, i, 9);
		        String city = eb.getCellData(filePath, sheetName, i, 10);
		        String pincode = eb.getCellData(filePath, sheetName, i, 11);
		        String details = eb.getCellData(filePath, sheetName, i, 12);
		        
		        logger.info("accesed row");
		        ebp.fillBookingForm(firstName, lastName, phone, email, eventType, eventDate, eventTime, guestCount, serviceType, address, city, pincode, details);
		        logger.info("filled the form");
		    }
		    
		    Thread.sleep(3000);
	}
	@Then("close the Browser")
	public void close() {
		//closeBrowser();
		
	}


}
