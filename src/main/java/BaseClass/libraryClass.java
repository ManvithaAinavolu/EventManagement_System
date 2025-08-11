package BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class libraryClass {
    protected static WebDriver driver;
    protected static Properties config = new Properties();
    private static final Logger logger = LogManager.getLogger(libraryClass.class);

    // Load config.properties with exception handling
    
    public static void loadConfig() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/Confuration.Properties/Config.property")) {
            config.load(fis);
            logger.info("Configuration file loaded successfully.");
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while loading config: " + e.getMessage(), e);
        }
    }

    // Initialize browser based on config
    public static void initializeBrowser() {
        try {
            loadConfig();
            String browser = config.getProperty("browser", "chrome");
            logger.info("Selected browser from config: " + browser);
            int implicitWait = Integer.parseInt(config.getProperty("implicitWait", "30"));

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    logger.error("Unsupported browser specified in config: " + browser);
                    return;
            }

            driver.manage().window().maximize();
            logger.info("Browser window maximized.");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            logger.info("Implicit wait set to: " + implicitWait + " seconds.");
        } catch (Exception e) {
            logger.error("Failed to initialize browser: " + e.getMessage(), e);
        }
    }

    // Open application using config URL
    public static void openApplication() {
        try {
            String url = config.getProperty("url");
            if (url != null && !url.isEmpty()) {
                driver.get(url);
                logger.info("Navigated to URL: " + url);
            } else {
                logger.warn("URL not found in config file.");
            }
        } catch (Exception e) {
            logger.error("Failed to open application URL: " + e.getMessage(), e);
        }
    }

    // Close the browser
    public static void closeBrowser() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed successfully.");
            } else {
                logger.warn("Driver is null. No browser session to close.");
            }
        } catch (Exception e) {
            logger.error("Error occurred while closing the browser: " + e.getMessage(), e);
        }
    }
}
