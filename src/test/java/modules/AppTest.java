package modules;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","json:target/json/results.json","html:target/html/results.html"},
        features = {"src/test/resources/features"},
        glue = {"steps_definitions","modules"},
        tags = "@frame and not @no-run"
)

class AppTest
{ }
