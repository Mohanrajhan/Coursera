Feature: courses details and form submission

	Scenario: Get the Courses Details based on the given input
		Given the user enters course name as "Web Development Courses" and click on search button
		When the user click on show more in the language section
		Then get the total number of languages displayed
		When the user clicks the language as "English"
		When the user clicks the level as "Beginner"
		Then get the courses details such as course name, rating, level, course type and duration
		When the user searches for "Language Learning" then click search button and the user click on show more option
		Then get the language and levels count displayed
		
	Scenario Outline: Form Submission With Valid or Invalid Input
    Given the user navigate to the For Enterprise page
    When the user enter details "<row_index>" and submit the form
    Then it displays either error msg or final msg based on input

    Examples: 
      | row_index |
      |         1 |
      |         2 |