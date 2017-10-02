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

    int cResource;

//    static class ViewHolder {
//        TextView custname, memstatus, valid, sessions;
//    }

    public ClassListAdapter(Context context, int resource, ArrayList<ClassList> objects) {
        super(context, resource, objects);
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

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView custname = (TextView) convertView.findViewById(R.id.txtName);
        TextView memstatus = (TextView) convertView.findViewById(R.id.txtMembership);
        TextView valid = (TextView) convertView.findViewById(R.id.txtValid);
        TextView sessions = (TextView) convertView.findViewById(R.id.txtSessions);

        custname.setText(clsCustName);
        memstatus.setText(clsMemStatus);
        valid.setText(clsValid);
        sessions.setText(clsSessions);

//        ViewHolder holder;
//
//        if(convertView == null){
//            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//            convertView = inflater.inflate(cResource, parent, false);
//            holder = new ViewHolder();
//            holder.custname = (TextView) convertView.findViewById(R.id.txtName);
//            holder.memstatus = (TextView) convertView.findViewById(R.id.txtMembership);
//            holder.valid = (TextView) convertView.findViewById(R.id.txtValid);
//            holder.sessions = (TextView) convertView.findViewById(R.id.txtSessions);
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        holder.custname.setText(classList.getClsCustName());
//        holder.memstatus.setText(classList.getClsMemStatus());
//        holder.valid.setText(classList.getClsValid());
//        holder.sessions.setText(classList.getClsSessions());

        return convertView;
    }
}
