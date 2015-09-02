package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Leo on 2/13/15.
 */
@ParseClassName("SurveyInfo")
public class SurveyInput extends ParseObject {

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public int getNumQuestions() {
        return getInt("numQuestions");
    }

    public void changeNumQuestions(int num) {
        put("numQuestions", num);
    }

    public String getQuestionType(int num) { return getString("questiontype" + num); }

    public String getQuestion(int num) {return getString("question" + num); }


    public static ParseQuery<SurveyInput> getQuery() {
        return ParseQuery.getQuery(SurveyInput.class);
    }
}