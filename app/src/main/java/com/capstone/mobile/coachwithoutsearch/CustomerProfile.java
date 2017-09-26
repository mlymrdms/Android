package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CustomerProfile extends AppCompatActivity {

    private Button btnViewWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        btnViewWorkout = (Button) findViewById(R.id.btnView);

        btnViewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewWorkout = new Intent(CustomerProfile.this, CustomerViewWorkout.class);
                startActivity(viewWorkout);
            }
        });
    }
}
