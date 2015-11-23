package com.example.annchih.classplanner;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.annchih.classplanner.dummy.Calender_Activity;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnnChih on 11/7/15.
 */
public class Select_classesTaken extends Activity {
    List<ParseObject> ob;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    private List<ClassData> list_of_classes = null;
    ListView listview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_class);
        new RemoteDataTask().execute();
    }
    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Select_classesTaken.this);
            // Set progressdialog message
            mProgressDialog.setTitle("Class Planner");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            //Log.d("I am here!","got it");
            list_of_classes = new ArrayList<ClassData>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "classplanner");
                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending
                //query.orderByAscending("class_id");
                ob = query.find();
                for (ParseObject classplanner : ob) {
                    ClassData new_class = new ClassData();
                    new_class.set_class_id_2((String) classplanner.get("class_id"));
                    new_class.set_class_name_2((String) classplanner.get("class_name"));
                    list_of_classes.add(new_class);

                }
            }  catch (com.parse.ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(Select_classesTaken.this,
                    list_of_classes);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog

            mProgressDialog.dismiss();
        }
    }
    public void toQuarterListView(View view) {
        Intent intent = new Intent(this, QuarterListView.class);
        startActivity(intent);
    }
}
