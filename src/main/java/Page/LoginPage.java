package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    private final By signInButton = By.cssSelector("a[data-testid='header-sign-in-button']");
    private final By emailInput = By.id("username");
    private final By continueButton = By.cssSelector("button[type='submit']");
    private final By passwordInput = By.id("password");
    private final By loginSubmitButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(continueButton).click();
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginSubmitButton).click();
    }
}