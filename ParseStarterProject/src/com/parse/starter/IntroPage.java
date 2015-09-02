package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Leo on 2/13/15.
 */
public class IntroPage extends Activity{

    Button login, register, skip;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intropage);

        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        skip = (Button) findViewById(R.id.skip);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(IntroPage.this, Login.class);
                startActivity(login);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(IntroPage.this, Register.class);
                startActivity(register);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTakeSurvey = new Intent(IntroPage.this, ParseStarterProjectActivity.class);
                startActivity(openTakeSurvey);
            }
        });



    }


}
