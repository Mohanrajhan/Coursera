package pageObjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoreLanguagesPage extends BasePage {
	
	public MoreLanguagesPage(WebDriver driver) {
		super(driver);
		
	}
	
	
	SearchResultPage sr = new SearchResultPage(driver);
     
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	
	@FindBy(xpath="//div[@aria-label='Select Language options']//span[@class='cds-247']")
	List<WebElement> languages; 
	
	@FindBy(xpath="//div[@aria-label='Select Language options']")
	WebElement languageDiv;
	
	@FindBy(xpath="//span[text()='Clear All']")
	WebElement clearAll;
	
	@FindBy(xpath="//span[text()='Apply']")
	WebElement apply;
	
	
	@FindBy(xpath="//div[@role='status']")
	WebElement loadingStatus;
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	
	public int getNoofLanguagesAvailable()
	{
	
		wait.until(ExpectedConditions.visibilityOfAllElements(languages));
		
		wait.until(new Function<WebDriver, Object>() {
			@Override
			public Object apply(WebDriver x) {
				return !loadingStatus.getText().equals("Loading search results");
			}
		});
		
		
		return languages.size();
		
	}
	
	
	
	public void applyLanguageFilter(String language)
	{
	       
			
           clearAll.click();
           
           languageDiv.findElement(By.xpath("//div[@class='css-14j9q5y']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span[text()='"+language+"']")).click();
			
		    apply.click();
		    	
	}
	
	public Map<String,String> getlevelsForAllLanguages()
	{
		Map<String,String> languageMap = new HashMap<>();
		
		int noOfLanguages = getNoofLanguagesAvailable();
		
		for(int i=1;i<=noOfLanguages;i++)
		{
		clearAll.click();
        
        WebElement language = languageDiv.findElement(By.xpath("(//div[@class='css-14j9q5y']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span)["+i+"]"));
        
        String name = language.getText();
        
        language.click();
        	
		apply.click();
		
		String level = sr.getLevels();
		
		
		if(i<noOfLanguages)
		{
		sr.showMore();
		}
		
		languageMap.put(name,level);
		}
		
		return languageMap;
	}
	
	
		

}
