package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private WebDriver driver;
    private final By labelName = By.xpath(".//label[text()='Имя']");
    private final By labelEmail = By.xpath(".//label[text()='Email']");
    private final By labelPassword =By.xpath(".//label[text()='Пароль']");
    private final By registerButton= By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink= By.xpath(".//a[text()='Войти']");
    private final By errorMessage= By.xpath(".//p[@class='input__error text_type_main-default']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill registration form with: email - {email}, password - {password}, name - {name}")
    public RegisterPage fillRegistrationForm(String email, String password, String name) {
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        return this;
    }

    @Step("Fill name field with: {name}")
    public RegisterPage fillNameField(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(labelName));
        WebElement nameElement = label.findElement(By.xpath("./following-sibling::input"));
        nameElement.click();
        nameElement.sendKeys(name);
        return this;
    }

    @Step("Fill email field with: {email}")
    public RegisterPage fillEmailField(String email) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(labelEmail));
        WebElement emailElement = label.findElement(By.xpath("./following-sibling::input"));
        emailElement.click();
        emailElement.sendKeys(email);

        return this;
    }

    @Step("Fill password field with: {password}")
    public RegisterPage fillPasswordField(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(labelPassword));
        WebElement passwordElement = label.findElement(By.xpath("./following-sibling::input"));
        passwordElement.click();
        passwordElement.sendKeys(password);

        return this;
    }

    @Step("Click register button")
    public RegisterPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return this;
    }

    @Step("Сhecking the redirect to the login url")
    public boolean isRedirectedToLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlContains("/login"));
    }
    @Step("Click login link")
    public LoginPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Check error message is displayed: {message}")
    public boolean checkErrorMessageIsDisplayed(String message) {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed() && errorElement.getText().equals(message);
    }
}

