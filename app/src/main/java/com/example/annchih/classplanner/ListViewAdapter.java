package com.example.annchih.classplanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnnChih on 11/22/15.
 */
public class ListViewAdapter extends BaseAdapter{
    Context mContext;
    private List<ClassData> list_of_classes = null;
    private ArrayList<ClassData> arraylist;
    LayoutInflater inflater;
    private ArrayList<ParseObject> position;

    public ListViewAdapter(Context context,
                           List<ClassData> list_of_classes) {
        mContext = context;
        this.list_of_classes = list_of_classes;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ClassData>();
        this.arraylist.addAll(list_of_classes);
    }
    public class ViewHolder {
        TextView class_id;
        TextView class_name;
    }
    @Override
    public int getCount() {
        return list_of_classes.size();
    }
    @Override
    public ClassData getItem(int position) {
        return list_of_classes.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(final int position, View view, ViewGroup parent) {
        final View convertView = view;
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.class_item, null);
            // Locate the TextViews in listview_item.xml
            holder.class_id = (TextView) view.findViewById(R.id.class_id);
            holder.class_name = (TextView) view.findViewById(R.id.class_title);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.class_id.setText(list_of_classes.get(position).get_class_id_2());
        holder.class_name.setText(list_of_classes.get(position).get_class_name_2());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("classplanner");
        query.fromLocalDatastore();
        query.whereEqualTo("taken", true);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("This is: ", "size" + objects.size());
                    convertView.setBackgroundColor(Color.RED);
                }
            }
        });



        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });

        return view;
    }


}