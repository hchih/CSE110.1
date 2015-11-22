package com.example.annchih.classplanner;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnnChih on 11/21/15.
 */
public class ClassList extends Activity {

    List<ClassPlanner> list_of_classes = new ArrayList<ClassPlanner>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.classlistview);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "nfKldYlgBloXoaCzcFtkZgVQFP4soJiohABrIX1o", "cX8HgSU6t03OqGMnsreTHYcSKLGxV8D0sAonyZ9v");

        ParseObject.registerSubclass(ClassPlanner.class);

        ParseQuery<ClassPlanner> query= new ParseQuery<ClassPlanner>("classplanner");

        query.findInBackground(new FindCallback<ClassPlanner>() {
            @Override
            public void done(List<ClassPlanner> list, ParseException e) {
                if (e != null){
                    Toast.makeText(ClassList.this, "Error " + e, Toast.LENGTH_SHORT ).show();
                }
                for (ClassPlanner classPlanner : list){
                    ClassPlanner newclass = new ClassPlanner();
                    newclass.set_class_id(classPlanner.get_class_id());
                    newclass.set_class_name(classPlanner.get_class_name());
                    list_of_classes.add(newclass);
                }

                ArrayAdapter<ClassPlanner> adapter = new ArrayAdapter<ClassPlanner>(ClassList.this,
                            android.R.layout.simple_list_item_1, list_of_classes);

                ListView listView = (ListView)findViewById(R.id.class_list);
                listView.setAdapter(adapter);
            }
        });


        /*mainAdapter = new ParseQueryAdapter<ParseObject>(this, "classplanner");
        mainAdapter.setTextKey("class_id");
        mainAdapter.setTextKey("class_title");

        classAdapter = new CustomAdapter(this);

        listView = (ListView) findViewById(R.id.class_list);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();*/


    }
}
