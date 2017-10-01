package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

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

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

//    private ImageView btnNext;
    SharedPreferences pref;
    ListView clientList;
//    ArrayList<String> list;
//    ArrayAdapter<String> adapter;
//    ArrayList id_list;

    public static final String PREFS_NAME = "sharedPref";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        btnNext = (ImageView) view.findViewById(R.id.btnNext);
        clientList = (ListView) view.findViewById(R.id.logbook);

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent viewWorkout = new Intent(getActivity(), CustomerProfile.class);
//                startActivity(viewWorkout);
//            }
//        });

        String id = pref.getString("id", "0");

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/logbook.php?id=" + id;
            checkUser(temp);
        }

        return view;
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkUser(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("CLIENTS: ", response);
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for(int i = 0; i < jarray.length(); i++){
                                JSONObject obj = jarray.getJSONObject(i);
                                String logID = obj.getString("log_id");
                                String logTime = obj.getString("log_timein");
                                String custFirstName = obj.getString("cust_firstname");
                                String custLastName = obj.getString("cust_lastname");
                                Log.d("LOG ID: ", logID);
                                Log.d("LOG TIME: ", logTime);
                                Log.d("FIRST NAME: ", custFirstName);
                                Log.d("LAST NAME: ", custLastName);

//                                id_list.add(wrkID);
//                                list.add(wrkName);
//                                adapter.notifyDataSetChanged();
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
        RequestQueue requestQueue = Volley.newRequestQueue(HomeFragment.this.getActivity());
        requestQueue.add(stringRequest);

    }
}
