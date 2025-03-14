package page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final String url ="https://stellarburgers.nomoreparties.site/";
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By stellarBurgersLogo =By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By bunsSectionButton =By.xpath(".//span[text()='Булки']");
    private final By saucesSectionButton=By.xpath(".//span[text()='Соусы']");
    private final By fillingsSectionButton = By.xpath (".//span[text()='Начинки']");
    private final By checkoutButton = By.xpath(".//button[text()='Оформить заказ']");
    private By saucesHeader = By.xpath(".//h2[text()='Соусы']");
    private By fillingsHeader = By.xpath(".//h2[text()='Начинки']");
    private By bunsHeader = By.xpath(".//h2[text()='Булки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open main page")
    public MainPage openMainPage() {
        driver.get(url);
        return this;
    }


    @Step("Click login button")
    public LoginPage clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButtonElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButtonElement);
        return new LoginPage(driver);
    }

    @Step("Click personal account button")
    public ProfilePage clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        return new ProfilePage(driver);
    }

    @Step("Click constructor button")
    public MainPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }

    @Step("Click Stellar Burgers logo")
    public MainPage clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
        return this;
    }

    @Step("Click Buns section button")
    public void clickBunsSectionButton() {
        driver.findElement(bunsSectionButton).click();
    }

    @Step("Click Sauces section button")
    public void clickSaucesSectionButton() {
        driver.findElement(saucesSectionButton).click();
    }

    @Step("Click Fillings section button")
    public void clickFillingsSectionButton() {
        driver.findElement(fillingsSectionButton).click();
    }

    @Step("Check if checkout button is displayed")
    public boolean isCheckoutButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
            WebElement checkoutButtonElement = driver.findElement(checkoutButton);
            return checkoutButtonElement.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public boolean isConstructorPageOpen() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean bunsVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsHeader)).isDisplayed();
        boolean saucesVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesHeader)).isDisplayed();
        boolean fillingsVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader)).isDisplayed();
        return bunsVisible && saucesVisible && fillingsVisible;
    }
    @Step("Check Sauces section is visible")
    public boolean isSaucesHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(saucesHeader));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    @Step("Check Fillings section is visible")
    public boolean isFillingsHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    @Step("Check Buns section is visible")
    public boolean isBunsHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(bunsHeader));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}
