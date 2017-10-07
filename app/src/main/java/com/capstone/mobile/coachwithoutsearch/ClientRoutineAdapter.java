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

public class ClientRoutineAdapter extends ArrayAdapter<ClientRoutine> {

    int cResource;

    public ClientRoutineAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ClientRoutine> objects) {
        super(context, resource, objects);
        this.cResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String act_name = getItem(position).getActname();
        String acp_status = getItem(position).getAcpstatus();
        String wra_sets = getItem(position).getWrasets();

        ClientRoutine clientRoutine = new ClientRoutine(act_name, acp_status, wra_sets);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView actname = (TextView) convertView.findViewById(R.id.txtActivityName);
        TextView acpstatus = (TextView) convertView.findViewById(R.id.txtWSets);
        TextView wrasets = (TextView) convertView.findViewById(R.id.txtActivityStatus);

        actname.setText(act_name);
        acpstatus.setText(acp_status);
        wrasets.setText(wra_sets);

        return convertView;
    }
}
