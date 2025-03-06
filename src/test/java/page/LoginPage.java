package page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {


    private WebDriver driver;
    private final By emailLabel = By.xpath(".//label[text()='Email']");
    private final By passwordLabel = By.xpath(".//label[text()='Пароль']");
    private final By loginButton =By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath (".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill email field with: {email}")
    public LoginPage fillEmailField(String email) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(emailLabel));
        WebElement emailElement = label.findElement(By.xpath("./following-sibling::input"));
        emailElement.click();
        emailElement.sendKeys(email);
        return this;
    }

    @Step("Fill password field with: {password}")
    public LoginPage fillPasswordField(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(passwordLabel));
        WebElement passwordElement = label.findElement(By.xpath("./following-sibling::input"));
        passwordElement.click();
        passwordElement.sendKeys(password);

        return this;
    }

    @Step("Click login button")
    public ProfilePage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new ProfilePage(driver);
    }

    @Step("Click register link")
    public RegisterPage clickRegisterLink() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }

    @Step("Click forgot password link")
    public ForgotPasswordPage clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }

}

