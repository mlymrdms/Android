package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClassesActivity extends AppCompatActivity {

    String clsCustName, clsMemStatus, clsValid, clsSessions;
    TextView ClassName;
    ListView classList;
    ArrayList<ClassList> classesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        ClassName = (TextView) findViewById(R.id.txtClassName);
        classList = (ListView) findViewById(R.id.classlist);

        classesList = new ArrayList<>();
        Intent name = getIntent();
        String id = name.getStringExtra("ClassID");
        String classname = name.getStringExtra("ClassName");

        ClassListAdapter adapter = new ClassListAdapter(this, R.layout.activity_classes_list, classesList);
        classList.setAdapter(adapter);

        ClassName.setText(classname);

        Log.d("clsID: ", id);
        Log.d("clsNAME: ", classname);

        //check if network is available
        if (isNetworkAvailable()) {
            //run AsyncTask JSONParser
            Log.d("is it connected?", "Yes it is");

//            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/classlist.php?cls_id=" + id;
            String temp = "http://192.168.43.253/Capstone/app/coach/classlist.php?cls_id=" + id;
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
                        Log.d("CLASS LIST: ", response);
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for (int i = 0; i < jarray.length(); i++) {
                                JSONObject obj = jarray.getJSONObject(i);
                                String clsCustName = obj.getString("cust_name");
                                String clsMemStatus = obj.getString("mem_status");
                                String clsValid = obj.getString("valid");
                                String clsSessions = obj.getString("rec_session_remain");
                                Log.d("CUST NAME: ", clsCustName);
                                Log.d("MEM STATUS: ", clsMemStatus);
                                Log.d("VALID: ", clsValid);
                                Log.d("SESSION REMAIN: ", clsSessions);

                                classesList.add(new ClassList(clsCustName, clsMemStatus, clsValid, clsSessions));
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
