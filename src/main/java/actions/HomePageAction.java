package actions;

import Page.HomePage;
import org.openqa.selenium.WebElement;

public class HomePageAction {
    HomePage homePage;

    public HomePageAction(HomePage homePage) {
        this.homePage = homePage;
    }

    public void acceptAllCookies() {
        WebElement accept = homePage.getDriver().findElement(homePage.getAcceptButton());
        accept.click();
    }
    public String getHeadlineText() {
        try {
            WebElement headline = homePage.getDriver().findElement(homePage.getHeadlineText());
            if (headline != null && headline.isDisplayed()) {
                return headline.getText().trim();
            } else {
                return "[Headline element not visible]";
            }
        } catch (Exception e) {
            return "[Headline element not found]";
        }
    }

    public String getSubHeadlineText() {
        return homePage.getDriver().findElement(homePage.getSubHeadlineText()).getText();
    }

    public boolean isBookYoursButtonDisplayed() {
        return homePage.getDriver().findElement(homePage.getBookYoursButton()).isDisplayed();
    }

    public boolean isLogoDisplayed() {
        return homePage.getDriver().findElement(homePage.getLogoLink()).isDisplayed();
    }

    public boolean isStaysTabVisible() {
        return homePage.getDriver().findElement(homePage.getStaysTab()).isDisplayed();
    }

    public boolean isFlightsTabVisible() {
        return homePage.getDriver().findElement(homePage.getFlightsTab()).isDisplayed();
    }

    public boolean isFlightHotelTabVisible() {
        return homePage.getDriver().findElement(homePage.getFlightHotelTab()).isDisplayed();
    }

    public boolean isCarRentalsTabVisible() {
        return homePage.getDriver().findElement(homePage.getCarRentalsTab()).isDisplayed();
    }

    public boolean isAttractionsTabVisible() {
        return homePage.getDriver().findElement(homePage.getAttractionsTab()).isDisplayed();
    }

    public boolean isAirportTaxisTabVisible() {
        return homePage.getDriver().findElement(homePage.getAirportTaxisTab()).isDisplayed();
    }
}
