package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MoreLanguagesPage;
import pageObjects.SearchResultPage;
import testBase.TestBaseClass;

public class TC_001_LanguageCountTest extends TestBaseClass{

	@Test
	@Parameters({"Course"})
	public void getNoofLanguagesAvailable(String courseName)
	{
		homePage = new HomePage(driver);
		searchResultPage = new SearchResultPage(driver);
		moreLanguagesPage = new MoreLanguagesPage(driver);
		logger.info("**** Starting TC_001_LanguageCountTest  ****");
		
		try
		{
			homePage.search(courseName);
			searchResultPage.showMore();
			System.out.println(moreLanguagesPage.getNoofLanguagesAvailable());
			Assert.assertEquals(23,moreLanguagesPage.getNoofLanguagesAvailable());
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail("An exception occured:"+ e.getMessage());
		}
		logger.info("**** Finished TC_001_LanguageCountTest  ****");
	}
}
