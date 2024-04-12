package stepDefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.MoreLanguagesPage;
import pageObjects.SearchResultPage;

public class SearchCourses {
	WebDriver driver;
	HomePage homePage;
	MoreLanguagesPage moreLanguagesPage;
	SearchResultPage searchResultPage;

	@Given("the user enters course name as {string} and click on search button")
	public void the_user_enters_course_name_as_and_click_on_search_button(String courseName)
	{
		homePage = new HomePage(BaseClass.getDriver());
		homePage.search(courseName);
	}
	
	@When("the user click on show more in the language section")
	public void the_user_click_on_show_more_in_the_language_section()
	{
		searchResultPage = new SearchResultPage(BaseClass.getDriver());
		searchResultPage.showMore();	
	}
	
	@Then("get the total number of languages displayed")
	public void get_the_total_number_of_languages_displayed()
	{
		moreLanguagesPage = new MoreLanguagesPage(BaseClass.getDriver());
		System.out.println(moreLanguagesPage.getNoofLanguagesAvailable());
	}
	
	@When("the user clicks the language as {string}")
	public void the_user_clicks_the_language_as(String language)
	{
		moreLanguagesPage.applyLanguageFilter(language);
	}
	
	@When("the user clicks the level as {string}")
	public void the_user_clicks_the_level_as(String level)
	{
		searchResultPage.applyLevelFilter(level);
	}
	
	@Then("get the courses details such as course name, rating, level, course type and duration")
	public void get_the_courses_details()
	{
		for(String result :searchResultPage.getsearchResults())
		{
			System.out.println(result);
			System.out.println("--------------------\n");
		}
	}
	
	@When("the user searches for {string} then click search button and the user click on show more option")
	public void the_user_searches_for_then_click_search_button_and_the_user_click_on_show_more_option(String courseName)
	{
		homePage.clearSearchBox();
		homePage.search(courseName);
		searchResultPage.showMore();
	}

	@Then("get the language and levels count displayed")
	public void get_the_language_and_levels_count_displayed()
	{
		for(Map.Entry<String,String> entry: moreLanguagesPage.getlevelsForAllLanguages().entrySet())
		{
			System.out.println("Language Name:"+entry.getKey()+"\n");
			System.out.println("Levels:\n"+entry.getValue()+"\n-------------------");
		}
		searchResultPage.navigateHome();
	}

}
