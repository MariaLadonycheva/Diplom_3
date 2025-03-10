package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.MalformedURLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class BrowserFactory {
    private static final String CONFIG_FILE = "config.properties";
    private static final String YANDEX_BROWSER_PATH;
    private static final String YANDEX_DRIVER_VERSION;

    static {
        Properties props = new Properties();
        try (InputStream input = BrowserFactory.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Не удалось найти файл " + CONFIG_FILE);
                YANDEX_BROWSER_PATH = null;
                YANDEX_DRIVER_VERSION = null;
            } else {
                props.load(input);
                YANDEX_BROWSER_PATH = props.getProperty("yandex.browser.path");
                YANDEX_DRIVER_VERSION = props.getProperty("yandex.driver.version");
            }
        } catch (IOException ex) {
            System.err.println("Ошибка при чтении файла " + CONFIG_FILE + ": " + ex.getMessage());
            throw new RuntimeException("Ошибка при инициализации", ex);

        }
    }
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
                if (YANDEX_DRIVER_VERSION != null && !YANDEX_DRIVER_VERSION.isEmpty()) {
                    WebDriverManager.chromedriver().driverVersion(YANDEX_DRIVER_VERSION).setup();
                } else {
                    WebDriverManager.chromedriver().setup();
                    System.err.println("Версия драйвера для Яндекс.Браузера не найдена в " + CONFIG_FILE + ".  Используется последняя версия.");
                }

                ChromeOptions yandexOptions = new ChromeOptions();
                if (YANDEX_BROWSER_PATH != null && !YANDEX_BROWSER_PATH.isEmpty()) {
                    yandexOptions.setBinary(YANDEX_BROWSER_PATH);
                } else {
                    System.err.println("Путь к Яндекс.Браузеру не найден в " + CONFIG_FILE + ".  Используется стандартный путь.");
                    // Можно добавить логику для поиска браузера в стандартных местах, если путь не задан
                }
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