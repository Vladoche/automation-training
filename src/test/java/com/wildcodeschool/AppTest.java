package com.wildcodeschool;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","json:target/json/results.json","html:target/html/results.html"},
        features = {"src/test/java/com/wildcodeschool"},
        glue = {"com.wildcodeschool"},
        tags = "@livecoding and not @notexecute"
)

class AppTest
{ }
