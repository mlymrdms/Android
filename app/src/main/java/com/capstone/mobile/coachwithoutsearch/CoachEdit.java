package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CoachEdit extends AppCompatActivity {

    SharedPreferences pref;
    EditText fname, lname, contactnum, email;
    Button btnSaveDetails;
//    SwipeRefreshLayout swipeDetails;

    public static final String PREFS_NAME = "sharedPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_edit);

        pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        fname = (EditText) findViewById(R.id.edtFName);
        lname = (EditText) findViewById(R.id.edtLName);
        contactnum = (EditText) findViewById(R.id.edtNumber);
        email = (EditText) findViewById(R.id.edtEmail);
        btnSaveDetails = (Button) findViewById(R.id.saveDetails);
//        swipeDetails = (SwipeRefreshLayout) findViewById(R.id.swipeProfile);

        final String id = pref.getString("id", "0");

        Intent editDetaile = getIntent();
        String coachFirstname = editDetaile.getStringExtra("firstname");
        String coachLastname = editDetaile.getStringExtra("lastname");
        String coachContactNumber = editDetaile.getStringExtra("contactnumber");
        String coachEmail = editDetaile.getStringExtra("email");

        fname.setText(coachFirstname);
        lname.setText(coachLastname);
        contactnum.setText(coachContactNumber);
        email.setText(coachEmail);

        btnSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    //run AsyncTask JSONParser
                    Log.d("is it connected?", "Yes it is");

                    String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/edit_profile.php?" +
                            "fname=" + fname.getText().toString() + "&lname=" + lname.getText().toString() + "&contact=" + contactnum.getText().toString()
                            + "&email=" + email.getText().toString() + "&id=" + id;
                    setComplete(temp);
                    finish();
                }
            }
        });

//        swipeDetails.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                if (isNetworkAvailable()) {
//                    //run AsyncTask JSONParser
//                    Log.d("is it connected?", "Yes it is");
//
//                    String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/edit_profile.php?" +
//                            "fname=" + fname.getText().toString() + "&lname=" + lname.getText().toString() + "&contact=" + contactnum.getText().toString()
//                            + "&email=" + email.getText().toString() + "&id=" + id;
////            String temp = "http://192.168.43.144/Capstone/app/coach/activity.php?id=" + id;
//                    setComplete(temp);
//                }
//
//                swipeDetails.setRefreshing(false);
//            }
//        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void setComplete(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("GA CONNECT:", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "server error");
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("type", "1");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
