package com.star.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button mForceOfflineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mForceOfflineButton = findViewById(R.id.force_offline);
        mForceOfflineButton.setOnClickListener(v -> {

            Intent intent = new Intent(ACTION_FORCE_OFFLINE);
            sendBroadcast(intent);
        });
    }
}
