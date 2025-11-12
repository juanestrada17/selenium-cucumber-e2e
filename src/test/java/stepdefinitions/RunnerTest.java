package stepdefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        tags = "@RentNotProvided",
        plugin = {
                "pretty",
                "json:Report/cucumber.json",
                "junit:Report/cucumber.junit",
                "html:Report/cucumber.html",
                "junit:Report/cucumber.xml"
        },
        publish = false
)
public class RunnerTest {
}
