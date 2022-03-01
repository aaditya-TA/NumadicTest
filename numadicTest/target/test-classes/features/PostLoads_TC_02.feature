Feature: NewLoads functionality_TC_02
  Verify  "Create a New Load functionality" for New Loads with keeping all the fields blank.


  Scenario Outline: NewLoads functionality_TC_02
    Given Initialize the browser with chrome
    And User navigate to "https://app.newloads.com/login"
    And User enters <MobileNo> and clicks on Send Otp
    And User enters <OTP> and clicks on next
    And User is logged into the system
    When User clicks on PostLoads button
    And selects Create a new bulk option
    Then "Post a load" layer is displayed
    When User does not enter any values in the fields
    And Clicks On Continue Button
    Then The mandatory fields will have errors displayed for them
    And User closes the browser


    Examples:
      |OTP    |MobileNo  |
      |901388 |9004207961|
