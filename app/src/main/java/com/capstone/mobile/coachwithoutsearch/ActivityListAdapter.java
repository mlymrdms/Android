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

    static class ViewHolder {
        TextView actname, actsets;
    }

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

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(cResource, parent, false);
            holder = new ViewHolder();
            holder.actname = (TextView) convertView.findViewById(R.id.txtActName);
            holder.actsets = (TextView) convertView.findViewById(R.id.txtSets);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.actname.setText(activityList.getActName());
        holder.actsets.setText(activityList.getActSets());

        return convertView;
    }
}
