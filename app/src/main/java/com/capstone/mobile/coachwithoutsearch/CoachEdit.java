package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class CoachEdit extends AppCompatActivity {

    EditText fname, lname, contactnum, email;
    //private Button btnSaveDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_edit);

        fname = (EditText) findViewById(R.id.edtFName);
        lname = (EditText) findViewById(R.id.edtLName);
        contactnum = (EditText) findViewById(R.id.edtNumber);
        email = (EditText) findViewById(R.id.edtEmail);

        Intent editDetaile = getIntent();
        String coachFirstname = editDetaile.getStringExtra("firstname");
        String coachLastname = editDetaile.getStringExtra("lastname");
        String coachContactNumber = editDetaile.getStringExtra("contactnumber");
        String coachEmail = editDetaile.getStringExtra("email");

        fname.setText(coachFirstname);
        lname.setText(coachLastname);
        contactnum.setText(coachContactNumber);
        email.setText(coachEmail);
    }
}
