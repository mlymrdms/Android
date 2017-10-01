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
import android.widget.AdapterView;
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

public class WorkoutFragment extends Fragment {

    SharedPreferences pref;
    ListView workoutList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ArrayList id_list;

    public static final String PREFS_NAME = "sharedPref";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        list = new ArrayList<>();
        id_list = new ArrayList<>();

        workoutList = (ListView) view.findViewById(R.id.workoutlist);

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);

        adapter = new ArrayAdapter<>(WorkoutFragment.this.getActivity(), android.R.layout.simple_list_item_1, list);
        workoutList.setAdapter(adapter);

        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String WrkID = (String) id_list.get(position);
                String WrkName = (String) parent.getItemAtPosition(position);
                Intent item = new Intent(WorkoutFragment.this.getActivity(), RoutinesActivity.class);
                Log.d("ID:", String.valueOf(WrkID));
                item.putExtra("WrkID", WrkID);
                item.putExtra("WrkName", WrkName);
                startActivity(item);
            }
        });

        String id = pref.getString("id", "0");

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/Capstone/app/coach/workoutplan.php?id=" + id;
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
        RequestQueue requestQueue = Volley.newRequestQueue(WorkoutFragment.this.getActivity());
        requestQueue.add(stringRequest);

    }
}
