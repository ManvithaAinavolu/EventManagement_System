package testrunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		    features = {"src/test/resources/features/"},      // Path to feature files

		    glue = {"Stepdefinitions","Hooks"},      
		    // Package for step definitions and hooks
		  tags="@tc007 or @tc008",

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


public class TestRunner2 extends AbstractTestNGCucumberTests {

}
