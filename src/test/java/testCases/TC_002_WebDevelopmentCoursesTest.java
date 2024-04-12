package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testBase.TestBaseClass;

public class TC_002_WebDevelopmentCoursesTest extends TestBaseClass{

	@Test
	@Parameters({"Language","Level"})
	public void getCourseDetails(String language, String level)
	{
		logger.info("**** Starting TC_002_WebDevelopmentCoursesTest  ****");
		
		try
		{
			moreLanguagesPage.applyLanguageFilter(language);
			searchResultPage.applyLevelFilter(level);
			for(String result : searchResultPage.getsearchResults())
			{
				System.out.println(result);
				System.out.println("--------------------\n");
			}
			Assert.assertTrue(true);
			
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail("An exception occured:"+ e.getMessage());
		}
		logger.info("**** Finished TC_002_WebDevelopmentCoursesTest  ****");
	}
}
