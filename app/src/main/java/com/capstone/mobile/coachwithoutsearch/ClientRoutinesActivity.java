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
    ListView routinelist;
    ArrayList<ClientRoutine> routinearraylist;
    ArrayList id_list;
    ClientRoutineAdapter adapter;

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
        routinelist = (ListView) view.findViewById(R.id.workoutslistview);

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);

        id_list = new ArrayList<>();
        routinearraylist = new ArrayList<>();

        adapter = new ClientRoutineAdapter(this.getActivity(), R.layout.activity_routines_client_list, routinearraylist);
        routinelist.setAdapter(adapter);

        String id = pref.getString("id", "0");
        Log.d("CUSTID: ", id);

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/client/routine_activity.php?custid=" + id;
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
                                String act_name = obj.getString("actname");
                                String acp_status = obj.getString("acpstatus");
                                String wra_sets = obj.getString("wrasets");

                                Log.d("LOGID", act_name);
                                Log.d("LOGID", acp_status);
                                Log.d("LOGID", wra_sets);

                                routinearraylist.add(new ClientRoutine(act_name, acp_status, wra_sets));
//                            JSONArray jarray= new JSONArray(response);
//                            for(int i = 0; i < jarray.length(); i++){
//                                JSONObject obj = jarray.getJSONObject(i);
//                                String log_id = obj.getString("logid");
//                                String log_date = obj.getString("log_date");
//                                String time_in = obj.getString("logtimein");
//                                String clsname = obj.getString("clsname");
//                                String recid = obj.getString("recid");
//                                String stfFName = obj.getString("stffirstname");
//                                String stfLName = obj.getString("stflastname");
//
//                                Log.d("LOGID", log_date);
//                                Log.d("LOGID", time_in);
//                                Log.d("LOGID", clsname);
//                                Log.d("LOGID", stfFName);
//                                Log.d("LOGID", stfLName);
//                            JSONArray jarray = new JSONArray(response);
//                            // Create log to visualize data retrieved
////                            Log.i("Data Object", jarray.toString());
//
//                            for (int i = 1; i < jarray.length(); i++) {
//                                JSONObject jsono = jarray.getJSONObject(i);
//                                String log_id = jsono.getString("logid");
//                                String log_date = jsono.getString("log_date");
//                                String time_in = jsono.getString("logtimein");
//                                String clsname = jsono.getString("clsname");
//                                String recid = jsono.getString("recid");
//                                String stfFName = jsono.getString("stffirstname");
//                                String stfLName = jsono.getString("stflastname");
//
//                                Log.d("LOGID", log_date);
//                                Log.d("LOGID", time_in);
//                                Log.d("LOGID", clsname);
//                                Log.d("LOGID", stfFName);
//                                Log.d("LOGID", stfLName);

//                            JSONObject jsono = new JSONObject(response);
//                            JSONArray jarray = jsono.getJSONArray("workout");
//                            // Create log to visualize data retrieved
//                            Log.i("Data Object", jarray.toString());
//
//                            for (int i = 1; i < jarray.length(); i++) {
//                                // JSONObject object = jarray.getJSONObject(i);
//                                // String details = object.getString("view");
//                                // String mProperties = "{" + "\"view\"" + ":[" + details + "]}";
//
//                                //removed "(JSONArray)" due to redundancy
//                                //JSONArray sarray = (JSONArray) jarray.getJSONArray(i);
//
//                                //removed "(JSONArray)" due to redundancy
//                                //JSONArray sarray = (JSONArray) jarray.getJSONArray(i);
//                                JSONArray sarray = jarray.getJSONArray(i);
//                                String act_name = sarray.getString(1);
//                                String acp_status = sarray.getString(2);
//                                String wra_sets = sarray.getString(3);
//
//                                Log.d("LOGID", act_name);
//                                Log.d("LOGID", acp_status);
//                                Log.d("LOGID", wra_sets);

//                                id_list.add(log_id);
//                                logsListArrayList.add(new LogsList(log_date, time_in, clsname, stfFName, stfLName));
//                                list.add(log_date + " : " + pro_percentage);
//                                adapter.notifyDataSetChanged();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);
    }
}
