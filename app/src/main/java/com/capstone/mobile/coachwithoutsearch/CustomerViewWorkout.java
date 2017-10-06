package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

public class CustomerViewWorkout extends AppCompatActivity {

    TextView workoutname, clientfullname, proid;
    ListView vwList;
    SwipeRefreshLayout swipeListView;
    ArrayList<ViewWorkoutList> viewWorkoutListArrayList;
    ViewWorkoutListAdapter adapter;
    ArrayList acp_id_list;
    Button finishbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_workout);

        clientfullname = (TextView) findViewById(R.id.txtClientFullName);
        workoutname = (TextView) findViewById(R.id.txtworkoutVW);
        vwList = (ListView) findViewById(R.id.viewworkoutlv);
        swipeListView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        finishbtn = (Button) findViewById(R.id.btnFinish);
        proid = (TextView) findViewById(R.id.txtproid);


        viewWorkoutListArrayList = new ArrayList<>();
        acp_id_list = new ArrayList();

        adapter = new ViewWorkoutListAdapter(this, R.layout.activity_customer_view_workout_list, viewWorkoutListArrayList);
        vwList.setAdapter(adapter);

        Intent viewWorkout = getIntent();
//        Intent item = getIntent();
        final String id = viewWorkout.getStringExtra("logID");

//        Toast.makeText(this, "LINTE NGA ID: " + id, Toast.LENGTH_SHORT).show();

        String customerClsName = viewWorkout.getStringExtra("ClientClsName");
        String wrkID = viewWorkout.getStringExtra("ClientWrkID");
        String fullname = viewWorkout.getStringExtra("ClientFullName");
//        String workoutplanname = item.getStringExtra("WrkName");
//        Toast.makeText(this, "WORKOUT NAME: " + workoutplanname, Toast.LENGTH_SHORT).show();

//        String acpid = (String) acp_id_list.get(position);

        clientfullname.setText(fullname.toUpperCase());
//        workoutname.setText(workoutplanname);
        workoutname.setText(customerClsName.toUpperCase());

        if (isNetworkAvailable()) {
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/logbook_workout.php?id=" + id;
//            String temp = "http://192.168.43.144/Capstone/app/coach/activity.php?id=" + id;
            checkUser(temp);
        }

        swipeListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (isNetworkAvailable()) {
                    //run AsyncTask JSONParser
                    Log.d("is it connected?", "Yes it is");

                    String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/logbook_workout.php?id=" + id;
//            String temp = "http://192.168.43.144/Capstone/app/coach/activity.php?id=" + id;
                    checkUser(temp);
                }

                swipeListView.setRefreshing(false);
            }
        });

//        finishbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                proid.getText();
//                Toast.makeText(CustomerViewWorkout.this, "PRO ID: " + proid.getText(), Toast.LENGTH_SHORT).show();

//            }
//        });

    }

    public void finishworkout(View view){
//        Toast.makeText(CustomerViewWorkout.this, "PRO ID: " + proid.getText(), Toast.LENGTH_SHORT).show();
        if (isNetworkAvailable()) {
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/finishworkout.php?pro_id=" + proid.getText();
//                    Toast.makeText(getContext(), "LINK: " + temp, Toast.LENGTH_SHORT).show();
            setComplete(temp);
            finish();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkUser(String url) {
        adapter.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOGBOOK WORKOUT: ", response);
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for (int i = 0; i < jarray.length(); i++) {
                                JSONObject obj = jarray.getJSONObject(i);
                                String pro_id = obj.getString("pro_id");
                                String acp_id = obj.getString("acp_id");
                                String act_name = obj.getString("act_name");
                                String wra_sets = obj.getString("wra_sets");
                                String act_status = obj.getString("act_status");
//                                Toast.makeText(RoutinesActivity.this, obj.getString("act_name"), Toast.LENGTH_SHORT).show();
                                Log.d("PRO ID: ", pro_id);
                                Log.d("ACP ID: ", acp_id);
                                Log.d("ACT NAME: ", act_name);
                                Log.d("WRA SETS: ", wra_sets);
                                Log.d("ACT STATUS: ", act_status);

                                proid.setText(pro_id);
                                viewWorkoutListArrayList.add(new ViewWorkoutList(pro_id, acp_id, act_name, wra_sets, act_status));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
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