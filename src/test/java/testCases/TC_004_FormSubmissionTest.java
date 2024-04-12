package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import factory.BaseClass;
import pageObjects.EnterprisePage;
import pageObjects.HomePage;
import pageObjects.SearchResultPage;
import testBase.TestBaseClass;
import utilities.DataProviders;


public class TC_004_FormSubmissionTest extends TestBaseClass{

	@Test(dataProvider="formData",dataProviderClass=DataProviders.class)
	public void getMessage(String firstName,String lastName,String email,String phoneNo,String companyName,String companySize,String jobTitle,String noOfLearners,String country,String state,String needs,String result)
	{
		logger.info("**** Starting TC_004_FormSubmissionTest  ****");
		
		homePage = new HomePage(driver);
		
		try
		{
//			searchResultPage = new SearchResultPage(driver);
//			searchResultPage.navigateHome();
			homePage.clickForIndividuals();
			homePage.goToEnterprise();
			enterprisePage = new EnterprisePage(BaseClass.getDriver());
			enterprisePage.setFirstName(firstName);
			enterprisePage.setLastName(lastName);
			enterprisePage.setEmail(email);
			enterprisePage.setPhoneNo(phoneNo);
			enterprisePage.setCompanyName(companyName);
			enterprisePage.setCompanysize(companySize);
			enterprisePage.setJobTitle(jobTitle);
			enterprisePage.selectNumOfLearners(noOfLearners);
			enterprisePage.clickCheckBox();
			enterprisePage.selectCountry(country);
			enterprisePage.selectState(state);
			enterprisePage.selectNeeds(needs);
			enterprisePage.submitBtn();
			enterprisePage.getMessage();
			
			
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail("An exception occured:"+ e.getMessage());
		}
		logger.info("**** Finished TC_004_FormSubmissionTest  ****");
	}
}
