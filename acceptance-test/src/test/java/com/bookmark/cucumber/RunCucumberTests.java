package com.bookmark.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", strict = true,
        plugin = {"json:/target/cucumber/bookmarks.json","junit:target/cucumber/bookmark.xml"},
    tags = {"@get-bookmarks"}, glue = "classpath:com.bookmark.cucumber")
public class RunCucumberTests {
}
