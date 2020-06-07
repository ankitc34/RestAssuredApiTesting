package Cucumber.Options;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src//test//java//features",
		plugin ="json: jsonReports/cucumber-report.json",
		glue = {"stepDefinitions"}
		//tags = "@DeletePlace"
		)
public class TestRunner {

}
