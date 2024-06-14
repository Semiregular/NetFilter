/**
 * @author 周正明
 * @date 2024/5/22
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PublicIP {

    public static void main(String[] args) throws Exception {
        String publicIP = fetchPublicIPAddress();
        System.out.println("Public IP: " + publicIP);
    }

    private static String fetchPublicIPAddress() throws Exception {
        URL url = new URL("http://checkip.amazonaws.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            return in.readLine();
        } finally {
            connection.disconnect();
        }
    }
}
