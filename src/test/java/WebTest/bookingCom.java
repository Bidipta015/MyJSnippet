package WebTest;

import Base.BaseClass;
import Page.HomePage;
import actions.HomePageAction;
import actions.SearchAction;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class bookingCom extends BaseClass {
    @BeforeMethod
    public void setup() {
        initDriver().get("https://www.booking.com/");
        HomePage homePage = new HomePage(getDriver());
        HomePageAction homePageAction = new HomePageAction(homePage);
        homePageAction.acceptAllCookies();
    }

    @Test
    public void testHomePageValidation() {
        setTest(extent.createTest("Home Page Validation"));
        HomePage homePage = new HomePage(getDriver());
        HomePageAction homePageAction = new HomePageAction(homePage);

        getTest().log(Status.INFO, "Validating headline text...");
        assert homePageAction.getHeadlineText().contains("Recharge in a holiday home") : "Headline text mismatch.";

        getTest().log(Status.INFO, "Validating subheadline text...");
        assert homePageAction.getSubHeadlineText().contains("All together, in a place that's just for you") : "Subheadline text mismatch.";

        getTest().log(Status.INFO, "Validating Booking.com logo...");
        assert homePageAction.isLogoDisplayed() : "Booking.com logo not displayed.";

        getTest().log(Status.INFO, "Validating navigation tabs...");
        assert homePageAction.isStaysTabVisible() : "'Stays' tab not visible.";
        assert homePageAction.isFlightsTabVisible() : "'Flights' tab not visible.";
        assert homePageAction.isFlightHotelTabVisible() : "'Flight + Hotel' tab not visible.";
        assert homePageAction.isCarRentalsTabVisible() : "'Car rentals' tab not visible.";
        assert homePageAction.isAttractionsTabVisible() : "'Attractions' tab not visible.";
        assert homePageAction.isAirportTaxisTabVisible() : "'Airport taxis' tab not visible.";
        getTest().log(Status.PASS, "Home page validation test passed.");
    }

    @Test
    public void testFlightSearch() throws InterruptedException {
        setTest(extent.createTest("Flight Search Test"));
        getTest().log(Status.INFO, "Initiating flight search test...");
        SearchAction searchAction = new SearchAction(getDriver());
        searchAction.searchForFlight("London");
        Thread.sleep(2000);
        String title = getDriver().getTitle().toLowerCase();
        getTest().log(Status.INFO, "Validating search result page title...");
        assert title.contains("london") : "Search result page did not load as expected.";
        getTest().log(Status.PASS, "Flight search test passed.");
    }

    @AfterMethod
    public void cleanup() {
        tearDown();
        extent.flush();
    }
}