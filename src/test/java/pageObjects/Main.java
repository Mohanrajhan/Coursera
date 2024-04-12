package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main
{
	static WebDriver driver ;
	
	

	
	
	public static void main(String[]args) throws InterruptedException
	{
		//WebDriverManager.chromedriver().setup();
		
		//navigate
		driver = new ChromeDriver();
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		Actions action = new Actions(driver);
		driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
		Actions act = new Actions(driver);
		//seach text
	    driver.findElement(By.xpath("//input[@role='textbox']")).sendKeys("Web Development Courses");
		//search button
		driver.findElement(By.xpath("(//div[@class='magnifier-wrapper'])[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='English']")).click();
		WebElement element=driver.findElement(By.xpath("//span[text()='Graduate Certificates']"));
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView();",element);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Beginner']")).click();
		jse.executeScript("window.scrollBy(0, - document.body.scrollHeight)");
		Thread.sleep(3000);
		List<WebElement> courses=driver.findElements(By.xpath("//ul[1]//h3[@class='cds-CommonCard-title css-6ecy9b']"));
		List<WebElement> time=driver.findElements(By.xpath("//ul[1]//div[@class='cds-CommonCard-metadata']/p"));
		List<WebElement> rating=driver.findElements(By.xpath("//ul[1]//p[@class='css-2xargn']"));

		System.out.println("Course Details :");
		for(int i=0;i<courses.size();i++) {
			int j = i+1;
			
			String rating1 = "(//ul[1]//p[@class='css-2xargn'])['%s']";
			
			String durationXpath = "(//ul[1]//div[@class='cds-CommonCard-metadata']/p)['%s']";
			
			String result = "Name : "+ courses.get(i).getText() + "\n" +
					"Rating : "+ driver.findElement(By.xpath(String.format(rating1, String.valueOf(j)))).getText() + "\n";
			         
	       String arr[]= driver.findElement(By.xpath(String.format(durationXpath, String.valueOf(j)))).getText().split("·");
	
	       result = result+"Level : "+arr[0] + "\n" +"Type : "+arr[1] + "\n" + "Duration : "+arr[2];
	
	        System.out.println(result);
	
			System.out.println("");

		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='search-filter-group-Language']//span[text()='English']"))).click();
		WebElement inputBox = driver.findElement(By.xpath("//input[@role='textbox']"));
		inputBox.click();
		act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
		act.sendKeys(Keys.BACK_SPACE).perform();
		
		driver.findElement(By.xpath("//input[@role='textbox']")).sendKeys("Language Learning");
		
		driver.findElement(By.xpath("(//div[@class='magnifier-wrapper'])[2]")).click();

		driver.findElement(By.xpath("//div[@data-testid='search-filter-group-Language']//button/span[text()='Show more']")).click();
		
		
		wait.until((x)->
		{
			return !x.findElement(By.xpath("//div[@role='status']")).getText().equals("Loading search results");
		});
		
		System.out.println(driver.findElement(By.xpath("//div[@role='status']")).getText());
		
		List<WebElement> listLang = driver.findElements(By.xpath("//div[@class='css-14j9q5y']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span"));
		
		
		for(int i =1;i<=listLang.size();i++)
		{
			System.out.println(i);
			
			driver.findElement(By.xpath("//span[text()='Clear All']")).click();
			
		    String[] currentLanguage = new String[1];
		    
		    currentLanguage[0] = driver.findElement(By.xpath("(//div[@class='css-14j9q5y']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span)["+i+"]")).getText().replaceAll("[^a-zA-Z]","");
		
		    driver.findElement(By.xpath("(//div[@class='css-14j9q5y']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span)["+i+"]")).click();
		    
		    System.out.println(currentLanguage[0]);
	         
			driver.findElement(By.xpath("//span[text()='Apply']")).click();
			
			
				wait.until((driver)->
				{
					String filterLanguage="";
				while(true)
				{
					try
					{
				 filterLanguage = driver.findElement(By.xpath("(//div[@data-testid='search-filter-group-Language']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span)[1]")).getText().replaceAll("[^a-zA-Z]","");
				break;
					}
					catch(StaleElementReferenceException e)
					{
						
					}
				}
				return filterLanguage.equals(currentLanguage[0]);
				
				});
				
				
				
			
			List<WebElement> levelList = driver.findElements(By.xpath("//div[@data-testid='search-filter-group-Level']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span"));
				
				
				for(WebElement level : levelList)
				{
					System.out.println(level.getText());
				}
				
		     System.out.println(".............................");
		     
		     driver.findElement(By.xpath("//div[@data-testid='search-filter-group-Language']//button/span[text()='Show more']")).click();
		     
		      
			
		}
		
		driver.findElement(By.xpath("//img[@class='rc-CourseraLogo']")).click();
				jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//p[text()='Coursera']")));
				driver.findElement(By.xpath("//a[text()='For Enterprise']")).click();
				jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//div[@class='css-10bkxld']/preceding-sibling::div[text()='Tim C.']")));
				driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Dhina");
				driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("G");
				driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("abc@sonatech.ac.in");
				driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys("9865455311");
				try {
				driver.findElement(By.id("Company")).sendKeys("Cognizant");
				WebElement size=driver.findElement(By.id("Employee_Range__c"));
				Select csize=new Select(size);
				csize.selectByVisibleText("501-1000");
				}
				catch(Exception e) {
				}
				driver.findElement(By.xpath("//input[@name='Title']")).sendKeys("qea");
				WebElement num =driver.findElement(By.id("Self_reported_employees_to_buy_for__c"));
				Select total=new Select(num);
				total.selectByVisibleText("26-100");
				driver.findElement(By.id("mktoCheckbox_92958_0")).click();
				WebElement coun =driver.findElement(By.id("Country"));
				Select country=new Select(coun);
				country.selectByVisibleText("India");
				WebElement st =driver.findElement(By.id("State"));
				Select state=new Select(st);
				state.selectByVisibleText("Tamil Nadu");
				WebElement feed =driver.findElement(By.id("What_the_lead_asked_for_on_the_website__c"));
				Select fback=new Select(feed);
				fback.selectByVisibleText("Billing/invoice support");
				jse.executeScript("arguments[0].scrollIntoView();",st);
				driver.findElement(By.xpath("//button[@class='mktoButton']")).click();
//				Thread.sleep(10000);
				 boolean result = true;
				    while(result) {
				        try {
				            WebElement finalMsg = driver.findElement(By.xpath("//h1[contains(text(),'Business')]"));
				            if(finalMsg.isDisplayed()) {
								System.out.println(finalMsg.getText());
							}
							else {
								WebElement error=driver.findElement(By.xpath("//div[@class='mktoErrorMsg']"));
								System.out.println(error.getText());
							}
				            result = false;
				            break;
				        } catch(StaleElementReferenceException e) {
				        }
				    }

	} 
		
}
