package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();
		//ParseAnalytics.trackAppOpenedInBackground(getIntent());

        Button makeSurvey, takeSurvey;

        makeSurvey = (Button) findViewById(R.id.add_survey);
        makeSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    Intent intent = new Intent(ParseStarterProjectActivity.this, MakeSurvey.class);
                    startActivity(intent);

            }
        });

        takeSurvey = (Button) findViewById(R.id.take_survey);
        takeSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(ParseStarterProjectActivity.this, SurveyList.class);
                startActivity(intent);

            }
        });
	}
}
