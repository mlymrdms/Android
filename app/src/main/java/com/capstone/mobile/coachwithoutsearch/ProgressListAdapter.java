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

public class ProgressListAdapter extends ArrayAdapter<ProgressList>{

    int cResource;

    public ProgressListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ProgressList> objects) {
        super(context, resource, objects);
        this.cResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String logid = getItem(position).getLogid();
        String logdate = getItem(position).getLogdate();
        String propercentage = getItem(position).getPropercentage();

        ProgressList progressList = new ProgressList(logid, logdate, propercentage);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView pLogDate = (TextView) convertView.findViewById(R.id.txtProgDate);
        TextView pPercenteage = (TextView) convertView.findViewById(R.id.txtProgPercent);

        pLogDate.setText(logdate);
        pPercenteage.setText(propercentage);

        return convertView;
    }
}
