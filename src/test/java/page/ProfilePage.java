package page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    private WebDriver driver;

    private final By profileLink = By.xpath(".//a[text()='Профиль']");
    private final By ordersHistoryLink= By.xpath(".//a[text()='История заказов']");
    private final By logoutButton=By.xpath(".//button[text()='Выход']");
    private final By constructorLink=By.xpath(".//p[text()='']");



    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click profile link")
    public ProfilePage clickProfileLink() {
        driver.findElement(profileLink).click();
        return this;
    }
    @Step("Сhecking the opening of a personal account")
    public boolean isProfilePageOpen() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlContains("/account/profile"));
    }
    @Step("Click orders history link")
    public ProfilePage clickOrdersHistoryLink() {
        driver.findElement(ordersHistoryLink).click();
        return this;
    }

    @Step("Click logout button")
    public ProfilePage clickLogoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(logoutButton));
        driver.findElement(logoutButton).click();
        return this;
    }

    @Step("Click constructor link")
    public MainPage clickConstructorLink() {
        driver.findElement(constructorLink).click();
        return new MainPage(driver);
    }

    public boolean isLogoutSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlContains("login"));
    }
}