package com.wildcodeschool;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Hooks {
    String os = System.getProperty("os.name").toLowerCase();
    static ChromeDriver driver;

    @Before
    public void setUp(){
        if (os.contains("windows")){
            System.setProperty("webdriver.chrome.driver","src/test/java/com/wildcodeschool/chromedriver.exe");
        } else if (os.contains("mac")){
            System.setProperty("webDriver.chrome.drive", "src/test/java/com/wildcodeschool/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().setPosition(new Point(2000,10));
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @After
    public void tearDown(){
        driver.quit();
    }
}
