//package com.capstone.mobile.coachwithoutsearch;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static android.content.Context.CONNECTIVITY_SERVICE;
//import static android.content.Context.MODE_PRIVATE;
//
//public class CoachDetails extends Fragment {
//
//    private ImageView btnEdit;
//    private TextView coachFName, coachLName, coachContact, coachEmail;
//    SharedPreferences pref;
//    SharedPreferences.Editor editor;
//    String data = "";
//
//    public static final String PREFS_NAME = "sharedPref";
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_coach_details, container, false);
//
//        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
//        editor = pref.edit();
//
//        String id = pref.getString("id", "0");
//
//        coachFName = (TextView) view.findViewById(R.id.stfFName);
//        coachLName = (TextView) view.findViewById(R.id.stfLName);
//        coachContact = (TextView) view.findViewById(R.id.stfEmail);
//        coachEmail = (TextView) view.findViewById(R.id.stfContact);
//        btnEdit = (ImageView) view.findViewById(R.id.editButton);
//
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editDetails = new Intent(getActivity(), CoachEdit.class);
//                startActivity(editDetails);
//            }
//        });
//
//        //check if network is available
//        if(isNetworkAvailable()){
//            //run AsyncTask JSONParser
//            /*new MainActivity.JSONParser().execute("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson");*/
//            Log.d("is it connected?", "Yes it is");
//
//            String temp = "http://192.168.8.101/capstone_main/app/coach/profile.php?mod=DETAILS&id=" + id;
//            checkUser(temp);
////            new LoginActivity.JSONParser().execute("http://192.168.8.101/capstone_main/app/coach/login.php" + "?email=" + email + "&password=" + password);
//        }
//
//        return view;
//    }
//
//    private boolean isNetworkAvailable(){
//        ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }
//
//    private void checkUser(String url) {
//        //final SharedPreferences.Editor editor = this.getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        Log.d("STAFF: ", response);
//                        try {
//                            JSONObject reader = new JSONObject(response);
//                            String staffFname = reader.getString("stf_firstname");
//                            String staffLname = reader.getString("stf_lastname");
//                            String staffContact = reader.getString("stf_contact");
//                            String staffEmail = reader.getString("stf_email");
//                            Log.d("STAFF FIRST NAME: ", reader.getString("stf_firstname"));
//                            Log.d("STAFF LAST NAME: ", reader.getString("stf_lastname"));
//                            Log.d("STAFF EMAIL: ", reader.getString("stf_email"));
//                            Log.d("STAFF CONTACT: ", reader.getString("stf_contact"));
//
//                            coachFName.setText(staffFname);
//                            coachLName.setText(staffLname);
//                            coachEmail.setText(staffEmail);
//                            coachContact.setText(staffContact);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("error", "server error");
//            }
//        }) {
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("type", "1");
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(CoachDetails.this.getActivity());
//        requestQueue.add(stringRequest);
//
//    }
//}
