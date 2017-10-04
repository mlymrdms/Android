package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerProfile extends AppCompatActivity {

    private Button btnViewWorkout;
    TextView custid, custname, clsname, sessions;
//    TextView custlname, custfname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        btnViewWorkout = (Button) findViewById(R.id.btnView);
        custid = (TextView) findViewById(R.id.custID);
        custname = (TextView) findViewById(R.id.custName);
        clsname = (TextView) findViewById(R.id.txtClsName);
        sessions = (TextView) findViewById(R.id.txtSessions);
//        custlname = (TextView) findViewById(R.id.custLName);
//        custfname = (TextView) findViewById(R.id.custFName);

        btnViewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewWorkout = new Intent(CustomerProfile.this, CustomerViewWorkout.class);
                startActivity(viewWorkout);
            }
        });

        Intent client = getIntent();
        String customerID = client.getStringExtra("ClientID");
        String customerName = client.getStringExtra("ClientName");
        String customerClsName = client.getStringExtra("ClientClsName");
        String customerSessionRemain = client.getStringExtra("ClientSession");
//        String customerLName = client.getStringExtra("ClientLName");
//        String customerFName = client.getStringExtra("ClientFName");

        custid.setText(customerID);
        custname.setText(customerName.toUpperCase());
        clsname.setText(customerClsName);
        sessions.setText(customerSessionRemain);
//        custlname.setText(customerLName);
//        custfname.setText(customerFName);
    }
}
