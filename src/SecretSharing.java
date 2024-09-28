import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SecretSharing {

    // Class to represent a point (x, y)
    static class Point {
        long x;
        double y;  // Keep y as double for precision

        Point(long x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        // Load and process the first JSON test case file
        processJsonFile("testcases/testcase1.json");

        // Load and process the second JSON test case file
        processJsonFile("testcases/testcase2.json");
    }

    private static void processJsonFile(String filePath) throws Exception {
        // Read the JSON file
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(content, JsonObject.class);

        // Parse keys
        JsonObject keys = jsonObject.getAsJsonObject("keys");
        int n = keys.get("n").getAsInt();
        int k = keys.get("k").getAsInt();

        // Create a list to store points
        List<Point> points = new ArrayList<>();

        // Parse the points
        for (Map.Entry<String, com.google.gson.JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getKey().equals("keys")) continue; // Skip the keys object
            JsonObject root = entry.getValue().getAsJsonObject();
            int base = root.get("base").getAsInt();
            String value = root.get("value").getAsString();
            long decodedValue = Long.parseLong(value, base);  // Decode Y value as long

            // Add a new Point to the list
            points.add(new Point(Long.parseLong(entry.getKey()), decodedValue));
        }

        // Calculate the constant term using Lagrange interpolation
        double constantTerm = lagrangeInterpolation(points, 0);  // Evaluate at x = 0
        System.out.println("The constant term c for file " + filePath + " is: " + constantTerm);
    }

    // Lagrange Interpolation Function
    public static double lagrangeInterpolation(List<Point> points, double x) {
        double result = 0.0;
        int n = points.size();

        // Iterate over all points
        for (int i = 0; i < n; i++) {
            double term = points.get(i).y;  // Start with the y-value

            // Apply the Lagrange basis polynomial
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    term *= (x - points.get(j).x) / (points.get(i).x - points.get(j).x);
                }
            }

            result += term;
        }

        return result;
    }
}
