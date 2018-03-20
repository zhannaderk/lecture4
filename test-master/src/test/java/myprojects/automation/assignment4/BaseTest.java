package myprojects.automation.assignment4;

import myprojects.automation.assignment4.utils.logging.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseTest {

    protected EventFiringWebDriver driver;
    protected GeneralActions actions;

    /**
     *
     * @param browser Driver type to use in tests.
     *
     * @return New instance of {@link WebDriver} object.
     */
//    private WebDriver getDriver(String browser) {
    private WebDriver getDriver(String browser) {
        String driverPath;
        switch (browser) {
            case BrowserType.CHROME:
                driverPath = getChromeDriverName();
                break;
            case BrowserType.FIREFOX:
                driverPath = "/geckodriver.exe";
                break;
            case BrowserType.IE:
                driverPath = "/IEDriverServer.exe";
                break;
            default:
                driverPath = "/chromedriver.exe";
                break;
        }
        System.setProperty("webdriver.chrome.driver", getResource(driverPath));
        return new ChromeDriver();
//        switch (browser) {
//            case "firefox":
//                System.setProperty(
//                        "webdriver.gecko.driver",
//                        getResource("/geckodriver.exe"));
//                return new FirefoxDriver();
//            case "ie":
//            case "internet explorer":
//                System.setProperty(
//                        "webdriver.ie.driver",
//                        getResource("/IEDriverServer.exe"));
//                return new InternetExplorerDriver();
//            case "chrome":
//            default:
//                System.setProperty(
//                        "webdriver.chrome.driver",
//                        getResource("/chromedriver.exe"));
//                return new ChromeDriver();
//        }
    }

    /**
     * @param resourceName The name of the resource
     * @return Path to resource
     */
    private String getResource(String resourceName) {
        try {
           return Paths.get(BaseTest.class.getResource(resourceName).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resourceName;
    }

    /**
     * Prepares {@link WebDriver} instance with timeout and browser window configurations.
     *
     * Driver type is based on passed parameters to the automation project,
     * creates {@link ChromeDriver} instance by default.
     *
     */
    @Parameters("selenium.browser")
    @BeforeClass
    public void setUp(String browser) {
        driver = new EventFiringWebDriver(getDriver(browser));
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        actions = new GeneralActions(driver);
    }

    /**
     * That is for other os type (mac and linux) for chrome driver
     * @return path for chrome driver
     */
    private static String getChromeDriverName() {
        String osName = System.getProperty("os.name").toLowerCase();
        String result;
        if (osName.contains("mac")) {
            result = "/chromedriver-mac";
        } else if (osName.contains("linux")) {
            result = "/chromedriver-linux";
        } else {
            result = "/chromedriver.exe";
        }
        return result;
    }

    /**
     * Closes driver instance after test class execution.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

//Настройте выполнение тестового скрипта таким образом, чтобы при вызове выполнения тестов (mvn test) он выполнился на разных браузерах:
// Chrome, Firefox, Internet Explorer.
// Для этого можно в testng.xml воспользоваться возможностью передачи параметров.