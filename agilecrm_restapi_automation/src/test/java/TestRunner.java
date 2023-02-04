import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags="@UpdateUser",
        features = "src/test/resources/features",
        glue="stepdefinition"
)
public class TestRunner {
}
