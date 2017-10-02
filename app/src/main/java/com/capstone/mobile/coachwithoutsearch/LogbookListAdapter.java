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

public class LogbookListAdapter extends ArrayAdapter<LogbookList>{

    int cResource;

    public LogbookListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<LogbookList> objects) {
        super(context, resource, objects);
        this.cResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String custFName = getItem(position).getCustfirstname();
        String custLName = getItem(position).getCustlastname();
        String custLog = getItem(position).getCustlog();

        LogbookList logbookList = new LogbookList(custFName, custLName, custLog);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView custname = (TextView) convertView.findViewById(R.id.txtCustomerName);
        TextView custlog = (TextView) convertView.findViewById(R.id.txtCustomerLog);

        custname.setText(custLName + ", " + custFName);
        custlog.setText(custLog);

        return convertView;
    }
}
