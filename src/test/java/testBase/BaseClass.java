package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

        ChromeOptions options = new ChromeOptions();

        String githubActions = System.getenv("GITHUB_ACTIONS");

        if ("true".equalsIgnoreCase(githubActions)) {
            // Running in GitHub Actions - run headless
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            driver = new ChromeDriver(options);
        }
        else {
            //Open Chrome Browser
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("http://jupiter.cloud.planittesting.com");
            driver.manage().window().maximize();
        }

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
