package com.wildcodeschool;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.wildcodeschool.Hooks.*;

import static org.junit.Assert.assertTrue;

public class TeamStepdefs {

    @Given("user navigates to {string}")
    public static void userNavigatesTo(String url) {
        driver.get(url);
    }

    @When("user enters {string} {string}")
    public void userEnters(String field, String value) {
        driver.findElement(By.id(field)).clear();
        driver.findElement(By.id(field)).sendKeys(value);
    }

    @When("user clicks on Connect button")
    public void userClicksOnConnectButton() {
        driver.findElement(By.cssSelector("#flogin > input[type=submit]")).click();
    }

    @Then("user is redirected to One2Team homepage")
    public void userIsRedirectedToOneTeamHomepage() {
        WebElement iconHome = driver.findElement(By.cssSelector("#root > div > section > aside > div > div > div.module-links-list > a:nth-child(1) > div"));
        assertTrue(iconHome.isDisplayed());
    }


}
