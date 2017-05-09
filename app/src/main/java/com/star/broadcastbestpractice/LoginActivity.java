package com.star.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    public static final String ACCOUNT_VALUE = "admin";
    public static final String PASSWORD_VALUE = "123456";

    public static final String REMEMBER_PASSWORD = "remember_password";
    public static final String ACCOUNT_KEY = "account";
    public static final String PASSWORD_KEY = "password";

    private EditText mAccountEditText;
    private EditText mPasswordEditText;

    private CheckBox mRememberPasswordCheckBox;

    private Button mLoginButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mAccountEditText = (EditText) findViewById(R.id.account);
        mPasswordEditText = (EditText) findViewById(R.id.password);

        mRememberPasswordCheckBox = (CheckBox) findViewById(R.id.remember_pass_check_box);

        boolean isRemember = mSharedPreferences.getBoolean(REMEMBER_PASSWORD, false);
        if (isRemember) {
            String account = mSharedPreferences.getString(ACCOUNT_KEY, "");
            String password = mSharedPreferences.getString(PASSWORD_KEY, "");

            mAccountEditText.setText(account);
            mPasswordEditText.setText(password);

            mRememberPasswordCheckBox.setChecked(true);
        }

        mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = mAccountEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if (ACCOUNT_VALUE.equals(account) && PASSWORD_VALUE.equals(password)) {

                    mEditor = mSharedPreferences.edit();

                    if (mRememberPasswordCheckBox.isChecked()) {
                        mEditor.putString(ACCOUNT_KEY, ACCOUNT_VALUE);
                        mEditor.putString(PASSWORD_KEY, PASSWORD_VALUE);
                        mEditor.putBoolean(REMEMBER_PASSWORD, true);
                    } else {
                        mEditor.clear();
                    }

                    mEditor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Account or Password is invalid",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
