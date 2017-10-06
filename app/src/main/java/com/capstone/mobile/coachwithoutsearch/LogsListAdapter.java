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

/**
 * Created by Emily Marie Adams on 10/6/2017.
 */

public class LogsListAdapter extends ArrayAdapter<LogsList> {

    int cResource;

    public LogsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<LogsList> objects) {
        super(context, resource, objects);
        this.cResource = cResource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String logdate = getItem(position).getLogdate();
        String logtime = getItem(position).getLogtime();
        String logclass = getItem(position).getLogclass();
        String logfname = getItem(position).getFname();
        String loglname = getItem(position).getLname();
        String logcoach = loglname + ", " + logfname;

        LogsList logsList = new LogsList(logdate, logtime, logclass, logfname, loglname);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView logDate = (TextView) convertView.findViewById(R.id.txtLogDate);
        TextView logTime = (TextView) convertView.findViewById(R.id.txtLogTime);
        TextView logClass = (TextView) convertView.findViewById(R.id.txtLogClass);
        TextView logCoach = (TextView) convertView.findViewById(R.id.txtLogCoach);

        logDate.setText(logdate);
        logTime.setText(logtime);
        logClass.setText(logclass);
        logCoach.setText(logcoach);

        return convertView;
    }
}
