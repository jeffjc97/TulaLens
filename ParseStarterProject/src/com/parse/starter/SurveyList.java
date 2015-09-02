package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by jeffreychang on 2/14/15.
 */
public class SurveyList extends Activity {

    private ParseQueryAdapter<SurveyInput> surveysQueryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_list);

        // creates the query adapter for pending requests
        ParseQueryAdapter.QueryFactory<SurveyInput> factory0 =

                new ParseQueryAdapter.QueryFactory<SurveyInput>() {

                    public ParseQuery<SurveyInput> create() {

                        ParseQuery<SurveyInput> query = SurveyInput.getQuery();
                        return query;

                    }

                };



        // Set up the query adapter

        surveysQueryAdapter = new ParseQueryAdapter<SurveyInput>(this, factory0) {

            @Override

            public View getItemView(SurveyInput post, View view, ViewGroup parent) {

                if (view == null) {

                    view = View.inflate(getContext(), R.layout.request_list_item, null);

                }

                // finds the fields to be updated
                TextView titleView = (TextView) view.findViewById(R.id.survey_titles);
                TextView questionView = (TextView) view.findViewById(R.id.num_questions);

                // updates the fields
                titleView.setText(post.getTitle().toString());
                questionView.setText("" + post.getNumQuestions());

                return view;

            }

        };

        // Disable automatic loading when the adapter is attached to a view.

        surveysQueryAdapter.setAutoload(false);

        // Attach the query adapter to the view

        ListView pendingPostsListView = (ListView) findViewById(R.id.survey_listview);

        pendingPostsListView.setAdapter(surveysQueryAdapter);

        surveysQueryAdapter.loadObjects();

        // Set up the handler for an item's selection
        ListView postsListView = (ListView) findViewById(R.id.survey_listview);

        postsListView.setAdapter(surveysQueryAdapter);

        postsListView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final SurveyInput item = surveysQueryAdapter.getItem(position);

                Intent intent = new Intent(SurveyList.this, TakeSurvey.class);
                Bundle extras = new Bundle();
                extras.putString("numQuestions", "" + item.getNumQuestions());
                extras.putString("title", item.getTitle());
                for (int i = 1; i <= item.getNumQuestions(); i++)
                {
                    extras.putString("type"+i, item.getQuestionType(i));
                    extras.putString("question"+i, item.getQuestion(i));
                }
                intent.putExtras(extras);
                startActivity(intent);

                startActivity(intent);
            }

        });



    }
}
