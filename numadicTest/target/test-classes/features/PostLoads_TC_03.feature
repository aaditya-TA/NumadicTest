Feature: NewLoads functionality_TC_03
  Verify  "Create a New Load functionality" for New Loads with different combinations.


  Scenario Outline: NewLoads functionality_TC_03
    Given Initialize the browser with chrome
    And User navigate to "https://app.newloads.com/login"
    And User enters <MobileNo> and clicks on Send Otp
    And User enters <OTP> and clicks on next
    And User is logged into the system
    When User clicks on PostLoads button
    And selects Create a new bulk option
    Then "Post a load" layer is displayed
    When User leaves Pickup location empty and fills the <DropOff>,<Consignment weight>,etc
    And Clicks On Continue Button
    Then The pickup field field will have errors displayed for them
    When User leaves Drop Off location empty and fills the <pickup>,<Consignment weight>,etc
    And Clicks On Continue Button
    Then The drop off field field will have error displayed for them
    When User leaves Pickup date and Drop date field empty and fills the <pickup>, <DropOff>,<Consignment weight>,etc
    And Clicks On Continue Button
    Then The pickup and drop off date field will have error displayed for them
    When User leaves Vehicle field empty and fills the <pickup>, <DropOff>,<Consignment weight>,etc
    And Clicks On Continue Button
    Then The vehicle type field will have error displayed for them



    Examples:
      |OTP    |MobileNo  |pickup |DropOff|Consignment weight|
      |901388 |9004207961|Mumbai |Delhi  |50                |

