package runner;

/**
 * Created by murthi on 05-08-2016.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        features = {"classpath:sample.feature"},
        plugin = {"json:target/cucumber-parallel/cucumber.json", "html:target/cucumber","rerun:target/cucumber/rerun.txt"},
        monochrome = true,
        glue = { "framework.controller","stepdefinitions" })
public class CukeRunner {
}
