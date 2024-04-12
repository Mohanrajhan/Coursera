package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	Actions act;
	

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//input[@role='textbox']")
	WebElement searchInputBox;
	
	@FindBy(xpath="(//div[@class='magnifier-wrapper'])[2]")
	WebElement searchIcon;
	
	@FindBy(xpath = "//a[text()='For Enterprise']")
	WebElement forEnterprise;
	
	@FindBy(xpath="//div[@role='navigation']/a[1]")
	WebElement forIndividuals;
	
	public void search(String value)
	{
		searchInputBox.sendKeys(value);
		searchIcon.click();
	}
	
	
	public void clearSearchBox()
	{
		act = new Actions(driver);
		searchInputBox.click();
		act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
		act.sendKeys(Keys.BACK_SPACE).perform();
	}
	
	public void goToEnterprise()
	{
		jse.executeScript("arguments[0].scrollIntoView;",forEnterprise);
		jse.executeScript("arguments[0].click();",forEnterprise);
	}
	public void clickForIndividuals() throws InterruptedException
	{
		act = new Actions(driver);
		act.sendKeys(Keys.HOME).perform();
		Thread.sleep(5000);
		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		myWait.until(ExpectedConditions.elementToBeClickable(forIndividuals));
		forIndividuals.click();
	}
	
	
	

}
