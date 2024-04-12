package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

	WebDriver driver;
	Properties properties;
	
	@Before
	public void setup() throws IOException
	{
		driver = BaseClass.initializeBrowserDriver();
		properties = BaseClass.getProperties();
		driver.get(properties.getProperty("CourseraURL"));
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	@AfterStep
    public void addScreenshot(Scenario scenario) {
             	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);  
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
	}
      
	
	
	
}
