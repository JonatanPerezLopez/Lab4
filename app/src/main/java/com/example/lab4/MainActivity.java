package com.example.lab4;

import android.Manifest;
import android.app.Service;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variables
    private static final int REQ_CODE = 1;
    TelephonyManager telephonyManager;
    PhoneStateListener phoneStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView opID = findViewById(R.id.opID);
        TextView country = findViewById(R.id.country);
        TextView network = findViewById(R.id.network);
        TextView phone_number = findViewById(R.id.phone_number);
        Button button = findViewById(R.id.button);


        telephonyManager = (TelephonyManager) getSystemService(Service.TELEPHONY_SERVICE);

        int checkResult = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if (checkResult != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                // show rationale
            }
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, REQ_CODE);
        }
        //checking again
        checkResult = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if (checkResult == PackageManager.PERMISSION_GRANTED) {
            String opId = telephonyManager.getNetworkOperator(); // get operator ID
            opID.setText(opId);
            String count = telephonyManager.getSimCountryIso();
            country.setText(count);
            String net = telephonyManager.getNetworkOperatorName();
            network.setText(net);
            String num = telephonyManager.getLine1Number();
            phone_number.setText(num);
            phoneStateListener = new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String phoneNumber) {
                    switch (state) {
                        case TelephonyManager.CALL_STATE_IDLE:
                            Toast.makeText(getApplicationContext(),"Idle",Toast.LENGTH_SHORT).show();
                            break;
                        case TelephonyManager.CALL_STATE_RINGING:
                            Toast.makeText(getApplicationContext(),"Ringing",Toast.LENGTH_SHORT).show();
                            break;
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                            Toast.makeText(getApplicationContext(),"Offhook",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            };
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]permissions, @NonNull int[]grantResults) {
        switch (requestCode) {
            case REQ_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted :) do stuff
                } else {
                    // permission denied :( don't do stuff
                }
                return;
            }
            // check for other permissions
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
    }
}