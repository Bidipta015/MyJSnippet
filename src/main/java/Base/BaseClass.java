package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import utils.ReportListener;
import utils.ExtentReportManager;

@Listeners(ReportListener.class)
public class BaseClass {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    protected static ExtentReports extent = ExtentReportManager.getReportInstance();

    private static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return tlTest.get();
    }

    public static void setTest(ExtentTest test) {
        tlTest.set(test);
    }

    public WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        tlDriver.set(new ChromeDriver());
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}