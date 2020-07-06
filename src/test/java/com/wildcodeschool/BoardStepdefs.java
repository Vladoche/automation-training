package com.wildcodeschool;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.wildcodeschool.Hooks.driver;
import static org.junit.Assert.assertTrue;

public class BoardStepdefs {
    int divSizeInitial, divSizeFinal;

    @And("user is on board page")
    public void userIsOnBoardPage() {
        while (!driver.getCurrentUrl().contains("/slideboard-new/a/")){
            driver.findElement(By.cssSelector("#root > div > section > aside > div > div > div.module-links-list > a:nth-child(2) > div > i")).click();
        }
    }

    @When("user creates a new card")
    public void userCreatesANewCard() throws InterruptedException {
        divSizeInitial = driver.findElements(By.xpath("//*[@id=\"columns-container\"]/div/div[3]/div[2]/div[1]/div")).size();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime now = LocalDateTime.now();
        String cardtitle = "AUTO_" +dtf.format(now);
        driver.findElement(By.cssSelector("#columns-container > div > div.containCol__col.column-animation.col0 > div.containCol__col__plus > div.header.containCol__col__header.ng-scope > div > span > span")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div[2]/div[2]/div/div[1]/div[2]/div/span/input")).sendKeys(cardtitle);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div[2]/div[3]/div/div[2]/button[2]")).click();
        Thread.sleep(5000);
    }

    @And("user drag&drop card")
    public void userDragDropCard() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.xpath("//*[@id=\"columns-container\"]/div/div[1]/div[2]/div[1]/div[1]/div/div[2]/div[1]"));
        WebElement target = driver.findElement(By.cssSelector("#columns-container > div > div.containCol__col.column-animation.col2 > div.containCol__col__card"));
        actions.dragAndDrop(source,target).build().perform();
        Thread.sleep(5000);
    }

    @Then("card is correctly dragged and dropped")
    public void cardIsCorrectlyDraggedAndDropped() {
        divSizeFinal = driver.findElements(By.xpath("//*[@id=\"columns-container\"]/div/div[3]/div[2]/div[1]/div")).size();
        assertTrue(divSizeFinal > divSizeInitial);
    }
}
