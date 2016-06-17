package com.example.aahewins.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Waits for an SMS message to be sent and prepares to send the SMS contents to the server
 * @author Anthony Hewins
 * @version 1.0
 * @since 1.0
 */
public class Interceptor extends BroadcastReceiver {
    BroadcastReceiver br = null; //The received broadcast to begin intercepting SMS_RECEIVED
    /**
     * Waits for Telephony.SMS_RECEIVED, then begins the onReceive method
     * to begin reading SMS messages.
     */
    public void onCreate() {
        IntentFilter i = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        br = new BroadcastReceiver() {
          @Override
            public void onReceive(Context c, Intent i) {
              onReceive(c,i);
          }
        };
    }
    /**
     * Begins parsing the incoming message and prepares to send it to the server.
     * @param context The interceptor class starts the onReceive when ready
     * @param intent Intent from the interceptor class to begin reading
     */
    @SuppressWarnings("deprecation") //Using createFromPdu(byte[],String) doesn't work for old APIs
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "text", Toast.LENGTH_LONG).show();

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] messages = null;
            String from;
            if (bundle != null) try {
                Object[] pdus = (Object[]) bundle.get("pdus");
                messages = new SmsMessage[pdus.length];
                for (int i = 0; i < messages.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]); //Deprecated
                    String text = messages[i].getMessageBody();
                }
            } catch (Exception e) {
                Log.d("Exception @Interceptor", "Exception @Interceptor");
                e.printStackTrace();
            }
        }
    }
}

