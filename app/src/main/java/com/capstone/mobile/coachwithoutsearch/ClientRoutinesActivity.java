package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 30/09/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClientRoutinesActivity extends Fragment{
    View view;

    public static ClientRoutinesActivity newInstance() {
        ClientRoutinesActivity fragment = new ClientRoutinesActivity();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_routines_client, container, false);
        return view;
    }
}
