package pageObjects;
 
 
import java.time.Duration;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class EnterprisePage extends BasePage 
{
	public EnterprisePage(WebDriver driver) 
   {
		super(driver);
	}
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement firstName;
	@FindBy(xpath="//input[@placeholder='Last Name']")
	WebElement lastName;
	@FindBy(xpath="//input[@name='Email']")
	WebElement email;
	@FindBy(xpath="//input[@name='Phone']")
	WebElement phoneNo;
	@FindBy(id="Company")
	WebElement company;
	@FindBy(id="Employee_Range__c")
	WebElement companySize;
	@FindBy(xpath="//input[@name='Title']")
	WebElement jobTitle;
	@FindBy(id="Self_reported_employees_to_buy_for__c")
	WebElement numOfLearners;
	@FindBy(id="mktoCheckbox_92958_0")
	WebElement checkBoxClick;
	@FindBy(id="Country")
	WebElement countrySelect;
	@FindBy(id="State")
	WebElement stateSelect;
	@FindBy(id="What_the_lead_asked_for_on_the_website__c")
	WebElement needsSelect;
	@FindBy(xpath="//button[@class='mktoButton']")
	WebElement submitBtn;
	@FindBy(xpath="//h1[contains(text(),'Business')]")
	WebElement finalMsg;
	@FindBy(xpath="//div[@class='mktoErrorMsg']")
	WebElement errorMsg;
	@FindBy(xpath="//iframe[@class='drift-frame-controller']")
	WebElement chatBotFrame;
	@FindBy(xpath="//button[@aria-label = 'Close Drift Widget messenger preview overlay']")
	WebElement closeButton;
	@FindBy(xpath="//div[@class='mktoGutter mktoHasWidth']/following-sibling::input")
	List<WebElement> formInputElements;
	@FindBy(tagName="select")
	List<WebElement> formselectElements;
 
	@FindBy(xpath="//h1[text()='Thanks for your interest in Coursera for Business']")
	WebElement successMessage;
	@FindBy(xpath="//div[@class='mktoFieldWrap mktoRequiredField selectParent']")
	WebElement inputTextSelect;
	@FindBy(id="ValidMsgEmail")
	WebElement workEmailError;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));

	JavascriptExecutor jse = (JavascriptExecutor)driver;
	Actions act=new Actions(driver);
 
 
	public void handleMessage()
	{
		try
		{
			WebElement messageFrame = wait.until(ExpectedConditions.visibilityOf(chatBotFrame));
			driver.switchTo().frame(messageFrame);
			wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
		}
		catch(Exception e)
		{
		}
 
	    driver.switchTo().defaultContent();
	}

	public void setFirstName(String FirstName)
	{
		jse.executeScript("arguments[0].scrollIntoView();",firstName);
	    firstName.sendKeys(FirstName);
	}
	public void setLastName(String LastName)
	{
		lastName.sendKeys(LastName);
	}
	public void setEmail(String EMail)
	{
		email.sendKeys(EMail);
	}
	public void setPhoneNo(String phoneNumber)
	{
		phoneNo.sendKeys(phoneNumber);
	}
	public void setCompanyName(String companyName)
	{
		try 
	       {
				company.sendKeys(companyName);
			}
			catch(NoSuchElementException e)
	       {
			}
	}
	public void setCompanysize(String companysize)
	{
		try 
	       {
				Select size=new Select(companySize);
				size.selectByVisibleText(companysize);
			}
			catch(Exception e) 
	       {
				
	       }
	}
	
	public void setJobTitle(String jobtitle)
	{
		jobTitle.sendKeys(jobtitle);
	}
	public void selectNumOfLearners(String noOfLearners)
	{
		Select numberOfLearners=new Select(numOfLearners);
		numberOfLearners.selectByVisibleText(noOfLearners);
	}
	public void clickCheckBox()
	{
		checkBoxClick.click();
	}
	public void selectCountry(String Country)
	{
		Select country=new Select(countrySelect);
		country.selectByVisibleText(Country);
	}
	public void selectState(String State)
	{
		try 
	       {
				Select state=new Select(stateSelect);
				state.selectByVisibleText(State);
			}
			catch(NoSuchElementException e) {
			}
	}
	public void selectNeeds(String Needs)
	{
		Select needs=new Select(needsSelect);
		needs.selectByVisibleText(Needs);
	}
	public void submitBtn() 
   {
		handleMessage();
		submitBtn.click();
   }
	
	public void successMessage()
	{
		while(true) 
        {
			try {
			   if(successMessage.isDisplayed()) 
                {
					System.out.println(successMessage.getText());
					break;
				}
		     } 
		    catch(StaleElementReferenceException e)
		    {
		    }
		}
	}
	
	
	public boolean workEmailCheck()
	{
		try
		{
			System.out.println("Error found for input element: Email" + "\n" + "Error message displayed: " + workEmailError.getText() + "\n");
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
 
   public void getMessage() 
   {
	   boolean flag = workEmailCheck();
       try
       {
			while(true) 
           {
			    try {
			            if(successMessage.isDisplayed()) 
                       {
							System.out.println(successMessage.getText());
							break;
						}
			        } 
               catch(StaleElementReferenceException e)
               {
			    }
			}
       }
       catch(NoSuchElementException e)
       {
           List<WebElement> inputElements = formInputElements;
           for (int i = 0; i<inputElements.size() ;i++) 
           {
               WebDriverWait wait_2 = new WebDriverWait(driver,Duration.ofSeconds(3));
               WebElement error = null;
               WebElement inputElement = inputElements.get(i);
               if(i==0)
               {
            	   jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", inputElement);
               }
               inputElement.click();
               try 
               {
                   error = wait_2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='"+inputElement.getAttribute("id")+"']/parent::*/div[@class='mktoError']")));
                   System.out.println("Error found for input element: " + inputElement.getAttribute("id"));
                   System.out.println("Error message displayed: " + error.getText());
                   if(i>0)
                   {
                       inputElements.get(i-1).click();
                   }
               }
               catch (NoSuchElementException | TimeoutException f) 
               {
            	   if(inputElement.getAttribute("id").equalsIgnoreCase("Email") && flag)
                   {
                   	continue;
                   }
                   System.out.println("No error found for input element: " + inputElement.getAttribute("id"));
               }
               System.out.println();
           }
           for (int i = 0; i<formselectElements.size() ;i++) 
           {
               WebDriverWait wait_2 = new WebDriverWait(driver,Duration.ofSeconds(3));
               WebElement error = null;
               WebElement inputElement = formselectElements.get(i);
 
               act.doubleClick(inputElement).perform();
 
               try 
               {
                   error = wait_2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='"+inputElement.getAttribute("id")+"']/parent::*/div[@class='mktoError']")));
                   WebElement textLabel = inputTextSelect.findElement(By.xpath("//select[@id='"+inputElement.getAttribute("id")+"']/parent::div[@class='mktoFieldWrap mktoRequiredField selectParent']/label"));
                   System.out.println("Error found for input element: " + textLabel.getText());
                   System.out.println("Error message displayed: " + error.getText());
 
               }
               catch (NoSuchElementException | TimeoutException f) 
               {
                   System.out.println("No error found for input element: " + inputTextSelect.findElement(By.xpath("//select[@id='"+inputElement.getAttribute("id")+"']/parent::div[@class='mktoFieldWrap mktoRequiredField selectParent']/label")).getText());
               }
               System.out.println();
           }
 
       }
	}
}