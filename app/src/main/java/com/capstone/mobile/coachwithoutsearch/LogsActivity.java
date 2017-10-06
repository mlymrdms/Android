package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 01/10/2017.
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

public class LogsActivity extends Fragment{
    View view;
    SharedPreferences pref;
    ListView logList;
    ArrayList<LogsList> logsListArrayList;
    LogsListAdapter adapter;

    public static LogsActivity newInstance() {
        LogsActivity fragment = new LogsActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_logs, container, false);

        logList = (ListView) view.findViewById(R.id.loglist);

        logsListArrayList = new ArrayList<>();

        adapter = new LogsListAdapter(this.getActivity(), R.layout.activity_logs_list, logsListArrayList);
        logList.setAdapter(adapter);

        return view;
//        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
//            Log.d("is it connected?", "Yes it is");

//            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/client/logs_activity.php?custid=" + id +
//                    "&month=" + month + "&year=" + year;
//            checkUser(temp);
        }

//        return view;
//    }

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
                            JSONArray jarray = new JSONArray(response);
                            for(int i = 0; i < jarray.length(); i++){
                                JSONObject obj = jarray.getJSONObject(i);
//                                String log_date = obj.getString("log_date");
//                                String pro_percentage = obj.getString("pro_percentage");
//                                String log_id = obj.getString("log_id");

//                                proglist.add(log_id);
//                                progressarraylist.add(new ProgressList(log_id, log_date, pro_percentage));
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
        RequestQueue requestQueue = Volley.newRequestQueue(LogsActivity.this.getActivity());
        requestQueue.add(stringRequest);
    }
}
