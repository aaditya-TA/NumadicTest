package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

import java.util.List;

public class LoginPage extends Base {

    @FindBy(css = "input[placeholder='Mobile Number']")
    public WebElement mobileNoField;

    @FindBy(css = "div[class='ant-row']>div:nth-child(3) div[class='ant-col']>button[type='button']>span")
    public List<WebElement> sendOTPBtn;

    @FindBy (css = "[placeholder='Enter your OTP']")
    public WebElement otpField;

    @FindBy (css = "input[class='ant-checkbox-input']")
    public WebElement termsAndCondtnChbox;

    @FindBy (css = "form[class='ant-form ant-form-vertical ant-form-hide-required-mark']>button[class*='ant-btn submit-btn']")
    public WebElement loginBtn;




    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}
