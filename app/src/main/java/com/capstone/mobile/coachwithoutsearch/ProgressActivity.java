package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 01/10/2017.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class ProgressActivity extends Fragment {
    View view;
    SharedPreferences pref;
    ListView progressList;
    ArrayList<ProgressList> progressarraylist;
    ProgressListAdapter adapter;
//    ArrayList<String> list;
//    ArrayAdapter<String> adapter;
    ArrayList proglist;

    public static ProgressActivity newInstance() {
        ProgressActivity fragment = new ProgressActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_progress, container, false);

//        list = new ArrayList<>();

        progressList = (ListView) view.findViewById(R.id.progresslist);

        progressarraylist = new ArrayList<>();
        proglist = new ArrayList<>();

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);

        adapter = new ProgressListAdapter(ProgressActivity.this.getActivity(), R.layout.activity_progress_list, progressarraylist);
//        adapter = new ArrayAdapter<>(ProgressActivity.this.getContext(), android.R.layout.simple_list_item_1, list);
        progressList.setAdapter(adapter);

        String id = pref.getString("id", "0");

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/client/progress.php?id=" + id;
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
                                String log_date = obj.getString("log_date");
                                String pro_percentage = obj.getString("pro_percentage");
                                String log_id = obj.getString("log_id");

                                proglist.add(log_id);
                                progressarraylist.add(new ProgressList(log_id, log_date, pro_percentage));
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
        RequestQueue requestQueue = Volley.newRequestQueue(ProgressActivity.this.getActivity());
        requestQueue.add(stringRequest);
    }
}
