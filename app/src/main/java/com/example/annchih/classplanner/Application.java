package com.example.annchih.classplanner;

import com.parse.Parse;
import com.parse.ParseUser;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "nfKldYlgBloXoaCzcFtkZgVQFP4soJiohABrIX1o", "cX8HgSU6t03OqGMnsreTHYcSKLGxV8D0sAonyZ9v");

    }

}