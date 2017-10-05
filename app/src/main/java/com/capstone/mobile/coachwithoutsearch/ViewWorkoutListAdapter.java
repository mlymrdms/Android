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
 * Created by Emily Marie Adams on 10/5/2017.
 */

public class ViewWorkoutListAdapter extends ArrayAdapter<ViewWorkoutList> {

    int cResource;

    public ViewWorkoutListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ViewWorkoutList> objects) {
        super(context, resource, objects);
        this.cResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String acpid = getItem(position).getAcpid();
        String actname = getItem(position).getActname();
        String wrasets = getItem(position).getWrasets();
        String actstatus = getItem(position).getActstatus();

        ViewWorkoutList viewWorkoutList = new ViewWorkoutList(acpid, actname, wrasets, actstatus);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView actName = (TextView) convertView.findViewById(R.id.vwActivityName);
        TextView wraSets = (TextView) convertView.findViewById(R.id.vwSets);
        TextView actStatus = (TextView) convertView.findViewById(R.id.vwStatus);

        actName.setText(actname);
        wraSets.setText(wrasets);
        actStatus.setText(actstatus);

        return convertView;
    }
}
