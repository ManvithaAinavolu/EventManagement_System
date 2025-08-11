package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ReusableFunctions;

public class EventBookingpage {
    WebDriver driver;
    ReusableFunctions rf;

    // Constructor initializes WebDriver and PageFactory elements
    public EventBookingpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        rf = new ReusableFunctions(driver);
    }

    // WebElements for booking form
    @FindBy(xpath = "//a[text()='Booking']")
    public WebElement Book;

    @FindBy(xpath = "//h4[text()='Book an Event Now']")
    public WebElement h4;

    @FindBy(id = "firstName")
    public WebElement fname;

    @FindBy(id = "lastName")
    public WebElement lname;

    @FindBy(id = "phoneNo")
    public WebElement mobile;

    @FindBy(id = "emaiId")
    public WebElement email;

    @FindBy(id = "eventType")
    public WebElement type;

    @FindBy(id = "eventDate")
    public WebElement date;

    @FindBy(id = "eventTime")
    public WebElement time;

    @FindBy(id = "guestCount")
    public WebElement count;

    @FindBy(xpath = "//*[@id='vegFood']")
    public WebElement veg;

    @FindBy(xpath = "//*[@id='nonVegFood']")
    public WebElement nonveg;

    @FindBy(id = "address")
    public WebElement addr;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "pincode")
    public WebElement pin;

    @FindBy(id = "eventDetail")
    public WebElement details;

    @FindBy(id = "book-now")
    public WebElement bookNowButton;

    @FindBy(xpath = "//button[text()='Reset']")
    public WebElement resetButton;

    @FindBy(id = "bookingconfirm")
    public WebElement confirmMessage;

    @FindBy(id = "ttab")
    public WebElement tableDetails;

    @FindBy(id = "fnameErr")
    public WebElement fnameErr;

    /**
     * Fill the booking form using provided test data
     */
    public void fillBookingForm(String firstName, String lastName, String phone, String emailId,
                                String eventType, String eventDate, String eventTime, String guestCount,
                                String serviceType, String address, String cityName, String pincode, String info) throws InterruptedException {

        rf.clickElement(Book);
        Thread.sleep(2000);  // Wait for the form to load

        rf.waitForElementToBeVisible(fname);

        // Fill input fields only if values are not null or empty
        if (firstName != null && !firstName.isEmpty()) rf.enterText(fname, firstName);
        if (lastName != null && !lastName.isEmpty()) rf.enterText(lname, lastName);
        if (phone != null && !phone.isEmpty()) rf.enterText(mobile, phone);
        if (emailId != null && !emailId.isEmpty()) rf.enterText(email, emailId);
        if (eventType != null && !eventType.isEmpty()) rf.selectDropdownByText(type, eventType);
        if (eventDate != null && !eventDate.isEmpty()) rf.enterText(date, eventDate);
        if (eventTime != null && !eventTime.isEmpty()) rf.enterText(time, eventTime);
        if (guestCount != null && !guestCount.isEmpty()) rf.enterText(count, guestCount);

        // Select service type
        if (serviceType != null && !serviceType.isEmpty()) {
            if (serviceType.equalsIgnoreCase("veg")) rf.clickElement(veg);
            else if (serviceType.equalsIgnoreCase("nonveg")) rf.clickElement(nonveg);
        }

        if (address != null && !address.isEmpty()) rf.enterText(addr, address);
        if (cityName != null && !cityName.isEmpty()) rf.selectDropdownByText(city, cityName);
        if (pincode != null && !pincode.isEmpty()) rf.enterText(pin, pincode);
        if (info != null && !info.isEmpty()) rf.enterText(details, info);
    }

    /**
     * Clicks the 'Book Now' button to submit the form
     */
    public void clickBookNow() {
        rf.clickElement(bookNowButton);
    }

    /**
     * Clicks the 'Reset' button to clear all fields
     */
    public void clickReset() {
        rf.clickElement(resetButton);
    }

    /**
     * Returns the confirmation message after booking and captures a screenshot
     */
    public String getConfirmationMessage() throws IOException {
        String message = confirmMessage.getText();
        String details = tableDetails.getText();
        System.out.println(details);

        // Capture screenshot of booking confirmation
        rf.takescreenshot("target/screenshots/confirmBooking.png");
        System.out.println("Screenshot saved in resources");

        return message;
    }

    /**
     * Enters an invalid email into the email field
     */
    public void enterInvalidEmail(String emailId) {
        rf.enterText(email, emailId);
    }

    /**
     * Enters an invalid guest count
     */
    public void enterInvalidCount(String guest) {
        rf.enterText(count, guest);
    }

    /**
     * Returns validation error text shown for the first name field
     */
    public String getValidationError() {
        return fnameErr.getText();
    }

    /**
     * Verifies whether all input fields are cleared after clicking Reset
     */
    public boolean areAllFieldsCleared() {
        return fname.getAttribute("value").isEmpty() &&
               lname.getAttribute("value").isEmpty() &&
               mobile.getAttribute("value").isEmpty() &&
               email.getAttribute("value").isEmpty() &&
               date.getAttribute("value").isEmpty() &&
               time.getAttribute("value").isEmpty() &&
               count.getAttribute("value").isEmpty() &&
               addr.getAttribute("value").isEmpty() &&
               pin.getAttribute("value").isEmpty() &&
               details.getAttribute("value").isEmpty();
    }
}
