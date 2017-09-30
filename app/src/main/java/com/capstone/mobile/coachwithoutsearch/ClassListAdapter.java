package com.capstone.mobile.coachwithoutsearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassListAdapter extends ArrayAdapter<ClassList> {

    private Context cContext;
    int cResource;

    public ClassListAdapter(Context context, int resource, ArrayList<ClassList> objects) {
        super(context, resource, objects);
        this.cContext = context;
        this.cResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String clsCustName = getItem(position).getClsCustName();
        String clsMemStatus = getItem(position).getClsMemStatus();
        String clsValid = getItem(position).getClsValid();
        String clsSessions = getItem(position).getClsSessions();

        ClassList classList = new ClassList(clsCustName, clsMemStatus, clsValid, clsSessions);

        LayoutInflater inflater = LayoutInflater.from(cContext);
        convertView = inflater.inflate(cResource, parent, false);

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtMembership = (TextView) convertView.findViewById(R.id.txtMembership);
        TextView txtValid = (TextView) convertView.findViewById(R.id.txtValid);
        TextView txtSessions = (TextView) convertView.findViewById(R.id.txtSessions);

        txtName.setText(clsCustName);
        txtMembership.setText(clsMemStatus);
        txtValid.setText(clsValid);
        txtSessions.setText(clsSessions);

        return convertView;
    }
}
