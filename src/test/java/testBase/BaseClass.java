package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import utilities.ExcelReader;

import java.time.Duration;


public class BaseClass {

    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    public void setup(){

        //Loading log4j file
        logger = LogManager.getLogger(this.getClass());

        //Open Chrome Browser
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://jupiter.cloud.planittesting.com");
        driver.manage().window().maximize();

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
