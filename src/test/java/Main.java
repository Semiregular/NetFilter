
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;


public class Main {

    public static void main(String[] args) throws Exception {
        String publicIP = fetchPublicIPAddress();
        System.out.println("Public IP: " + publicIP);
        System.out.println("Local IP: " + InetAddress.getLocalHost().getHostAddress());
    }

    private static String fetchPublicIPAddress() throws Exception {
        URL url = new URL("http://checkip.amazonaws.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            return in.readLine(); // 读取返回的IP地址
        } finally {
            connection.disconnect();
        }
    }

}
