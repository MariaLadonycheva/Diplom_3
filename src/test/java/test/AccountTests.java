package test;

import io.qameta.allure.Step;
import model.User;
import client.UserClient;
import org.openqa.selenium.WebDriver;
import page.ProfilePage;
import util.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import io.restassured.response.Response;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class AccountTests {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User user;
    private String accessToken;
    private UserClient userClient = new UserClient();
    private ProfilePage profilePage;

    @Before
    public void setUpAccount() throws MalformedURLException {
        String browser = System.getProperty("browser", "chrome");
        driver = BrowserFactory.createDriver(browser);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage=new ProfilePage(driver);
        user = UserGenerator.generateRandomUser();
        Response response = userClient.createUser(user);
        accessToken = response.path("accessToken");
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        loginPage.fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickLoginButton();
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
    @DisplayName("Go to account")
    public void goToAccount() {
        mainPage.clickPersonalAccountButton();
        assertTrue("Переход в личный кабинет не удался", profilePage.isProfilePageOpen());
    }

    @Test
    @DisplayName("Logout from account")
    public void logoutFromAccount() {
        mainPage.clickPersonalAccountButton();
        profilePage.clickLogoutButton();
        assertTrue("Выход из аккаунта не удался", profilePage.isLogoutSuccessful()); // Проверяем успешность выхода
    }

    @Test
    @DisplayName("Go to constructor from account by constructor button")
    public void goToConstructorFromAccountByConstructorButton() {
        mainPage.clickPersonalAccountButton();
        mainPage.clickConstructorButton();
        assertTrue("Переход в конструктор не удался", mainPage.isConstructorPageOpen());
    }


    @Test
    @DisplayName("Go to constructor from account by logo")
    public void goToConstructorFromAccountByLogo() {
        mainPage.clickPersonalAccountButton();
        mainPage.clickStellarBurgersLogo();
        assertTrue("Переход в конструктор не удался", mainPage.isConstructorPageOpen());
        }
}