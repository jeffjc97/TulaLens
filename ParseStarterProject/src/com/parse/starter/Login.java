package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Leo on 2/13/15.
 */
public class Login extends Activity{

    Button login;
    TextView usernameEdit, passwordEdit;
    String password, username;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameEdit = (EditText) findViewById(R.id.username);
        passwordEdit = (EditText) findViewById(R.id.password);


        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = passwordEdit.getText().toString();
                username = usernameEdit.getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e == null) {
                            success();
                        }
                        else {
                            error();
                        }
                    }
                });
            }
        });
    }

    public void success() {
        Intent openMakeSurvey = new Intent(Login.this, ParseStarterProjectActivity.class);
        startActivity(openMakeSurvey);
    }

    public void error() {
        StringBuilder validationErrorMessage = new StringBuilder("Login failed ");
        if (username.length() == 0) {
            validationErrorMessage.append(", no username ");
        }

        if (password.length() == 0) {
            validationErrorMessage.append(", no password");
        }

        Toast.makeText(Login.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                .show();
    }
}