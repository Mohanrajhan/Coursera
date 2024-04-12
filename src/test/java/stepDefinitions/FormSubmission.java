package stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.EnterprisePage;
import pageObjects.HomePage;
import utilities.DataReader;

public class FormSubmission {
	WebDriver driver;
	HomePage homePage;
	EnterprisePage enterprisePage;
	
	List<HashMap<String,String>> dataMap;
	@Given("the user navigate to the For Enterprise page")
	public void the_user_navigate_to_the_For_Enterprise_page()
	{
		homePage = new HomePage(BaseClass.getDriver());
		homePage.goToEnterprise();
	}
	
	@When("the user enter details {string} and submit the form")
	public void the_user_enter_details_and_submit_the_form(String rownum)
	{
		enterprisePage = new EnterprisePage(BaseClass.getDriver());
		dataMap = DataReader.data(System.getProperty("user.dir")+"/testData/EnterpriseFormFillingData.xlsx","FormData");
		int index = Integer.parseInt(rownum)-1;
		HashMap<String,String> HashData = dataMap.get(index);
		enterprisePage.setFirstName(HashData.get("First Name"));
		enterprisePage.setLastName(HashData.get("Last Name"));
		enterprisePage.setEmail(HashData.get("Email"));
		enterprisePage.setPhoneNo(HashData.get("Phone No"));
		enterprisePage.setCompanyName(HashData.get("Company"));	
		enterprisePage.setCompanysize(HashData.get("Company Size"));
		enterprisePage.setJobTitle(HashData.get("Job Title"));
		enterprisePage.selectNumOfLearners(HashData.get("Expected No of Learners"));
		enterprisePage.clickCheckBox();
		enterprisePage.selectCountry(HashData.get("Country"));
		enterprisePage.selectState(HashData.get("State"));
		enterprisePage.selectNeeds(HashData.get("Needs"));
		enterprisePage.submitBtn();
	}
	
	@Then("it displays either error msg or final msg based on input")
	public void it_displays_either_error_msg_or_final_msg_based_on_input()
	{
		enterprisePage.getMessage();
	}
}
