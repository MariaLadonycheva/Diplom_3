package page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;
    private final By emailField= By.xpath(".//label[text()='Email']");
    private final By recoverButton= By.xpath(".//button[text()='Восстановить']");
    private final By loginLink=By.xpath(".//a[text()='Войти']");
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill email field with: {email}")
    public ForgotPasswordPage fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Click recover button")
    public ForgotPasswordPage clickRecoverButton() {
        driver.findElement(recoverButton).click();
        return this;
    }

    @Step("Click login link")
    public LoginPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
