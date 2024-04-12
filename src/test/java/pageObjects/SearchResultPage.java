package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasePage {

	public SearchResultPage(WebDriver driver) {
		super(driver);
		
	}
	int size = 0;
	
	@FindBy(xpath = "//div[@data-testid = 'search-filter-group-Level']")
	WebElement levelFilter;
	
	
	@FindBy(xpath = "//div[@class='cds-CommonCard-ratings']")
	WebElement rating;
	
	@FindBy(xpath = "//div[@class='cds-ProductCard-footer']")
	WebElement duration;
	
	@FindBy(xpath = "//div[@data-testid='search-filter-group-Language']//button/span[text()='Show more']")
	WebElement showMoreButton;
	
	@FindBy(xpath = "//div[@data-testid='search-filter-group-Level']")
	WebElement level;
	
	@FindBy(xpath = "//img[@class='rc-CourseraLogo']")
	WebElement toHome;
	
	
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	public void applyLevelFilter(String level)
	{
		levelFilter.findElement(By.xpath("//span[text()='"+level+"']")).click();
	}
	
	
	public List<String> getsearchResults()
	{
		List<String> results = new ArrayList<>();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='active-filter-items']")));
		
		List<WebElement> courses = driver.findElements(By.xpath("//h3[@class='cds-CommonCard-title css-6ecy9b']"));
		
		for(int i=0; i<courses.size(); i++)
		{
			int j=i+1;
			
			String result = "Name : "+ courses.get(i).getText() + "\n" +
					         "Rating : "+ rating.findElement(By.xpath("(//p[@class='css-2xargn'])["+j+"]")).getText() + "\n";
			String arr[] = new String[courses.size()+1]; 
			
			arr= duration.findElement(By.xpath("(//div[@class='cds-CommonCard-metadata'])["+j+"]")).getText().split("·");
			
			result = result+"Level : "+arr[0] + "\n" +"Type : "+arr[1] + "\n" + "Duration : "+arr[2];
			
			results.add(result);
		}
		
		return results;
	}
	
	
	public void showMore()
	{
		jse.executeScript("arguments[0].click();",showMoreButton);
	}
	
	
	
	public String getLevels()
	{
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='active-filter-items']")));
		
		List<WebElement> levelsList = level.findElements(By.xpath("//div[@data-testid='search-filter-group-Level']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span"));
		
		String variousLevels = "";
		
		for(WebElement level : levelsList)
		{
			variousLevels = variousLevels+(level.getText()+"\n");
		}
		
		return variousLevels;
	}
	
	
	public void navigateHome()
	{
		toHome.click();
	}
	
	
	

}
