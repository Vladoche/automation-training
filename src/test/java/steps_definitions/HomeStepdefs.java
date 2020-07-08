package steps_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static modules.Hooks.*;
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
    public void userDisconnectsFromPlatform() throws InterruptedException {
        Thread.sleep(5000);
        WebElement divRoot = driver.findElement(By.cssSelector("#root > div > section > section > div > header:nth-child(2) > div > div"));
        while (divRoot.getAttribute("class").equals("app-bar-profile-picture ant-dropdown-trigger")){
            driver.findElement(By.cssSelector("#root > div > section > section > div > header:nth-child(2) > div > div > img")).click();
        }
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

    @When("I {string} tab {string}")
    public void itab(String arg0, String arg1) {
        tab = driver.findElement(By.cssSelector("#root > div > section > section > div > main > div > div > div.home-sections > div:nth-child("+arg1+") > div > div.ant-collapse-header"));
        switch (arg0){
            case "expand":
            default:
                if (tab.getAttribute("aria-expanded").equals("false")){
                    tab.click();
                }
                break;
            case "collapse":
                if (tab.getAttribute("aria-expanded").equals("true")){
                    tab.click();
                }
        }
    }

    @Then("items inside tab are {string}")
    public void itemsInsideTabAre(String arg0) throws InterruptedException {
        Thread.sleep(5000);
        if (arg0.equals("shown")){
            assertEquals("true", tab.getAttribute("aria-expanded"));
        } else {
            assertEquals("false", tab.getAttribute("aria-expanded"));
        }
    }
}
