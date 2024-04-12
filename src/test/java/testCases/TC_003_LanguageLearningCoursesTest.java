package testCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testBase.TestBaseClass;

public class TC_003_LanguageLearningCoursesTest extends TestBaseClass{

	@Test
	@Parameters({"Course2"})
	public void getCourse2Details(String courseName)
	{
		logger.info("**** TC_003_LanguageLearningCoursesTest  ****");
		
		try
		{
			homePage.clearSearchBox();
			homePage.search(courseName);
			searchResultPage.showMore();
			for(Map.Entry<String,String> entry: moreLanguagesPage.getlevelsForAllLanguages().entrySet())
			{
				System.out.println("Language Name:"+entry.getKey()+"\n");
				System.out.println("Levels:\n"+entry.getValue()+"\n-------------------");
			}
			
			Assert.assertTrue(true);
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail("An exception occured:"+ e.getMessage());
		}
		logger.info("**** Finished TC_003_LanguageLearningCoursesTest  ****");
	}
}
