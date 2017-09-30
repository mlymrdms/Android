package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 01/10/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProgressActivity extends Fragment {
    View view;
    Button bt_workout;

    public static ProgressActivity newInstance() {
        ProgressActivity fragment = new ProgressActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_progress, container, false);

        bt_workout = (Button) view.findViewById(R.id.bt_workout);
        bt_workout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkoutsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
