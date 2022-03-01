Feature: NewLoads functionality_TC_01
  Verify  "Create a New Load functionality" for New Loads after entering all the valid inputs.


  Scenario Outline: NewLoads functionality_TC_01
    Given Initialize the browser with chrome
    And User navigate to "https://app.newloads.com/login"
    And User enters <MobileNo> and clicks on Send Otp
    And User enters <OTP> and clicks on next
    And User is logged into the system
    When User clicks on PostLoads button
    And selects Create a new bulk option
    Then "Post a load" layer is displayed
    When User enters <pickup> location and selects from the dropdown
    And enters <DropOff> location and does the same
    And Select <Vehicle type>
    And Selects Pickup date and Drop off date
    And Selects Material type and enter <Consignment weight>
    And Clicks On Continue Button
    And Selects the Vehicle Insurance Checkbox and enters the <number of vehicle> in the corresponding field
    And clicks on Post Load button
    Then Success Messsage "Load posted successfully" is displayed


    Examples:
      |OTP    |MobileNo  |pickup |DropOff|Vehicle type |Consignment weight|number of vehicle|
      |901388 |9004207961|Mumbai |Delhi  |Tanker       |50                |12               |
