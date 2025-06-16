package testBase;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class BaseClass {

    public ChromeDriver driver;
    public Logger logger;

    @BeforeClass
    public void setup() throws Exception {

        // Loading log4j logger
        logger = LogManager.getLogger(this.getClass());

        // Prepare ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Detect if running on GitHub Actions (or any CI)
        String githubAction = System.getenv("GITHUB_ACTIONS");
        if (Boolean.parseBoolean(githubAction)) {
            options.addArguments("--headless=new"); // Required for Chrome 109+
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080"); // Ensure element is on screen

            // Create a unique temp user-data-dir to avoid conflicts
            Path tempUserDataDir = Files.createTempDirectory("chrome-user-data");
            options.addArguments("--user-data-dir=" + tempUserDataDir.toAbsolutePath());

        }

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);

        //driver.manage().deleteAllCookies();
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
