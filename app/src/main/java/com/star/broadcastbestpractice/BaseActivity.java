package com.star.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    public static final String ACTION_FORCE_OFFLINE =
            "com.star.broadcastbestpractice.FORCE_OFFLINE";

    private ForceOfflineBroadcastReceiver mForceOfflineBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseActivity.ACTION_FORCE_OFFLINE);

        mForceOfflineBroadcastReceiver = new ForceOfflineBroadcastReceiver();

        registerReceiver(mForceOfflineBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mForceOfflineBroadcastReceiver != null) {
            unregisterReceiver(mForceOfflineBroadcastReceiver);
            mForceOfflineBroadcastReceiver = null;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityCollector.removeActivity(this);
    }

    private class ForceOfflineBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder
                    .setTitle("Warning")
                    .setMessage("You are forced to be offline. Please try to login in again.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCollector.finishAll();

                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        }
                    })
                    .show();
        }
    }
}
