package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;

public class BrowserFactory {

    public static WebDriver createDriver(String browserName) throws MalformedURLException {
        WebDriver driver = null;

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "yandex":
                WebDriverManager.chromedriver().driverVersion("132.0.6834.83").setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary("C:/Users/pc/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                yandexOptions.addArguments("--remote-allow-origins=*");
                yandexOptions.addArguments("--no-sandbox");
                yandexOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(yandexOptions);

                break;
            
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions defaultOptions = new ChromeOptions();
                defaultOptions.addArguments("--remote-allow-origins=*");
                defaultOptions.addArguments("--no-sandbox");
                defaultOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(defaultOptions);
        }

        return driver;
    }


}