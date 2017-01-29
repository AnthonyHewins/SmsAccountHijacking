package edu.oakland.smsstealer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public static TelephonyManager MANAGER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MANAGER = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }
}

//Doesn't work on HTC M9 One with 6.0 Marshmallow, Verizon but may work with another phone?
//int[] b = new int[3];
//b[0] = MANAGER.getPhoneCount();
//        b[1] = MANAGER.getPhoneType();
//        b[2] = MANAGER.getSimState();
//        for(int i=0; i<b.length; i++) {
//        Toast.makeText(this, b[i], Toast.LENGTH_LONG).show();
//        }