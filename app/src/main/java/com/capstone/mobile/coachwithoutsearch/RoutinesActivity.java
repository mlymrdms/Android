package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

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

public class RoutinesActivity extends AppCompatActivity {

    String actName, actSets;
    TextView WorkoutName;
    ListView actList;
    ArrayList<ActivityList> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        WorkoutName = (TextView) findViewById(R.id.txtWorkoutName);
        actList = (ListView) findViewById(R.id.activitieslist);

        activityList = new ArrayList<>();
        Intent name = getIntent();
        String id = name.getStringExtra("WrkID");
        String workoutname = name.getStringExtra("WrkName");

        ActivityListAdapter adapter = new ActivityListAdapter(this, R.layout.activity_routines_list, activityList);
        actList.setAdapter(adapter);

        WorkoutName.setText(workoutname);

        Log.d("wrkID: ", id);
        Log.d("wrkNAME: ", workoutname);

        //check if network is available
        if (isNetworkAvailable()) {
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

//            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/activity.php?id=" + id;
            String temp = "http://192.168.43.253/Capstone/app/coach/activity.php?id=" + id;
            checkUser(temp);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkUser(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ACTIVITY LIST: ", response);
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for (int i = 0; i < jarray.length(); i++) {
                                JSONObject obj = jarray.getJSONObject(i);
                                String activityName = obj.getString("act_name");
                                String activitySets = obj.getString("wra_sets");
                                Log.d("ACT NAME: ", activityName);
                                Log.d("SETS: ", activitySets);

                                activityList.add(new ActivityList(activityName, activitySets));
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
}
