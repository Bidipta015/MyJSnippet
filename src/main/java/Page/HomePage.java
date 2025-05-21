package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    private final By acceptButton = By.xpath("//button[text()='Accept']");
    // Use a more generic locator for the headline, e.g., any h1 tag on the page
    private final By headlineText = By.tagName("h1");
    private final By subHeadlineText = By.xpath("//div[contains(text(),\"All together, in a place that's just for you\")]");
    private final By bookYoursButton = By.xpath("//button[text()='Book yours']");

    // Booking.com logo and main navigation tabs locators
    private final By logoLink = By.cssSelector("a[href='https://www.booking.com/']");
    private final By staysTab = By.xpath("//span[text()='Stays']");
    private final By flightsTab = By.xpath("//span[text()='Flights']");
    private final By flightHotelTab = By.xpath("//span[text()='Flight + Hotel']");
    private final By carRentalsTab = By.xpath("//span[text()='Car rentals']");
    private final By attractionsTab = By.xpath("//span[text()='Attractions']");
    private final By airportTaxisTab = By.xpath("//span[text()='Airport taxis']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public By getAcceptButton() {
        return acceptButton;
    }

    public By getHeadlineText() {
        return headlineText;
    }

    public By getSubHeadlineText() {
        return subHeadlineText;
    }
    public By getLogoLink() {
        return logoLink;
    }
    public By getStaysTab() {
        return staysTab;
    }
    public By getFlightsTab() {
        return flightsTab;
    }
    public By getFlightHotelTab() {
        return flightHotelTab;
    }
    public By getCarRentalsTab() {
        return carRentalsTab;
    }
    public By getAttractionsTab() {
        return attractionsTab;
    }
    public By getAirportTaxisTab() {
        return airportTaxisTab;
    }

    public By getBookYoursButton() {
        return bookYoursButton;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
