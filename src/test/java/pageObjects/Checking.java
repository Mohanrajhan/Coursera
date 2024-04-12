package pageObjects;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DataReader;

public class Checking {
	
	public static WebDriver driver=new ChromeDriver();
	
	public static void main(String[]args) throws InterruptedException
	{
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
		driver.get("https://Coursera.org/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		HomePage h = new HomePage(driver);
		MoreLanguagesPage mp = new MoreLanguagesPage(driver);
		SearchResultPage sr = new SearchResultPage(driver);
		EnterprisePage ep = new EnterprisePage(driver);
		
		h.search("WebDevelopment Courses");
		
		sr.showMore();
		
		System.out.println(mp.getNoofLanguagesAvailable());
		
		mp.applyLanguageFilter("English");
		
		sr.applyLevelFilter("Beginner");
		
		for(String s : sr.getsearchResults())
		{
			System.out.println(s);
			System.out.println("--------------------\n");
		}
		
		h.clearSearchBox();
		
//		h.search("Language Learning");
		
//		sr.showMore();
		
//		mp.applyLanguageFilter("Turkish");
//		Map<String,String> levels=mp.getlevelsForAllLanguages();
//		for(Map.Entry<String,String> entry: levels.entrySet())
//		{
//			System.out.println("Language Name:"+entry.getKey()+"\n");
//			System.out.println("Levels:\n----------------"+entry.getValue());
//		}
//		System.out.println(sr.getLevels());
		
		sr.navigateHome();
		
		h.goToEnterprise();
		ep.setFirstName("Dhina");
		ep.setLastName("G");
		ep.setEmail("dhinag@sonatech.ac.in");
		ep.setPhoneNo("7487398933");
		ep.setCompanyName("CTS");
		ep.setCompanysize("501-1000");
		ep.setJobTitle("QEA");
		ep.selectNumOfLearners("26-100");
		ep.clickCheckBox();
		ep.selectCountry("India");
		ep.selectState("Tamil Nadu");
		ep.selectNeeds("Billing/invoice support");
		ep.submitBtn();
		ep.getMessage();
//		driver.quit();
	}

}
