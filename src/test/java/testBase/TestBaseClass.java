package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import factory.BaseClass;
import pageObjects.EnterprisePage;
import pageObjects.HomePage;
import pageObjects.MoreLanguagesPage;
import pageObjects.SearchResultPage;

public class TestBaseClass {

	public static HomePage homePage;
	public static EnterprisePage enterprisePage;
	public static SearchResultPage searchResultPage;
	public static MoreLanguagesPage moreLanguagesPage;
	public static WebDriver driver;
	static Properties properties;
	public static Logger logger;
	
	@BeforeTest
	public void setup() throws IOException
	{
		driver = BaseClass.initializeBrowserDriver();
		properties = BaseClass.getProperties();
		logger = BaseClass.logger();
		driver.get(properties.getProperty("CourseraURL"));
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	public String screenShot(String testName)
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + testName + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}

}
