package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

import java.util.List;

public class LoadPage extends Base {

    @FindBy(css = "div[class='ant-row'] h3")
    public WebElement textHeadline;

    @FindBy(css = "button[class*='ant-dropdown-trigger']")
    public WebElement postLoadsButton;

    @FindBy(css = "li[class='ant-dropdown-menu-item']:nth-of-type(1)>a")
    public WebElement createANewBulk;

    @FindBy(css="div[class='ant-modal-title']")
    public WebElement postLoadHeadline;

    @FindBy(css="div[role*='combobox']>div[class='ant-select-selection__rendered']")
    public List<WebElement> loadDetailFields;

    @FindBy(xpath = "//li[text()='Tanker']")
    public WebElement tankerOption;

    @FindBy(css="td[title='March 3, 2022']>div")
    public WebElement pickUpDate;

    @FindBy(css = "td[title='March 25, 2022']>div")
    public WebElement dropOffDate;

    @FindBy(css = "span[class='ant-calendar-picker-input ant-input']>input")
    public WebElement pickupAndDropDateField;

    @FindBy(xpath = "//li[text()='Beverages']")
    public WebElement materialTypeValue;

    @FindBy(css="input[role='spinbutton'][id='weight']")
    public WebElement consignMentWeightField;

    @FindBy(css = "input[value='Vehicle insurance']")
    public WebElement vehicleInsurance;

    @FindBy(css = "label[for='documents']")
    public WebElement documensRequiredText;

    @FindBy(css = "input[id='no_of_vehicles']")
    public WebElement noOfVehicles;

    @FindBy(css = "div[class='ant-modal-footer']>button[type='button']")
    public WebElement continueButton;

    @FindBy(css = "button[class='ant-btn ant-btn-primary']")
    public WebElement postLoadButton ;

    @FindBy(xpath = "//span[contains(text(),'Load posted successfully')]")
    public WebElement postLoadedSuccessMessage;

    @FindBy(xpath = "//div[text()='Select a pickup location']")
    public WebElement selectPickupLocationError;

    @FindBy(xpath = "//div[text()='Select a dropoff location']")
    public WebElement selectDropoffError;

    @FindBy(xpath = "//div[text()='Select a Pickup and Dropoff date']")
    public WebElement selectPickupAndDropOffDate;

    @FindBy(xpath = "//div[text()='Select a Vehicle Type']")
    public WebElement selectAVehicleTypeError;

    @FindBy(css = "i[aria-label='icon: close-circle']")
    public List<WebElement> clearButton;

    public LoadPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }







}

