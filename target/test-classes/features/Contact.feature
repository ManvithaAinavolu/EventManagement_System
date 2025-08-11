Feature: Event Contact Us Form Functionality

@tc007
Scenario Outline: Submit Contact Us form with valid data
    Given user is on Contact Us section
    When user enters contact name "<Name>" "<Email>" "<Subject>" "<Message>"
    
    And user clicks Send Message
    Then contact confirmation "Your message has been sent !" should be shown
 
    Examples:
      | Name     | Email            | Subject      | Message                             |
      | John Doe | john@example.com | Booking Help | Please assist with my booking.      |
      | Jane Roe | jane@example.net | Inquiry      | I’d like to know more about events. |
 
 @tc008
  Scenario: Submit Contact Us form with missing message
    Given user is on Contact Us section
    When user enters contact details "Alex" "example@gmail.com" "conact"
    And leaves the contact message field blank
    And user clicks Send Message
    Then contact error "Your message is required" should be shown
 