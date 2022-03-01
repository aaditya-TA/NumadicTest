package step_definitions;

import com.aventstack.extentreports.ExtentTest;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import pageObjects.LoadPage;
import pageObjects.LoginPage;
import resources.Base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class Steps extends Base {




    LoginPage loginPageObj;
    LoadPage loadPageObj;
    SoftAssert assertion ;
    ExtentTest test;





    @Given("^Initialize the browser with chrome$")
    public void initializeTheBrowserWithChrome() throws IOException {
        driver = initializeDriver();
        Actions actions = new Actions(driver);
    }

    @And("^User navigate to \"([^\"]*)\"$")
    public void userNavigateTo(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(url);
        loadPageObj = new LoadPage(driver);

    }




    @And("^User closes the browser$")
    public void userClosesTheBrowser() {
        driver.quit();
    }



    @And("^User enters (.+) and clicks on next$")
    public void userEntersOTPAndClicksOnNext(String otp) throws InterruptedException {
        //Entering the OTP
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginPageObj.otpField);
        //Have to manually enter otp
//        loginPageObj.otpField.sendKeys(otp);



        //Agreeing to the terms and condition
        clickItemJS(loginPageObj.termsAndCondtnChbox,"Accepting terms and condition");

        clickItemJS(loginPageObj.loginBtn,"Login button click");

    }




    @And("^User enters (.+) and clicks on Send Otp$")
    public void userEntersMobileNoAndClicksOnSendOtp(String mobileNumber) throws InterruptedException {
        //Entering the mobile number
        loginPageObj = new LoginPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginPageObj.mobileNoField);
        clickItemJS(loginPageObj.mobileNoField,"Mobile number field");

        //entering number
        loginPageObj.mobileNoField.sendKeys(mobileNumber);

        //clicking on send otp
        Thread.sleep(2000);
        Actions act= new Actions(driver);
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();


    }

    @And("^User is logged into the system")
    public void userIsLoggedIntoTheSystem() throws Throwable {
        //Verifying the good evening text is displayed
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(loadPageObj.postLoadsButton));
       Assert.assertTrue(loadPageObj.postLoadsButton.isDisplayed());

    }

    @When("^User clicks on PostLoads button$")
    public void userClicksOnPostLoadsButton() {
        clickItemJS(loadPageObj.postLoadsButton,"Clicking on post load button");

    }

    @And("^selects Create a new bulk option$")
    public void selectsCreateANewBulkOption() throws InterruptedException {
        Thread.sleep(1000);
        clickItemJS(loadPageObj.createANewBulk,"Clicking on creating a new bulk button");
    }

    @Then("^\"([^\"]*)\" layer is displayed$")
    public void layerIsDisplayed(String layerHeadline) throws Throwable {
        //Verifying that the layer is displayed
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loadPageObj.postLoadHeadline));
        Assert.assertTrue(loadPageObj.postLoadHeadline.getText().contains(layerHeadline));

    }



    @When("^User enters (.+) location and selects from the dropdown$")
    public void userEntersPickupLocation(String pickupLoc) throws InterruptedException {
        Assert.assertTrue(loadPageObj.loadDetailFields.get(0).isDisplayed());
        loadPageObj.loadDetailFields.get(0).click();
       Actions act = new Actions(driver);
       act.sendKeys(pickupLoc).perform();
       Thread.sleep(3000);
       act.sendKeys(Keys.ENTER).perform();
    }

    @And("^enters (.+) location and does the same$")
    public void entersDropOffLocationAndDoesTheSame(String dropOff) throws InterruptedException {
        Assert.assertTrue(loadPageObj.loadDetailFields.get(1).isDisplayed(),"Drop off location is displayed");
        clickItemJS(loadPageObj.loadDetailFields.get(1),"drop off location");
        Actions act= new Actions(driver);
        act.sendKeys(dropOff).perform();
        Thread.sleep(3000);
        act.sendKeys(Keys.ENTER).perform();
    }


    @And("^Select (.+)$")
    public void selectVehicleType(String vehicleType) throws InterruptedException {
        Assert.assertTrue(loadPageObj.loadDetailFields.get(2).isDisplayed(),"The vehicle type field is visible");
        loadPageObj.loadDetailFields.get(2).click();
        Thread.sleep(500);
        clickItemJS(loadPageObj.tankerOption,"Selecting tanker from the dropdown");
    }

    @And("^Selects Pickup date and Drop off date$")
    public void selectsPickupDateAndDropOffDate() throws InterruptedException {
        Assert.assertTrue(loadPageObj.pickupAndDropDateField.isDisplayed());
        clickItemJS(loadPageObj.pickupAndDropDateField,"clicking on the pickup and drop off date field");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loadPageObj.pickUpDate));
        clickItemJS(loadPageObj.pickUpDate,"selecting pickup date");
        clickItemJS(loadPageObj.dropOffDate,"Selecting dropoff date");
    }

    @And("^Selects Material type and enter (.+)$")
    public void selectsMaterialTypeAndEnterConsignmentDate(String consignmentWeight) {
        Assert.assertTrue(loadPageObj.loadDetailFields.get(3).isDisplayed());
        clickItemJS(loadPageObj.loadDetailFields.get(3),"Material type dropdown");
        clickItemJS(loadPageObj.materialTypeValue,"Selecting the value from the material type dropdown");

        Assert.assertTrue(loadPageObj.consignMentWeightField.isDisplayed());
        clickItemJS(loadPageObj.consignMentWeightField,"Consignment weight field");
        Actions act = new Actions(driver);
        act.sendKeys(consignmentWeight).perform();
    }

    @And("^Clicks On Continue Button$")
    public void clicksOnContinueButton() {
        Assert.assertTrue(loadPageObj.continueButton.isDisplayed(),"Continue button is displayed");
        clickItemJS(loadPageObj.continueButton,"Continue button click");

    }

    @And("^Selects the Vehicle Insurance Checkbox and enters the (.+) in the corresponding field$")
    public void selectsTheVehicleInsuranceCheckboxAndEntersTheVehicleNumberInTheCorrespondingField(String noOfVeh) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loadPageObj.noOfVehicles));
        Assert.assertTrue(loadPageObj.documensRequiredText.isDisplayed(),"vehicle insurance checkbox is displaued");
        clickItemJS(loadPageObj.vehicleInsurance,"Vehicle insurance checkbox is selected");
        Assert.assertTrue(loadPageObj.noOfVehicles.isDisplayed(),"No of vehicles is displayed");

        clickItemJS(loadPageObj.noOfVehicles,"Number or vehicles required field");
        Actions act = new Actions(driver);
        act.sendKeys(noOfVeh).perform();

    }

    @And("^clicks on Post Load button$")
    public void clicksOnPostLoadButton() {
        Assert.assertTrue(loadPageObj.postLoadButton.isDisplayed(),"post load button is dissplayed");
        clickItemJS(loadPageObj.postLoadButton,"Post load button");
    }

    @Then("^Success Messsage \"([^\"]*)\" is displayed$")
    public void successMesssageIsDisplayed(String successMessage) throws Throwable {
        Assert.assertTrue(loadPageObj.postLoadedSuccessMessage.isDisplayed(),"post loaded succesfully message is displayed");

        System.out.println(loadPageObj.postLoadedSuccessMessage.getText());
        Assert.assertTrue(loadPageObj.postLoadedSuccessMessage.getText().contains(successMessage),"Message displayed as expected");
    }

    @When("^User does not enter any values in the fields$")
    public void userDoesNotEnterAnyValuesInTheFields() {
        System.out.println("Not entering any values");
    }

    @Then("^The mandatory fields will have errors displayed for them$")
    public void theMandatoryFieldsWillHaveErrorsDisplayedForThem() {
        assertion = new SoftAssert();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loadPageObj.selectPickupLocationError));
        assertion.assertTrue(loadPageObj.selectPickupLocationError.isDisplayed(),"Select a pickup location error is displayed");
        assertion.assertTrue(loadPageObj.selectDropoffError.isDisplayed(),"Select a drop off loaction error is displayed");
        assertion.assertTrue(loadPageObj.selectAVehicleTypeError.isDisplayed(),"Select a vehicle type error is displayed");
        assertion.assertTrue(loadPageObj.selectPickupAndDropOffDate.isDisplayed(),"Select a pickup and dropp off date error is displayed");
        assertion.assertAll();
    }




    @When("User leaves Pickup location empty and fills the (.+),(.+),etc")
    public void userLeavesPickupLocationEmptyAndFillsTheOtherFields(String dropOffDate, String consignmentWeight) throws InterruptedException {
        clickItemJS(loadPageObj.loadDetailFields.get(1),"drop off field");
        Actions act = new Actions(driver);
        act.sendKeys(dropOffDate).perform();
        Thread.sleep(3000);
        act.sendKeys(Keys.ENTER).perform();

        Assert.assertTrue(loadPageObj.loadDetailFields.get(2).isDisplayed(),"The vehicle type field is visible");
        loadPageObj.loadDetailFields.get(2).click();
        Thread.sleep(500);
        loadPageObj.tankerOption.click();

        clickItemJS(loadPageObj.pickupAndDropDateField,"clicking on the pickup and drop off date field");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loadPageObj.pickUpDate));
        clickItemJS(loadPageObj.pickUpDate,"selecting pickup date");
        clickItemJS(loadPageObj.dropOffDate,"Selecting dropoff date");

        clickItemJS(loadPageObj.loadDetailFields.get(3),"Material type dropdown");
        clickItemJS(loadPageObj.materialTypeValue,"Selecting the value from the material type dropdown");

        Thread.sleep(1000);
        Assert.assertTrue(loadPageObj.consignMentWeightField.isDisplayed());
        clickItemJS(loadPageObj.consignMentWeightField,"Consignment weight field");
        act.sendKeys(consignmentWeight).perform();



    }

    @Then("The pickup field field will have errors displayed for them")
    public void thePickupFieldFieldWillHaveErrorsDisplayedForThem() {
        assertion = new SoftAssert();
        assertion.assertTrue(loadPageObj.selectPickupLocationError.isDisplayed(),"Pickup location erorr is displayed");

    }

    @When("User leaves Drop Off location empty and fills the (.+),(.+),etc")
    public void userLeavesDropOffLocationEmptyAndFillsThePickupConsignmentWeightEtc(String pickupDate, String consignmentWeight) throws InterruptedException {
        clickItemJS(loadPageObj.loadDetailFields.get(0),"pick up field");
        Actions act = new Actions(driver);
        act.sendKeys(pickupDate).perform();
        Thread.sleep(3000);
        act.sendKeys(Keys.ENTER).perform();

        clickItemJS(loadPageObj.loadDetailFields.get(1),"pick up field");
        clickItemJS(loadPageObj.clearButton.get(1),"Clear drop off field");

    }


    @Then("The drop off field field will have error displayed for them")
    public void thedropoffFieldFieldWillHaveErrorsDisplayedForThem() {
        Assert.assertTrue(loadPageObj.selectDropoffError.isDisplayed(),"Drop off location erorr is displayed");

    }

    @When("User leaves Pickup date and Drop date field empty and fills the (.+), (.+),(.+),etc")
    public void userLeavesPickupDateAndDropDateFieldEmptyAndFillsThePickupDropOffConsignmentWeightEtc(String pickupDate,String dropOffDate,String consignmentWeight) throws InterruptedException {
        Actions act = new Actions(driver);
        clickItemJS(loadPageObj.loadDetailFields.get(1),"Drop off field");
        act.sendKeys(dropOffDate).perform();
        Thread.sleep(3000);
        act.sendKeys(Keys.ENTER).perform();

       clickItemJS(loadPageObj.clearButton.get(3),"Clearing the pickup and drop off date");
    }

    @Then("The pickup and drop off date field will have error displayed for them")
    public void thePickupAndDropOffDateFieldWillHaveErrorDisplayedForThem() {
        assertion.assertTrue(loadPageObj.selectPickupAndDropOffDate.isDisplayed(),"The error for pickup and drop off is displayed");
    }


    @When("User leaves Vehicle field empty and fills the (.+), (.+),(.+),etc")
    public void userLeavesVehicleFieldEmptyAndFillsThePickupDropOffConsignmentWeightEtc(String pickupDate, String drop, String consignmentWeight) throws InterruptedException {
        clickItemJS(loadPageObj.pickupAndDropDateField,"clicking on the pickup and drop off date field");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loadPageObj.pickUpDate));
        clickItemJS(loadPageObj.pickUpDate,"selecting pickup date");
        clickItemJS(loadPageObj.dropOffDate,"Selecting dropoff date");


        clickItemJS(loadPageObj.loadDetailFields.get(2),"The vehicle type");
        clickItemJS(loadPageObj.clearButton.get(2),"Vehicle field empty");


    }


    @Then("The vehicle type field will have error displayed for them")
    public void theVehicleTypeFieldWillHaveErrorDisplayedForThem() {
        assertion.assertTrue(loadPageObj.selectAVehicleTypeError.isDisplayed(),"Vehicle type error is displayed");
        assertion.assertAll();
    }




    @After
    public void killBrowser(Scenario scenario) {
//Preparing the extent report
        try {
           if (scenario.isFailed()) {
                // Code to take screenshot and embed in json cucumber report
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                //Building up the destination path for the screenshot to save
                //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
                File destinationPath = new File(System.getProperty("user.dir") + "/target/ExtentReport/" + screenshotName + ".png");

                //Copy taken screenshot from source location to destination location
                Files.copy(sourcePath, destinationPath);

                //This attach the specified screenshot to the test
                Reporter.addScreenCaptureFromPath(destinationPath.toString());
                Reporter.addScenarioLog(screenshotName);

            }

        } catch (Throwable somePlatformsDontSupportScreeshots) {
//            scenario.write("This Browser does not support screenshots - " + somePlatformsDontSupportScreeshots.getMessage());
        } finally {
            try {
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception e) {
//                scenario.write("Unknown exception while closing browser: " + e.getMessage());
            }
        }





    }

}



