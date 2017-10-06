package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssignWorkout extends AppCompatActivity {

    ArrayList<String> list;
    ArrayList id_list;
    ArrayAdapter<String> adapter;
    ListView assignedlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_workout);

        list = new ArrayList<>();
        id_list = new ArrayList<>();

        assignedlist = (ListView) findViewById(R.id.assignworkoutlist);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        assignedlist.setAdapter(adapter);

        Intent assignworkout = getIntent();
        final String logid = assignworkout.getStringExtra("logID");

        assignedlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String wrkID = (String) id_list.get(position); //gets wrk_id of item
                if (isNetworkAvailable()) {
                    //run AsyncTask JSONParser
                    Log.d("is it connected?", "Yes it is");

                    String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/assign_addworkout.php?log_id=" + logid + "&wrk_id=" + wrkID;
//                    Toast.makeText(getContext(), "LINK: " + temp, Toast.LENGTH_SHORT).show();
                    setComplete(temp);
                }
//                startActivity(new Intent(AssignWorkout.this, CustomerProfile.class));
            }
        });

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/assign_workout.php?log_id=" + logid;
//            String temp = "http://192.168.43.144/Capstone/app/coach/workoutplan.php?id=" + id;
            checkUser(temp);
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkUser(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("WORKOUT PLANS: ", response);
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for(int i = 0; i < jarray.length(); i++){
                                JSONObject obj = jarray.getJSONObject(i);
                                String wrkID = obj.getString("wrk_id");
                                String wrkName = obj.getString("wrk_name");
                                Log.d("WORKOUT ID: ", wrkID);
                                Log.d("WORKOUT NAME: ", wrkName);

                                id_list.add(wrkID);
                                list.add(wrkName);
                                adapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
