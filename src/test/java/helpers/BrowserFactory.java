package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;

import static helpers.PropertiesFile.readFile;

public class BrowserFactory {
    static String os = System.getProperty("os.name").toLowerCase();
    static WebDriver factoryDriver;
    static HashMap<String, String> properties = PropertiesFile.readFile("src/test/resources/environment/config.properties");
    static String browserType = properties.get("browser");
    static String incognito = properties.get("incognito_mode");
    static String headless = properties.get("headless_mode");

    public static WebDriver getDriver(){
        String driverPath = "src/test/resources/drivers/";
        switch (browserType){
            case "gc":
            default:
                if (os.contains("windows")){
                    System.setProperty("webdriver.chrome.driver",driverPath+"chromedriver.exe");
                } else if (os.contains("mac")){
                    System.setProperty("webDriver.chrome.drive", driverPath+"chromedriver");
                }
                ChromeOptions options = new ChromeOptions();
                if (incognito.equals("true")){
                    options.addArguments("--incognito");
                }
                if (headless.equals("true")){
                    options.setHeadless(true);
                }
                factoryDriver = new ChromeDriver(options);
                break;
            case "ff":
                System.setProperty("webdriver.gecko.driver",driverPath+"geckodriver.exe");
                factoryDriver = new FirefoxDriver();
                break;
        }
        return factoryDriver;
    }
}
