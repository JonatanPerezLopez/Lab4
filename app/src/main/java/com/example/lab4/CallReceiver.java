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
            String phoneNumber= intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context.getApplicationContext(),state,Toast.LENGTH_SHORT).show();
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
