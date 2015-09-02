package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.SaveCallback;

/**
 * Created by jeffreychang on 2/13/15.
 */
public class MakeSurvey extends Activity {

    EditText surveyTitle;
    String title, objectID;
    Boolean postResult;
    Button addMC, addFR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_survey);

        // on-click handler for the multiple choice button
        addMC = (Button) findViewById(R.id.add_mc);
        addMC.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                post();
                // redirect to the main map
                if (postResult){
                    Intent intent = new Intent(MakeSurvey.this, AddMC.class);
                    Bundle extras = new Bundle();
                    //extras.putString("id", objectID);
                    extras.putString("num","0");
                    extras.putString("title", title);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MakeSurvey.this, "An error occurred, please try again.", Toast.LENGTH_LONG).show();
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
                    Intent intent = new Intent(MakeSurvey.this, AddFR.class);
                    Bundle extras = new Bundle();
                    //extras.putString("id", objectID);
                    extras.putString("num","0");
                    extras.putString("title", title);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MakeSurvey.this, "An error occurred, please try again.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void post () {


        // finds the values in the post
        surveyTitle = (EditText) findViewById(R.id.title_input);

        // converts inputed text to strings
        title = surveyTitle.getText().toString().trim();


        if (title.equals("")){
            Toast.makeText(MakeSurvey.this, "Please enter a title!", Toast.LENGTH_LONG).show();
        }

        else{
            postResult = true;
            SurveyInput post = new SurveyInput();

            post.setTitle(title);
            post.changeNumQuestions(0);

            // setting permissions with ParseACL (chmod for parse)
            ParseACL acl = new ParseACL();
            // Give public read and write access
            acl.setPublicReadAccess(true);
            acl.setPublicWriteAccess(true);
            post.setACL(acl);

            // Save the post in the background
            post.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    finish();
                }
            });

            //ParseObject thisSurvey = new ParseObject("SurveyInfo");
            //thisSurvey.put("title", "ksaowkkl");
            //thisSurvey.put("numQuestions", 0);
            //Toast.makeText(MakeSurvey.this, title, Toast.LENGTH_LONG).show();
            //thisSurvey.saveInBackground();
            //objectID = thisSurvey.getObjectId();
        }
    }
}
