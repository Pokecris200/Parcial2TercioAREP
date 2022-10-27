package co.edu.escuelaing.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static spark.Spark.*;

/**
 *
 * @author cristian.forero-m
 */
public class ProxyService {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://ec2-3-83-175-74.compute-1.amazonaws.com:8080/palindrome";

    public static void main(String... args) throws IOException {
        port(getPort());
        staticFiles.location("/public");
        get("/palindrome", (req, res) -> {
            String s = "404 NOT FOUND :*";
            URL obj = new URL(GET_URL+"?value="+req.queryParams("value"));
            System.out.println(GET_URL+"?value="+req.queryParams("value"));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
                s = response.toString();
            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
            return s;
        });
        /**/
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8081;
    }
}
