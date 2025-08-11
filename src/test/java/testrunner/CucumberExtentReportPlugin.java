package testrunner;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.Plugin;
import io.cucumber.plugin.event.*;
import io.cucumber.plugin.event.Status;

import java.io.File;

public class CucumberExtentReportPlugin implements Plugin, ConcurrentEventListener {

    private static ExtentReports extent;
    private static ExtentTest scenarioTest;
    private static final String SCREENSHOT_DIR = "target/screenshots/";
    private String currentScenarioName;

    public CucumberExtentReportPlugin() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        currentScenarioName = event.getTestCase().getName();
        scenarioTest = extent.createTest(currentScenarioName);
    }

    private void onTestStepFinished(TestStepFinished event) {
        Status status = event.getResult().getStatus();

        if (status == Status.PASSED) {
            scenarioTest.pass(event.getTestStep().getCodeLocation());
        } else if (status == Status.FAILED) {
            scenarioTest.fail(event.getResult().getError().getMessage());

            // ✅ Use safe filename and relative path
            String safeFileName = currentScenarioName.replaceAll("[^a-zA-Z0-9]", "_") + ".png";
            String screenshotAbsolutePath = SCREENSHOT_DIR + safeFileName;
            String screenshotRelativePath = "screenshots/" + safeFileName;

            File screenshotFile = new File(screenshotAbsolutePath);
            if (screenshotFile.exists()) {
                try {
                    scenarioTest.fail("Screenshot on Failure",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotRelativePath).build());
                } catch (Exception e) {
                    scenarioTest.warning("Failed to attach screenshot: " + e.getMessage());
                }
            } else {
                scenarioTest.warning("Screenshot not found: " + screenshotAbsolutePath);
            }
        } else if (status == Status.SKIPPED) {
            scenarioTest.skip("Step skipped: " + event.getTestStep().getCodeLocation());
        }
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        extent.flush();
    }
}
