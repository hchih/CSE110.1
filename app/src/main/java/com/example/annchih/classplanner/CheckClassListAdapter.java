package com.example.annchih.classplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnnChih on 11/22/15.
 */
public class CheckClassListAdapter extends BaseAdapter{
    Context mContext;
    private List<ClassData> list_of_classes = null;
    private ArrayList<ClassData> arraylist;
    LayoutInflater inflater;
    boolean [] checkItems;


    public CheckClassListAdapter(Context context,
                            List<ClassData> list_of_classes) {
        mContext = context;
        this.list_of_classes = list_of_classes;

        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ClassData>();
        this.arraylist.addAll(list_of_classes);
        checkItems = new boolean[list_of_classes.size()];

    }
    public class ViewHolder {
        TextView class_id;
        TextView class_name;
        CheckBox checkBox;
        boolean taken;
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

        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.check_class_item, null);
            // Locate the TextViews in listview_item.xml
            holder.class_id = (TextView) view.findViewById(R.id.class_id);
            holder.class_name = (TextView) view.findViewById(R.id.class_title);
            holder.checkBox =(CheckBox)view.findViewById(R.id.checkbox);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
            // remove the listener so that it does not get attached to other chechboxes
            holder.checkBox.setOnCheckedChangeListener(null);
            //update the checkbox value from boolean array
            holder.checkBox.setChecked(checkItems[position]);
        }

        holder.class_id.setText(list_of_classes.get(position).get_class_id_2());
        holder.class_name.setText(list_of_classes.get(position).get_class_name_2());

        holder.checkBox.setTag(String.valueOf(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = Integer.parseInt(buttonView.getTag().toString());
                checkItems[pos] = true;
                holder.taken = true;
                ParseQuery<ParseObject> query = ParseQuery.getQuery("classplanner");
                /*ParseObject classPlanner = new ParseObject("classplanner");
                classPlanner.put("taken",true);
                classPlanner.pinInBackground();*/

            }
        });



        return view;
    }



}
