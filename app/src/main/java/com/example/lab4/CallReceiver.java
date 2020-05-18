package com.example.lab4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            Toast.makeText(context.getApplicationContext(),state,Toast.LENGTH_SHORT).show();
            if (intent.getIntExtra(TelephonyManager.EXTRA_STATE,0) == TelephonyManager.CALL_STATE_RINGING) {
                String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context.getApplicationContext(),phoneNumber,Toast.LENGTH_SHORT).show();
            }
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
