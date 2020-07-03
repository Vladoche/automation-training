package com.wildcodeschool;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static com.wildcodeschool.Hooks.*;
import static org.junit.Assert.*;

public class HomeStepdefs {
    WebElement tab;

    @Given("user is connected to {string}")
    public void userIsConnectedTo(String arg0) {
        driver.get(arg0);
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("etudiant2");
        driver.findElement(By.id("passWord")).clear();
        driver.findElement(By.id("passWord")).sendKeys("Etudiant2*");
        driver.findElement(By.id("domainName")).clear();
        driver.findElement(By.id("domainName")).sendKeys("telco");
        driver.findElement(By.cssSelector("#flogin > input[type=submit]")).click();
        WebElement iconHome = driver.findElement(By.cssSelector("#root > div > section > aside > div > div > div.module-links-list > a:nth-child(1) > div"));
        assertTrue(iconHome.isDisplayed());
    }

    @When("user clicks on button {string} with {string}")
    public void userClicksOnButton(String arg0, String arg1) throws InterruptedException {
        String cssRoot = "#root > div > section > section > div > main > div > div > div.shortcuts-section > div.shortcuts";
        driver.findElement(By.cssSelector(cssRoot+arg1)).click();
    }

    @Then("user is redirected to right page {string}")
    public void userIsRedirectedToRightPage(String arg0) throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        int size = tabs.size();
        //array 0 ---> physiquement tab n1 = tab[0] / tab n2 = tab[1]
        driver.switchTo().window(tabs.get(size-1));
        assertTrue(driver.getCurrentUrl().contains(arg0));
    }

    @When("user disconnects from platform")
    public void userDisconnectsFromPlatform() {
        driver.findElement(By.cssSelector("#root > div > section > section > div > header:nth-child(2) > div > div > i")).click();
        driver.findElement(By.cssSelector("body > div:nth-child(8) > div > div > ul > li:nth-child(2)")).click();
    }

    @Then("user is redirected to Login page")
    public void userIsRedirectedToLoginPage() {
        assertTrue(driver.getCurrentUrl().contains("login"));
    }


    @Then("items inside tab are hidden")
    public void itemsInsideTabAreHidden() throws InterruptedException {
        Thread.sleep(5000);
        assertEquals("false", tab.getAttribute("aria-expanded"));
    }


    @When("I collapse {string}")
    public void iCollapse(String arg0) {
        tab = driver.findElement(By.cssSelector("#root > div > section > section > div > main > div > div > div.home-sections > div:nth-child("+arg0+") > div > div.ant-collapse-header"));
        tab.click();
    }
}
