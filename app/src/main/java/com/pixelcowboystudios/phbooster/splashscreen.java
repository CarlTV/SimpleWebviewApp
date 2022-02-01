package com.pixelcowboystudios.phbooster;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //Set Activity Full Screen
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).hide();

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(splashscreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(0, 0);
                }
            },3000 );
        }
        else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(splashscreen.this);
            builder1.setTitle("Connection not available");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Close App",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }



    }
}