package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.java.After;
import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public Properties prop;





    public WebDriver initializeDriver() throws IOException
    {

        prop= new Properties();
        FileInputStream fis=new FileInputStream("src\\main\\java\\resources\\data.properties");

        prop.load(fis);
        String browserName=prop.getProperty("browser");
        System.out.println(browserName);

        if(browserName.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver= new ChromeDriver();
            driver.manage().window().maximize();

        }
        else if (browserName.equals("firefox"))
        {
            driver= new FirefoxDriver();
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;


    }


    protected boolean clickItemJS(WebElement element, String elementName){
        try{
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            return true;
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }

    }









}
