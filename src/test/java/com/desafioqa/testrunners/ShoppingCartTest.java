package com.desafioqa.testrunners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/com/desafioqa/features",
        glue={"com/desafioqa/stepdefinitions", "com/desafioqa/hooks"},
        plugin={"pretty"}
)
public class ShoppingCartTest {
}