package Component;

public class APIURL {
    public static final String BASE_URL = "http://api.weatherapi.com/v1";
    public static final String CURRENT_WEATHER_ENDPOINT = "/current.json";
    public static final String REQUEST_METHOD = "GET";
    public static final int SUCCESS_STATUS_CODE = 200;

    public static String getCurrentWeatherUrl(String apiKey, String city) {
        return BASE_URL + CURRENT_WEATHER_ENDPOINT + "?q=" + city + "&Key=" + apiKey;
    }
}
