package Testcases;
import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import BaseClass.libraryClass;
import pages.EventBookingpage;
import pages.EventContactpage;

public class ContactUsTestcase extends libraryClass {
ContactUsTestcase(){
	
}
EventContactpage ecp;

@BeforeClass
public void openApp() throws InterruptedException {
    initializeBrowser();   
   // Step 1: launch browser
    openApplication(); // Step 2: open site
    Thread.sleep(3000);
    ecp=new EventContactpage(driver);
    
}
@Test(priority=1)
public void setValues() throws InterruptedException {
	Thread.sleep(2000);
	ecp.enter_Contactdetails("Manvitha", "manvitha@gmail.com", "event booking", "Consider this booking");
	ecp.clickSend();
	System.out.println("Message send successfully");
	
}
@Test(priority=2)
public void verifyConfirmationMessage() throws InterruptedException, IOException {
    Thread.sleep(2000);
    String msg = ecp.getConfirmMessage();
    System.out.println("Confirmation: " + msg);
   // assertTrue(msg.contains("success") || msg.contains("confirmed")); 
}
@AfterClass
public void closeApp() throws InterruptedException {
	Thread.sleep(5000);
	closeBrowser();
	
}

}
