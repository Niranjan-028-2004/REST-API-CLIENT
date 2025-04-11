import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherClient {

    // Replace this with your actual OpenWeatherMap API key
    static String API_KEY = "your_api_key_here";

    // Change city as needed
    static String CITY = "London";

    public static void main(String[] args) {
        try {
            // Construct API endpoint
            String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&appid=" + API_KEY + "&units=metric";

            // Make the HTTP GET request
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Parse JSON
            JSONObject json = new JSONObject(response.toString());

            // Extract data
            String city = json.getString("name");
            JSONObject main = json.getJSONObject("main");
            double temp = main.getDouble("temp");
            double feelsLike = main.getDouble("feels_like");
            int humidity = main.getInt("humidity");

            JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
            String description = weather.getString("description");

            // Display data
            System.out.println("📍 City: " + city);
            System.out.println("🌡️ Temperature: " + temp + "°C");
            System.out.println("🤔 Feels Like: " + feelsLike + "°C");
            System.out.println("💧 Humidity: " + humidity + "%");
            System.out.println("☁️ Condition: " + description);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
