Feature: Event Booking Form Functionality
Background:
	Given user is on Event Booking page
	And close the Browser
	
@tc001
Scenario Outline: Submit booking form with valid inputs
    
    When user fills booking form with "<FirstName>", "<LastName>", "<Phone>", "<Email>", "<EventType>", "<EventDate>", "<EventTime>", "<GuestCount>", "<ServiceType>", "<Address>", "<City>", "<Pincode>", "<Details>"
    And user clicks Book Now
    Then booking confirmation "<Message>" is displayed
 		
    Examples:
      | FirstName | LastName | Phone      | Email             | EventType | EventDate  | EventTime | GuestCount | ServiceType | Address        | City    | Pincode | Details             | Message                                     |
      | Alice     | Smith    | 9876543210 | alice@example.com | Wedding   | 2025-08-01 | 18:00     |        100 | Veg         | 123 Main St    | Chennai |  600001 | Grand wedding event | Thank you Alice! Your booking is confirmed. |
      | Bob       | Lee      | 9988776655 | bob@example.net   | Birthday  | 2025-08-05 | 14:00     |         50 | NonVeg      | 456 Elm Street | Coimbatore  |  400001 | 50th Birthday party | Thank you Bob! Your booking is confirmed.   |
 @tc002
  Scenario: Submit booking form with missing first name
    
    When user fills all fields except First Name
    And user clicks Book Now
    Then validation error "Your first name is required" should be shown
    
 @tc003
  Scenario: Submit booking form with invalid email
    
    When user enters invalid booking email "invalid@@email"
    And fills other booking fields correctly
    And user clicks Book Now
    Then validation error "Invalid email address" for email id should be shown
    
 @tc004
  Scenario: Submit booking form with zero guests
    
    When user enters "0" as Guest Count
    And user has filled the booking form
    And user clicks Book Now
    Then validation error "Guest count must be at least 1" for guest should be shown
    
 @tc005
  Scenario: Reset the booking form
  
    When user has filled the booking form
    When user clicks Reset
    Then all booking form fields should be cleared
    
 
 @tc006
 Scenario: Entering Booking Filed using Excel file 
 	When data is taken from excel file
 	Then user clicks Book Now
 	
