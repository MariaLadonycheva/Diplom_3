package test;

import io.qameta.allure.Step;
import  io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.MainPage;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class ConstructorTests {
    private WebDriver driver;
    private MainPage mainPage;
    @Before
    public void setUpConstructor() throws MalformedURLException {
        String browser = System.getProperty("browser", "chrome");
        driver = BrowserFactory.createDriver(browser);
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }
    @Test
    @DisplayName("Go to 'Sauces' section")
    public void goToSaucesSection() {
        mainPage.clickSaucesSectionButton();

        assertTrue("Переход к разделу 'Соусы' не удался: заголовок 'Соусы' не отображается", mainPage.isSaucesHeaderVisible());
    }
    @Test
    @DisplayName("Go to 'Buns' section")
    public void goToBunsSection() {
        mainPage.clickSaucesSectionButton();
        mainPage.clickBunsSectionButton();
        assertTrue("Переход к разделу 'Булки' не удался: заголовок 'Булки' не отображается", mainPage.isBunsHeaderVisible());

    }

    @Test
    @DisplayName("Go to 'Filling' section")
    public void goToFillingSection() {

        mainPage.clickFillingsSectionButton();
        assertTrue("Переход к разделу 'Начинки' не удался: заголовок 'Начинки' не отображается", mainPage.isFillingsHeaderVisible());
    }

    @After
    @Step("Teardown: quit driver")
     public void tearDown() {
        driver.quit();
    }
}

