package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 30/09/2017.
 */

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ClientRoutinesActivity extends Fragment{
    View view;
    SharedPreferences pref;
    ListView logList;
    ArrayList<LogsList> logsListArrayList;
    ArrayList id_list;
    LogsListAdapter adapter;

    public static final String PREFS_NAME = "sharedPref";

    public static ClientRoutinesActivity newInstance() {
        ClientRoutinesActivity fragment = new ClientRoutinesActivity();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_routines_client, container, false);
        logList = (ListView) view.findViewById(R.id.loglist);

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);

        id_list = new ArrayList<>();
        logsListArrayList = new ArrayList<>();

        adapter = new LogsListAdapter(this.getActivity(), R.layout.activity_logs_list, logsListArrayList);
        logList.setAdapter(adapter);

        String id = pref.getString("id", "0");
        Log.d("CUSTID: ", id);

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/client/routines_activity.php?custid=" + id;
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
                        Log.d("LOGS: ", response);
                        try {
                            JSONArray jarray= new JSONArray(response);
                            for(int i = 0; i < jarray.length(); i++){
                                JSONObject obj = jarray.getJSONObject(i);
                                String log_id = obj.getString("logid");
                                String log_date = obj.getString("log_date");
                                String time_in = obj.getString("logtimein");
                                String clsname = obj.getString("clsname");
                                String recid = obj.getString("recid");
                                String stfFName = obj.getString("stffirstname");
                                String stfLName = obj.getString("stflastname");

                                Log.d("LOGID", log_date);
                                Log.d("LOGID", time_in);
                                Log.d("LOGID", clsname);
                                Log.d("LOGID", stfFName);
                                Log.d("LOGID", stfLName);

//                                id_list.add(log_id);
//                                logsListArrayList.add(new LogsList(log_date, time_in, clsname, stfFName, stfLName));
//                                list.add(log_date + " : " + pro_percentage);
//                                adapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        adapter.notifyDataSetChanged();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);
    }
}
