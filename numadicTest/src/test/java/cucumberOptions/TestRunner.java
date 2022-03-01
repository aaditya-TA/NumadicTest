package cucumberOptions;


import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.AfterClass;
import java.io.File;


@CucumberOptions(
		plugin =  {"com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport/Report_Chrome.html"},
	    features = "src/test/resources/features",
	    glue="step_definitions")

public class TestRunner extends AbstractTestNGCucumberTests  {

	@AfterClass
	public static void writeExtentReport() throws InterruptedException {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "//extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", 	"Windows 10" + "64 Bit");
		Reporter.setSystemInfo("Selenium", "4.1.2");
		Reporter.setSystemInfo("Maven", "4.0.0");
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
	}

}
