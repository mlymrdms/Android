package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Lenovo on 01/10/2017.
 */

public class MoreActivity extends Fragment{
    View view;
    Button bt_tips, bt_profile, bt_contact;


    public static MoreActivity newInstance() {
        MoreActivity fragment = new MoreActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_more, container, false);

        bt_tips = (Button) view.findViewById(R.id.bt_tips);
        bt_profile = (Button) view.findViewById(R.id.bt_profile);
        bt_contact = (Button) view.findViewById(R.id.bt_contact);
        bt_profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        bt_contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactusActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
