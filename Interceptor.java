package com.example.aahewins.helloworld;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
/**
 * Waits for an SMS message to be sent and prepares to send the SMS contents to the server
 * @author Anthony Hewins
 * @version 1.0
 * @since 1.0
 */
public class Interceptor extends BroadcastReceiver {
    private String TAG = Interceptor.class.getSimpleName();
    private String message;

    public Interceptor() {
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] messages = null;
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                messages = new SmsMessage[pdus.length];
                for (int i=0; i < messages.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    this.message += messages[i].getMessageBody().toString();
                }
                Toast.makeText(context, "Extracted text message:\n"+message, Toast.LENGTH_LONG).show();
//                new SendToServer(this.message);
        }
    }
}