package com.wildcodeschool;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.wildcodeschool.Hooks.*;
import static org.junit.Assert.assertTrue;

public class HomeStepdefs {

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
}
