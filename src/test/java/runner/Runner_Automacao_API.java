package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature",
		glue = "stepsDefinitions",
		tags = "@Automacao_Cenarios_API",
		plugin = {"pretty","html:target/report-html.html", "json:target/report.json"},
		monochrome = true)

public class Runner_Automacao_API {

}
