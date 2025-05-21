package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightSearch {
    WebDriver driver;

    private final By destinationInput = By.cssSelector("input[placeholder='Where are you going?']");
    private final By searchButton = By.cssSelector("button[type='submit']");

    public FlightSearch(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDestination(String destination) {
        driver.findElement(destinationInput).sendKeys(destination);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }
}
