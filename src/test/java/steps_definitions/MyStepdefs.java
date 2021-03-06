package steps_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import static modules.Hooks.driver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyStepdefs {

    String base_url = "https://the-internet.herokuapp.com/";
    String target_url;

    @Given("I am on page {string}")
    public void iAmOnPage(String arg0) {
        target_url = arg0;
        driver.get(base_url + target_url);
    }

    @When("I click on button {string}")
    public void iClickOnButton(String arg0) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
    }

    @Then("A button {string} is added to the page")
    public void aButtonIsAddedToThePage(String arg0) throws InterruptedException {
        int nbrElement = driver.findElements(By.className("added-manually")).size();
        assertTrue(nbrElement>0);
    }


    @When("I login with username and password {string}")
    public void iLoginWithUsernameAndPassword(String arg0) {
        driver.navigate().to("https://" +arg0 +"@the-internet.herokuapp.com/" + target_url);
    }

    @Then("I have {string} login message")
    public void iHaveLoginMessage(String arg0) {
        String resultMessage = driver.findElement(By.cssSelector("#content > div > p")).getText();
        if (arg0.equals("successful")){
            assertEquals("Congratulations! You must have the proper credentials.", resultMessage);
        } else if (arg0.equals("failed")){
            assertEquals("Not authorized",driver.findElement(By.cssSelector("body")).getText());
        }

    }

    @When("I login with incorrect username and password")
    public void iLoginWithIncorrectUsernameAndPassword() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.alertIsPresent());
        String alrt = driver.switchTo().alert().getText();
        System.out.print(alrt);
        driver.switchTo().alert().dismiss();

    }

    @When("I enter login {string}")
    public void iEnterLogin(String arg0) {
        driver.findElement(By.id("username")).sendKeys(arg0);
    }

    @And("password {string}")
    public void password(String arg0) {
        driver.findElement(By.id("password")).sendKeys(arg0);
    }

    @And("click on button Login")
    public void clickOnButtonLogin() {
        driver.findElement(By.className("radius")).click();
    }

    @Then("login is successful")
    public void loginIsSuccessful() {
        String expectedMessage = "You logged into a secure area!";
        assertTrue(driver.findElement(By.id("flash")).getText().contains(expectedMessage));
    }

    @Then("login is failed with wrong {string} message")
    public void loginIsFailedWithWrongMessage(String arg0) {
        String expectedMessage;
        switch (arg0){
            case "password":
                expectedMessage = "Your password is invalid!";
                assertTrue(driver.findElement(By.id("flash")).getText().contains(expectedMessage));
                break;
            case "username":
            default:
                expectedMessage = "Your username is invalid!";
                assertTrue(driver.findElement(By.id("flash")).getText().contains(expectedMessage));
                break;
        }
    }

    @Then("login is {string} with {string}")
    public void loginIsWith(String arg0, String arg1) {
        assertTrue(driver.findElement(By.id("flash")).getText().contains(arg1));
    }

    @Then("an error message is displayed")
    public void anErrorMessageIsDisplayed() {
    }

    @When("I identify all webpage frames")
    public void iIdentifyAllWebpageFrames() {
        ArrayList<WebElement> frames = (ArrayList<WebElement>) driver.findElements(By.tagName("frame"));
        System.out.println("Level one of frame has " +frames.size() +" frames");
        for (WebElement frameLevelOne:frames){
            System.out.println(frameLevelOne.getAttribute("name"));
            driver.switchTo().frame(frameLevelOne);
            ArrayList<WebElement> framesLevelTwo = (ArrayList<WebElement>) driver.findElements(By.tagName("frame"));
            System.out.println("Level two of frame has " +framesLevelTwo.size() +" frames");
            for (WebElement frameLevelTwo:framesLevelTwo){
                System.out.println(frameLevelTwo.getAttribute("name"));
            }
            driver.switchTo().parentFrame();
        }
    }

    @When("I switch to frame {string}")
    public void iSwitchToFrame(String arg0) {
        switch (arg0){
            case "frame-left":
            case "frame-middle":
            case "frame-right":
                driver.switchTo().frame("frame-top");
                driver.switchTo().frame(arg0);
                break;
            case "frame-bottom":
                driver.switchTo().frame(arg0);
                break;
        }
        System.out.println(driver.findElement(By.cssSelector("body")).getText());
    }
}
