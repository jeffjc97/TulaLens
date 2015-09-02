package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by jeffreychang on 2/13/15.
 */
public class AddMC extends Activity {

    EditText mcQuestion, choice1, choice2, choice3, choice4;
    String questionString, objectID, title, choice1string, choice2string, choice3string, choice4string, concat;
    Boolean postResult;
    Button submit, addFR, finalize;
    int questionNum, onQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_mc);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //objectID = extras.getString("id");
        questionNum = Integer.parseInt(extras.getString("num"));
        title = extras.getString("title");

        TextView questionNumber = (TextView) findViewById(R.id.question_number);
        onQuestion = questionNum + 1;
        questionNumber.setText("Question Number: " + onQuestion);

        // on-click handler for the submit button
        submit = (Button) findViewById(R.id.submit_question);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                post();
                // redirect to the main map
                if (postResult){
                    Intent intent = new Intent(AddMC.this, SurveyMiddle.class);
                    Bundle extras = new Bundle();
                    //extras.putString("id", objectID);
                    questionNum++;
                    extras.putString("num", "" + questionNum);
                    extras.putString("title", title);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(AddMC.this, "An error occurred, please try again.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void post () {

        // finds the values in the post
        mcQuestion = (EditText) findViewById(R.id.input_mc);
        choice1 = (EditText) findViewById(R.id.input_choice_1);
        choice2 = (EditText) findViewById(R.id.input_choice_2);
        choice3 = (EditText) findViewById(R.id.input_choice_3);
        choice4 = (EditText) findViewById(R.id.input_choice_4);

        // converts inputted text to strings
        questionString = mcQuestion.getText().toString().trim();
        choice1string = choice1.getText().toString().trim();
        choice2string = choice2.getText().toString().trim();
        choice3string = choice3.getText().toString().trim();
        choice4string = choice4.getText().toString().trim();


        if (questionString.equals("") || choice1string.equals("") || choice2string.equals("") || choice3string.equals("") || choice4string.equals("")){
            Toast.makeText(AddMC.this, "Please complete all fields.", Toast.LENGTH_LONG).show();
        }

        else{
            concat = questionString + "&&&" + choice1string + "&&&" + choice2string + "&&&" + choice3string + "&&&" + choice4string;
            postResult = true;
            ParseQuery<ParseObject> query = ParseQuery.getQuery("SurveyInfo");

            // Retrieve the object by id
            query.whereEqualTo("title", title);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject surveyInfo, ParseException e) {
                    // on successful query
                    if (e == null) {
                        String questionType = "questiontype" + questionNum;
                        String questionQues = "question" + questionNum;
                        //String[] info = {"fr", questionString};
                        //surveyInfo.put(questionType, Arrays.asList(info));
                        surveyInfo.put(questionType, "mc");
                        surveyInfo.put(questionQues, concat);
                        surveyInfo.put("numQuestions", questionNum);
                        surveyInfo.saveInBackground();
                    }
                }
            });


//            query.getInBackground(title, new GetCallback<ParseObject>() {
//                public void done(ParseObject surveyInfo, ParseException e) {
//                    if (e == null) {
//                        String questionCol = "question" + questionNum;
//                        String[] info = {"fr", questionString};
//                        surveyInfo.put(questionCol, info);
//                        surveyInfo.put("numQuestions", questionNum+1);
//                        surveyInfo.saveInBackground();
//                    }
//                    //Toast.makeText(AddFR.this, title, Toast.LENGTH_LONG).show();
//                }
//            });

        }
    }
}
