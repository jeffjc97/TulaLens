package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by Leo on 2/13/15.
 */
public class TakeSurvey extends Activity {

    int frLen, mcLen;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.takesurvey);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        LayoutParams llLP = new LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(llLP);

        LayoutParams lp = new LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        final RadioButton[] rb = new RadioButton[5];
        final RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
        for(int j=0; j<5; j++){
            rb[j]  = new RadioButton(this);
            rg.addView(rb[j]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[j].setText("Test");
        }
        ll.addView(rg);//you add the whole RadioGroup to the layout

        EditText et = new EditText(this);
        et.setLayoutParams(lp);
        et.setHint("TEST2");
        et.setPadding(8, 8, 8, 8);
        ll.addView(et);

        Button next = new Button(this);
        next.setText("Next");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 5; i++) {
                    if (rb[i].isChecked()) {
                        Intent home = new Intent("android.intent.action.HOMESCREEN");
                        startActivity(home);
                    }
                }
            }
        });

        ll.addView(next);


        TextView fr = new TextView(this);

        fr.setLayoutParams(lp);
        fr.setText("TEST");
        fr.setPadding(8, 8, 8, 8);
        ll.addView(fr);

        setContentView(ll);




    }
}