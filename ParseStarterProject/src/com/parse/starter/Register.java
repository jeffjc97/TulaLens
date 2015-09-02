package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Leo on 2/13/15.
 */
public class Register extends Activity{

    Button register;
    EditText username_view, password_view, confirm_view;
    String password, username, confirm;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username_view = (EditText) findViewById(R.id.username);
        password_view = (EditText) findViewById(R.id.password);
        confirm_view = (EditText) findViewById(R.id.confirm);

        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = password_view.getText().toString();
                username = username_view.getText().toString();
                confirm = confirm_view.getText().toString();
                check();
            }
        });
    }

    public void check() {
        boolean good = true;

        if (password.length() == 0 || username.length() == 0 || confirm.length() == 0) {
            Toast.makeText(Register.this, "Please complete all fields", Toast.LENGTH_LONG)
                    .show();
            good = false;
        }

        else if (username.length() < 5 && password.length() < 5) {
            Toast.makeText(Register.this, "Username and password must be at least five characters in length", Toast.LENGTH_LONG)
                    .show();
            good = false;
        }

        else if (!password.equals(confirm)) {
            Toast.makeText(Register.this, "Password and confirmation must match", Toast.LENGTH_LONG)
                    .show();
            good = false;
        }

        if (good == true) {
            register();
        }
    }
    public void register() {
        // Set up a new Parse user
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        // Call the Parse signup method
        user.signUpInBackground();
        Intent openHome = new Intent(Register.this, ParseStarterProjectActivity.class);
        startActivity(openHome);
    }

}