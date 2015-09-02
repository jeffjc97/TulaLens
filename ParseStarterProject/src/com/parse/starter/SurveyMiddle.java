package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jeffreychang on 2/13/15.
 */
public class SurveyMiddle extends Activity {

    String title, objectID;
    Boolean postResult;
    Button addMC, addFR, finalize;
    int questionNum, questionComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_middle);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //objectID = extras.getString("id");
        questionNum = Integer.parseInt(extras.getString("num"));
        questionComplete = questionNum;
        title = extras.getString("title");

        TextView surveyTitle = (TextView) findViewById(R.id.survey_title);
        surveyTitle.setText("Survey Title: " + title);

        TextView questionNumber = (TextView) findViewById(R.id.question_num);

        questionNumber.setText("Completed Questions: " + questionComplete);

        // on-click handler for the multiple choice button
        addMC = (Button) findViewById(R.id.add_mc);
        addMC.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                post();
                // redirect to the main map
                if (postResult){
                    Intent intent = new Intent(SurveyMiddle.this, AddMC.class);
                    Bundle extras = new Bundle();
                    //extras.putString("id", objectID);
                    extras.putString("num", "" + questionNum);
                    extras.putString("title", title);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SurveyMiddle.this, "An error occurred, please try again.", Toast.LENGTH_LONG).show();
                }

            }
        });

        // on-click handler for the free response button
        addFR = (Button) findViewById(R.id.add_fr);
        addFR.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                post();
                // redirect to the main map
                if (postResult){
                    Intent intent = new Intent(SurveyMiddle.this, AddFR.class);
                    Bundle extras = new Bundle();
                    //extras.putString("id", objectID);
                    extras.putString("num", "" + questionNum);
                    extras.putString("title", title);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SurveyMiddle.this, "An error occurred, please try again.", Toast.LENGTH_LONG).show();
                }

            }
        });

        // on-click handler for the finalize survey button
        finalize = (Button) findViewById(R.id.finalize);
        finalize.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SurveyMiddle.this, ParseStarterProjectActivity.class);
                startActivity(intent);

            }
        });
    }

    private void post () {
        postResult = true;
    }
}
