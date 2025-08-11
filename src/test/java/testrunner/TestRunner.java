package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		    features = {"src/test/resources/features"},      // Path to feature files

		    glue = {"Stepdefinitions","Hooks"},      
		    // Package for step definitions and hooks
		  tags="@tc001 or @tc002 or @tc003 or @tc004 or @tc005 or @tc006 or @tc007 or @tc008",

		    		plugin = {

		    		        "pretty",

		    		        "html:target/cucumber-html-report.html",

		    		        "json:target/cucumber.json",

		    		        "rerun:target/rerun.txt",
		    		        
		    		        "testrunner.CucumberExtentReportPlugin"

		    		       	    		    },
		    		//dryRun=true,

		    		 monochrome = false


		)

		public class TestRunner extends AbstractTestNGCucumberTests {

		    // Empty class - the annotations drive the configuration

	}
	 