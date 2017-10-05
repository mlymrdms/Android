package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerProfile extends AppCompatActivity {

    private Button btnViewWorkout;
    TextView custid, custname, clsname, sessions;
    String customerID, customerClsName, customerWrkID, customerFullName;
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

        Intent client = getIntent();
        customerID = client.getStringExtra("ClientID");
        customerClsName = client.getStringExtra("ClientClsName");
        customerWrkID = client.getStringExtra("ClientWrkID");
        customerFullName = client.getStringExtra("ClientFullName");
        String customerName = client.getStringExtra("ClientName");
        String customerSessionRemain = client.getStringExtra("ClientSession");

//        String customerLName = client.getStringExtra("ClientLName");
//        String customerFName = client.getStringExtra("ClientFName");

        custid.setText(customerID);
        custname.setText(customerName.toUpperCase());
        clsname.setText(customerClsName);
        sessions.setText(customerSessionRemain);
//        custlname.setText(customerLName);
//        custfname.setText(customerFName);

        btnViewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewWorkout = new Intent(CustomerProfile.this, CustomerViewWorkout.class);
                viewWorkout.putExtra("ClientID", customerID);
                viewWorkout.putExtra("ClientClsName", customerClsName);
//                Toast.makeText(CustomerProfile.this, "CLASSNAME: " + customerClsName, Toast.LENGTH_SHORT).show();
                viewWorkout.putExtra("ClientWrkID", customerWrkID);
                viewWorkout.putExtra("ClientFullName", customerFullName);
                startActivity(viewWorkout);
            }
        });
    }
}
