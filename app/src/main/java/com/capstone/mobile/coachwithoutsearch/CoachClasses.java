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
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class CoachClasses extends Fragment {

    private TextView textview;
    ImageView btnNext;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    RequestQueue requestQueue;
//    RecyclerView recyclerView;

    public static final String PREFS_NAME = "sharedPref";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coach_classes, container, false);

        btnNext = (ImageView) view.findViewById(R.id.btnNext);

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
        editor = pref.edit();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openClasses = new Intent(getActivity(), ClassesActivity.class);
                startActivity(openClasses);
            }
        });

        requestQueue = Volley.newRequestQueue(CoachClasses.this.getActivity());

        //for recyclerview
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        String id = pref.getString("id", "0");

        //textview = (TextView) view.findViewById(R.id.tvdata);

        //String JsonURL = "http://192.168.8.101/capstone_main/app/coach/profile.php?mod=CLASSES&id=" + id;

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            /*new MainActivity.JSONParser().execute("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson");*/
            Log.d("is it connected?", "Yes it is");

            String temp = "http://192.168.43.144/capstone_main/app/coach/profile.php?mod=CLASSES&id=" + id;
            checkUser(temp);
//            new LoginActivity.JSONParser().execute("http://192.168.8.101/capstone_main/app/coach/login.php" + "?email=" + email + "&password=" + password);
        }

        return view;
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkUser(String url) {
        //final SharedPreferences.Editor editor = this.getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("CLASSES: ", response);
//                        ArrayList<CClasses> classList = new ArrayList<CClasses>();
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for(int i = 0; i < jarray.length(); i++){
//                                HashMap<String, String> map = new HashMap<>();
                                JSONObject obj = jarray.getJSONObject(i);
                                String clsID = obj.getString("cls_id");
                                String clsName = obj.getString("cls_name");
                                Log.d("CLASS ID: ", clsID);
                                Log.d("CLASS NAME: ", clsName);

//                                classList.add(new CClasses(clsID, clsName));
                            }
//                            ClassAdapter adapter = new ClassAdapter(CoachClasses.this, classList);
//                            recyclerView.setAdapter(adapter);
//                            JSONObject obj = jarray.getJSONObject(0);
//                            String clsID = obj.getString("cls_id");
//                            String clsName = obj.getString("cls_name");
//                            Log.d("CLASS ID: ", clsID);
//                            Log.d("CLASS NAME: ", clsName);
//                            JSONObject reader = new JSONObject(response);
//
//                            for(int i = 0; i < reader.length(); i++) {
//                                JSONObject read = reader.getJSONObject(i);
//                            }
//                            String staffFname = reader.getString("stf_firstname");
//                            String staffLname = reader.getString("stf_lastname");
//                            String staffContact = reader.getString("stf_contact");
//                            String staffEmail = reader.getString("stf_email");
//                            Log.d("STAFF FIRST NAME: ", reader.getString("stf_firstname"));
//                            Log.d("STAFF LAST NAME: ", reader.getString("stf_lastname"));
//                            Log.d("STAFF EMAIL: ", reader.getString("stf_email"));
//                            Log.d("STAFF CONTACT: ", reader.getString("stf_contact"));

//                            coachFName.setText(staffFname);
//                            coachLName.setText(staffLname);
//                            coachEmail.setText(staffEmail);
//                            coachContact.setText(staffContact);

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
        RequestQueue requestQueue = Volley.newRequestQueue(CoachClasses.this.getActivity());
        requestQueue.add(stringRequest);

    }
}
