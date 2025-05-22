package WebTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Component.APIURL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WeatherAPI {

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    @org.testng.annotations.BeforeTest
    public void setupReport() {
        spark = new ExtentSparkReporter("test-output/WeatherReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    public void testWeatherAPI() {
        runWeatherTests();
    }

    private static void runWeatherTests() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Unable to load config: " + e.getMessage());
            return;
        }

        String apiKey = props.getProperty("weatherapi.key");
        String country = props.getProperty("country");

        ObjectMapper mapper = new ObjectMapper();
        List<String> postcodes;
        try (FileInputStream jsonStream = new FileInputStream("src/main/resources/Data.json")) {
            JsonNode rootNode = mapper.readTree(jsonStream);
            JsonNode locations = rootNode.get(country.toUpperCase());
            if (locations == null) {
                System.err.println("No postcodes found for country: " + country);
                return;
            }
            postcodes = new ArrayList<>();
            locations.forEach(node -> postcodes.add(node.asText()));
        } catch (IOException e) {
            System.err.println("Error loading postcodes JSON: " + e.getMessage());
            return;
        }

        for (String city : postcodes) {
            runTestForCity(extent, apiKey, city);
        }
    }

    private static void runTestForCity(ExtentReports extent, String apiKey, String city) {
        String requestUrl = APIURL.getCurrentWeatherUrl(apiKey, city);
        Response response = RestAssured.given().get(requestUrl);

        String cityName = response.jsonPath().getString("location.name");
        String region = response.jsonPath().getString("location.region");
        String countryName = response.jsonPath().getString("location.country");
        String tempC = response.jsonPath().getString("current.temp_c");
        String condition = response.jsonPath().getString("current.condition.text");
        String humidity = response.jsonPath().getString("current.humidity");
        String windKph = response.jsonPath().getString("current.wind_kph");

        String table = "<table border='1'><tr><th>Field</th><th>Value</th></tr>"
            + "<tr><td>City</td><td>" + cityName + "</td></tr>"
            + "<tr><td>Region</td><td>" + region + "</td></tr>"
            + "<tr><td>Country</td><td>" + countryName + "</td></tr>"
            + "<tr><td>Temperature (°C)</td><td>" + tempC + "</td></tr>"
            + "<tr><td>Condition</td><td>" + condition + "</td></tr>"
            + "<tr><td>Humidity (%)</td><td>" + humidity + "</td></tr>"
            + "<tr><td>Wind Speed (kph)</td><td>" + windKph + "</td></tr>"
            + "</table>";

        ExtentTest test = extent.createTest("Weather Report for: " + cityName);
        test.info(table);
    }

    @AfterTest
    public void tearDownTest() {
        if (extent != null) {
            extent.flush();
        }
        System.out.println("WeatherAPI test completed.");
    }
}
