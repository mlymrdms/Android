package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 01/10/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogsActivity extends Fragment{
    View view;

    public static LogsActivity newInstance() {
        LogsActivity fragment = new LogsActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_logs, container, false);
        return view;
    }
}
