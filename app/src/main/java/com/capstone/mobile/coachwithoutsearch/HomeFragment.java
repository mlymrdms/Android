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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    SharedPreferences pref;
    ListView clientList;
//    ArrayList<String> list;
//    ArrayAdapter<String> adapter;
    ArrayList<LogbookList> logbooklist;
    LogbookListAdapter adapter;
    ArrayList id_list;

    public static final String PREFS_NAME = "sharedPref";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        list = new ArrayList<>();
        id_list = new ArrayList<>();

//        btnNext = (ImageView) view.findViewById(R.id.btnNext);
        clientList = (ListView) view.findViewById(R.id.logbook);

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
        logbooklist = new ArrayList<>();

        adapter = new LogbookListAdapter(HomeFragment.this.getContext(), R.layout.fragment_home_list, logbooklist);
        clientList.setAdapter(adapter);

        clientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ClientID = (String) id_list.get(position);
//                String ClientName = (String) parent.getItemAtPosition(position);
                LogbookList custname = (LogbookList) parent.getItemAtPosition(position);
                String clientname = custname.getCustfirstname() + " " + custname.getCustlastname();
//                String ClientName = String.valueOf(custname);
//                Toast.makeText(HomeFragment.this.getActivity(), "Name:\n" + logbookList.getCustlastname() + ", " +
//                                logbookList.getCustfirstname(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(HomeFragment.this.getActivity(), "Name: " + clientname, Toast.LENGTH_SHORT).show();
                Intent client = new Intent(HomeFragment.this.getActivity(), CustomerProfile.class);
//                Log.d("ClientID:", String.valueOf(ClientID));
                client.putExtra("ClientID", ClientID);
                client.putExtra("ClientName", clientname);
                startActivity(client);
            }
        });
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent viewWorkout = new Intent(getActivity(), CustomerProfile.class);
//                startActivity(viewWorkout);
//            }
//        });

//        adapter = new ArrayAdapter<>(HomeFragment.this.getActivity(), android.R.layout.simple_list_item_1, list);
//        clientList.setAdapter(adapter);
//
//        clientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String ClientID = (String) id_list.get(position);
//                String ClientName = (String) parent.getItemAtPosition(position);
//                Intent client = new Intent(HomeFragment.this.getActivity(), CustomerProfile.class);
//                Log.d("ID:", String.valueOf(ClientID));
//                client.putExtra("ClientID", ClientID);
//                client.putExtra("ClientName", ClientName);
//                startActivity(client);
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


//                                Log.i("List check: ", "Client Name: " + custFirstName + ", " + custLastName + "\nTime-In: " + logTime);
//                                result = "Zipcode: " + zipcode + "\nPopulation: " + population + "\nTotal Males: " + males + "\nTotal Females: " + females;
//                                String[] info = new String[]{result};
//                                list.addAll(Arrays.asList(info));
                                id_list.add(logID);
                                logbooklist.add(new LogbookList(custFirstName, custLastName, logTime));
//                                list.add(wrkName);
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
        RequestQueue requestQueue = Volley.newRequestQueue(HomeFragment.this.getActivity());
        requestQueue.add(stringRequest);

    }
}
