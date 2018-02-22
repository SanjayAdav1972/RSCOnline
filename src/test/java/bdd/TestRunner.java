package bdd;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:format/"}, 
			features = "src/test/java/bdd/",
			glue = {"bdd/"})
public class TestRunner {

}
