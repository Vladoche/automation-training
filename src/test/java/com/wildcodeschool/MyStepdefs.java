package com.wildcodeschool;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.Assert.assertTrue;

public class MyStepdefs {
    ChromeDriver driver;
    String os = System.getProperty("os.name").toLowerCase();

    @Given("I am on page {string}")
    public void iAmOnPage(String arg0) {

        if (os.contains("windows")){
            System.setProperty("webdriver.chrome.driver","src/test/java/com/wildcodeschool/chromedriver.exe");
        } else if (os.contains("mac")){
            System.setProperty("webDriver.chrome.drive", "src/test/java/com/wildcodeschool/chromedriver");
        }

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().setPosition(new Point(2000,10));
        driver.manage().window().maximize();
        driver.get(arg0);
    }

    @When("I click on button {string}")
    public void iClickOnButton(String arg0) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
    }

    @Then("A button {string} is added to the page")
    public void aButtonIsAddedToThePage(String arg0) throws InterruptedException {
        int nbrElement = driver.findElements(By.className("added-manually")).size();
        assertTrue(nbrElement>0);
        driver.quit();
    }
}
