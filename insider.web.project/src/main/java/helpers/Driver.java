package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    protected static WebDriver driver;
    static Util util = new Util();

    public static WebDriver getDriver() {

        if (driver == null) {
            switch (util.getProperties("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}