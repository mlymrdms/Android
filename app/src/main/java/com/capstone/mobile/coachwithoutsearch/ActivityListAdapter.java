package com.capstone.mobile.coachwithoutsearch;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ActivityListAdapter extends ArrayAdapter<ActivityList> {

    int cResource;

    public ActivityListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ActivityList> objects) {
        super(context, resource, objects);
        this.cResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String actName = getItem(position).getActName();
        String actSets = getItem(position).getActSets();

        ActivityList activityList = new ActivityList(actName, actSets);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView txtActName = (TextView) convertView.findViewById(R.id.txtActName);
        TextView txtSets = (TextView) convertView.findViewById(R.id.txtSets);

        txtActName.setText(actName);
        txtSets.setText(actSets);

        return convertView;
    }
}
