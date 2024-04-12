package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
					features = {"C:\\Users\\2318604\\eclipse-workspace\\CourseraHP\\features\\Coursera.feature"},
					//features ={"@target/rerun.txt"},
					glue = "stepDefinitions",
					plugin = {
							"pretty", "html:reports/myreport.html", 
							  "rerun:target/rerun.txt",
							  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
							},
					
					dryRun = false,
					monochrome = true,
					publish = true
		
				)
public class TestRunner {

}
