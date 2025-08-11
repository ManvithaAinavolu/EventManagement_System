package Stepdefinitions;

import BaseClass.libraryClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks extends libraryClass{
	@Before
	 public void setUp() throws Exception {
		initializeBrowser();
		openApplication();
	    }

   @After
   public void tearDown() {
	   closeBrowser();
   }
}
