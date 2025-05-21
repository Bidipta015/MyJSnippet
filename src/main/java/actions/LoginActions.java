package actions;

import org.openqa.selenium.WebDriver;
import Page.LoginPage;

public class LoginActions {
    private LoginPage loginPage;

    public LoginActions(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    public void performLogin(String email, String password) {
        loginPage.clickSignIn();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }
}