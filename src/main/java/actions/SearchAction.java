package actions;

import org.openqa.selenium.WebDriver;
import Page.FlightSearch;

public class SearchAction {
    FlightSearch searchPage;

    public SearchAction(WebDriver driver) {
        this.searchPage = new FlightSearch(driver);
    }

    public void searchForFlight(String destination) {
        searchPage.enterDestination(destination);
        searchPage.clickSearch();
    }
}
