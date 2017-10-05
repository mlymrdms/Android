package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CustomerViewWorkout extends AppCompatActivity {

    TextView clsname, clientfullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_workout);

        clientfullname = (TextView) findViewById(R.id.txtClientFullName);
        clsname = (TextView) findViewById(R.id.txtVWClass);

        Intent viewWorkout = getIntent();
        String customerClsName = viewWorkout.getStringExtra("ClientClassName");
        String wrkID = viewWorkout.getStringExtra("ClientWrkID");
        String fullname = viewWorkout.getStringExtra("ClientFullName");

        clientfullname.setText(fullname.toUpperCase() );
        clsname.setText(customerClsName.toUpperCase());
    }
}
