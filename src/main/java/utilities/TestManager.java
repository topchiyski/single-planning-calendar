package utilities;

import enumerations.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class TestManager {

    private WebDriver webDriver;
    private static final String PATH_TO_CHROME_DRIVER = ".\\src\\main\\resources\\drivers\\chromedriver.exe";
    private static final String PATH_TO_FIREFOX_DRIVER = ".\\src\\main\\resources\\drivers\\geckodriver.exe";

    public void startBrowser(Browser browser) {

        if(browser == Browser.CHROME) {
            System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
            webDriver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", PATH_TO_FIREFOX_DRIVER);
            webDriver = new FirefoxDriver();
        }
        webDriver.manage().window().maximize();
        int defaultImpliocitWaitInterval = 3;
        webDriver.manage().timeouts().implicitlyWait(defaultImpliocitWaitInterval, TimeUnit.SECONDS);
    }

    public WebDriver provideWebDriver() {
        return webDriver;
    }

    public void navigate(String urlToBeNavigated) {
        webDriver.get(urlToBeNavigated);
    }

    public void closeBrowser() {
        webDriver.quit();
    }
}