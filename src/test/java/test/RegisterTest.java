package test;

import client.UserClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import util.UserGenerator;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;


public class RegisterTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User user;
    private String accessToken;
    private UserClient userClient = new UserClient();

    @Before
    @Step("Setup: Open main page, initialize driver and page objects")
    public void setUp() throws MalformedURLException {
        String browser = System.getProperty("browser", "chrome"); // Get browser from system property
        driver = BrowserFactory.createDriver(browser);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        user = UserGenerator.generateRandomUser();
        mainPage.openMainPage();
    }

    @After
    @Step("Teardown: Delete user via API, quit driver")
    public void tearDown() {
        if (accessToken != null) {
            try {
                userClient.deleteUser(accessToken);
            } catch (Exception e) {
                System.err.println("Ошибка при удалении пользователя через API: " + e.getMessage());
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Successful registration")
    @Step("Perform successful registration")
    public void successfulRegistration() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.fillRegistrationForm(user.getEmail(), user.getPassword(), user.getName());
        registerPage.clickRegisterButton();
        assertTrue("Registration should redirect to the login page", registerPage.isRedirectedToLoginPage());
  }

    @Test
    @DisplayName("Registration with invalid password")
    @Step("Perform registration with invalid password")
    public void registrationWithInvalidPassword() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.fillRegistrationForm(user.getEmail(), "123", user.getName());
        registerPage.clickRegisterButton();
        assertTrue("Error message should be displayed", registerPage.checkErrorMessageIsDisplayed("Некорректный пароль"));
    }
}