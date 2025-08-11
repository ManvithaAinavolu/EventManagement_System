package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableFunctions {

    WebDriver driver;

    // Constructor to initialize WebDriver
    public ReusableFunctions(WebDriver driver) {
        this.driver = driver;
    }

    // Waits until the given element is visible
    public void waitForElementToBeVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Error in waitForElementToBeVisible: " + e.getMessage());
        }
    }

    // Clicks on an element after ensuring it's visible
    public void clickElement(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            element.click();
        } catch (Exception e) {
            System.out.println("Error in clickElement: " + e.getMessage());
        }
    }

    // Clears and enters text into a textbox
    public void enterText(WebElement element, String text) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Error in enterText: " + e.getMessage());
        }
    }

    // Scrolls the browser to bring the element into view
    public void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.out.println("Error in scrollIntoView: " + e.getMessage());
        }
    }

    // Captures screenshot and saves it to the specified path
    public void takescreenshot(String path) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            System.out.println("IO Error in takescreenshot: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error in takescreenshot: " + e.getMessage());
        }
    }

    // Selects an option from a dropdown by visible text
    public void selectDropdownByText(WebElement dropdownElement, String visibleText) {
        try {
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(visibleText);
        } catch (Exception e) {
            System.out.println("Error in selectDropdownByText: " + e.getMessage());
        }
    }

    // Performs mouse hover action on the element
    public void hoverOverElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Error in hoverOverElement: " + e.getMessage());
        }
    }

    // Selects an option from auto-suggestion list based on the given value
    public void selectFromAutoSuggest(String valueToSelect) {
        try {
            Thread.sleep(2000); // Wait for the suggestion list to load
            List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
            for (WebElement suggestion : suggestions) {
                if (suggestion.getText().toLowerCase().contains(valueToSelect.toLowerCase())) {
                    suggestion.click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in selectFromAutoSuggest: " + e.getMessage());
        }
    }

    // Scrolls down the page and clicks on the given element by XPath
    public void scrolldown(String element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,2000)");
            Thread.sleep(5000);
            WebElement ele = driver.findElement(By.xpath(element));
            js.executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            System.out.println("Error in scrolldown: " + e.getMessage());
        }
    }

    // Selects a radio button if not already selected
    public void selectRadioButton(By locator) {
        try {
            WebElement radio = driver.findElement(locator);
            if (!radio.isSelected()) {
                radio.click();
            }
        } catch (Exception e) {
            System.out.println("Error in selectRadioButton: " + e.getMessage());
        }
    }

    // Clicks using JavaScript executor (used when normal click fails)
    public void clickSearch(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Error in clickSearch: " + e.getMessage());
        }
    }

    // Handles window switching: opens a new window, switches to it, then switches back
    public void windowhandling(String element) {
        try {
            driver.findElement(By.xpath(element)).click();
            String mainWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            for (String window : allWindows) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    System.out.println("Switched to new window: " + driver.getTitle());
                }
            }

            driver.switchTo().window(mainWindow); // Return to original window
        } catch (Exception e) {
            System.out.println("Error in windowhandling: " + e.getMessage());
        }
    }

    // Switches to a frame using WebElement
    public void switchToFrame(WebElement frameElement) {
        try {
            driver.switchTo().frame(frameElement);
        } catch (Exception e) {
            System.out.println("Error in switchToFrame: " + e.getMessage());
        }
    }

    // Handles alert popup: sends text and accepts it
    public void alerthandling(String value) {
        try {
            Alert alert = driver.switchTo().alert();
            Thread.sleep(4000);
            alert.sendKeys(value);
            alert.accept();
        } catch (Exception e) {
            System.out.println("Error in alerthandling: " + e.getMessage());
        }
    }

    // Selects a date in a calendar by aria-label (used in dynamic date pickers)
    public void selectDate(String dateLabel) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@aria-label='" + dateLabel + "']")));
            dateElement.click();
        } catch (Exception e) {
            System.out.println("Error in selectDate: " + e.getMessage());
        }
    }
}
