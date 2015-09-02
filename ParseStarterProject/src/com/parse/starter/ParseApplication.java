package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    // ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    // Parse.enableLocalDatastore(this);

    // Add your initialization code here
    ParseObject.registerSubclass(SurveyInput.class);
    Parse.initialize(this, "yDBwDWi8H69URSDmDFUY4qz9Eu4roJNRe5MnHieH", "Jnmf7tklPNWSyzF8YEOx0oxatJKzJUKerK6bVTlE");

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
