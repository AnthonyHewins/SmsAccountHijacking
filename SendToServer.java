package com.example.aahewins.helloworld;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Sends information to the server
 * @author Anthony Hewins
 * @version 1.0
 * @since 1.0
 */
public class SendToServer {
    private final static String SITE = "";
    private OutputStream output;
    private URL url;
    private HttpURLConnection connection;

    public SendToServer(String s) {
        try {
            url = new URL(SITE);
            connection = (HttpURLConnection) url.openConnection();
            output = new BufferedOutputStream(connection.getOutputStream());

        } catch (MalformedURLException mue) {
            Log.d("MalformedURLException","MalformedURLException:" + this.url);
        } catch (IOException io) {
            Log.d("IOException","IO problem:\n");
            io.printStackTrace();
        } finally {
            connection.disconnect();
            Interceptor i = new Interceptor();
            //i.prepareInterception();
        }
    }
}
