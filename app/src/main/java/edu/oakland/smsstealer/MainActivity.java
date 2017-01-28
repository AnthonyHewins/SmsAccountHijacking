package edu.oakland.smsstealer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {
    public static TelephonyManager MANAGER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MANAGER = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }
}
