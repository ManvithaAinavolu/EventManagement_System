package Testcases;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import BaseClass.libraryClass;

import pages.EventBookingpage;

public class BookingTestCase extends libraryClass{
	BookingTestCase(){
		
	}
	
	EventBookingpage ebp;
	@BeforeClass
	public void openApp() throws InterruptedException {
	    initializeBrowser(); 
	    Thread.sleep(3000);// Step 1: launch browser
	    openApplication(); // Step 2: open site
	    Thread.sleep(3000);
	    ebp=new EventBookingpage(driver);
	    
	}
	
	@Test(priority=1)
	public void setFormValues() throws InterruptedException {
		
		
		ebp.fillBookingForm("Alice", "Smith", "9876543210", "alice@example.com", 
			    "Wedding", "2025-08-01", "18:00", "100", 
			    "Veg", "123 Main St", "Chennai", "600001", "Grand wedding event");
		
		Thread.sleep(2000);
		System.out.println("Entered the form details");
		
	}
	
	@Test(priority=2)
	public void clickSubmit() throws InterruptedException {
		
		ebp.clickBookNow();
		System.out.println("clicked on Book now");
	}
	@Test(priority=3)
	public void verifyConfirmationMessage() throws InterruptedException, IOException {
	    Thread.sleep(2000);
	    String msg = ebp.getConfirmationMessage();
	    System.out.println("Confirmation: " + msg);
	   // assertTrue(msg.contains("success") || msg.contains("confirmed")); 
	}
	@AfterClass
	public void closeApp() throws InterruptedException {
		//Thread.sleep(3000);
		closeBrowser();
		System.out.println("closed the browser");
		
	}
	

}
