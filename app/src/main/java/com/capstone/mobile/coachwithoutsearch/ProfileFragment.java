package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ImageView btnEdit;
    private TextView coachNameHeader, coachID, coachFName, coachLName, coachContact, coachEmail;
    private ViewPager mViewPager;
    private ImageView btnID;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String data = "";

    public static final String PREFS_NAME = "sharedPref";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        pref = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
        editor = pref.edit();

        String id = pref.getString("id", "0");
        String firstName = pref.getString("firstName", "No name");
        String lastName = pref.getString("lastName", "No name");

        coachID = (TextView) view.findViewById(R.id.coachProfID);
        coachNameHeader = (TextView) view.findViewById(R.id.coachProfName);
        coachFName = (TextView) view.findViewById(R.id.stfFName);
        coachLName = (TextView) view.findViewById(R.id.stfLName);
        coachContact = (TextView) view.findViewById(R.id.stfContact);
        coachEmail = (TextView) view.findViewById(R.id.stfEmail);
        btnEdit = (ImageView) view.findViewById(R.id.editButton);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = coachFName.getText().toString();
                String lastname = coachLName.getText().toString();
                String contactnumber = coachContact.getText().toString();
                String email = coachEmail.getText().toString();
                Intent editDetails = new Intent(getActivity(), CoachEdit.class);
                editDetails.putExtra("firstname", firstname);
                editDetails.putExtra("lastname", lastname);
                editDetails.putExtra("contactnumber", contactnumber);
                editDetails.putExtra("email", email);
                startActivity(editDetails);
            }
        });

        coachID.setText(id);
        coachNameHeader.setText(firstName.toUpperCase() + " " + lastName.toUpperCase());

        // Setting ViewPager for each Tabs
        //ViewPager viewPager = (ViewPager) view.findViewById(R.id.container);
        //setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        //TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        //tabs.setupWithViewPager(viewPager);

        btnID = (ImageView) view.findViewById(R.id.IDButton);

        btnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coachID = new Intent(getActivity(), CoachID.class);
                startActivity(coachID);
            }
        });

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            /*new MainActivity.JSONParser().execute("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson");*/
            Log.d("is it connected?", "Yes it is");

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/profile.php?mod=DETAILS&id=" + id;
//            String temp = "http://192.168.43.144/Capstone/app/coach/profile.php?mod=DETAILS&id=" + id;
            checkUser(temp);
//            new LoginActivity.JSONParser().execute("http://192.168.8.101/capstone_main/app/coach/login.php" + "?email=" + email + "&password=" + password);
        }

        return view;
    }

    private boolean isNetworkAvailable() {
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
//                        Log.d("STAFF: ", response);
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for(int i = 0; i < jarray.length(); i++){
                                JSONObject obj = jarray.getJSONObject(i);
                                String staffFname = obj.getString("stf_firstname");
                                String staffLname = obj.getString("stf_lastname");
                                String staffContact = obj.getString("stf_contact");
                                String staffEmail = obj.getString("stf_email");
                                Log.d("STAFF FIRST NAME: ", obj.getString("stf_firstname"));
                                Log.d("STAFF LAST NAME: ", obj.getString("stf_lastname"));
                                Log.d("STAFF EMAIL: ", obj.getString("stf_email"));
                                Log.d("STAFF CONTACT: ", obj.getString("stf_contact"));

                                coachFName.setText(staffFname);
                                coachLName.setText(staffLname);
                                coachEmail.setText(staffEmail);
                                coachContact.setText(staffContact);
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
        RequestQueue requestQueue = Volley.newRequestQueue(ProfileFragment.this.getActivity());
        requestQueue.add(stringRequest);

    }
}

//    static class Adapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public Adapter(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }
//
//    // Add Fragments to Tabs
//    private void setupViewPager(ViewPager viewPager) {
//        Adapter adapter = new Adapter(getChildFragmentManager());
//        adapter.addFragment(new CoachDetails(), "Details");
//        adapter.addFragment(new CoachClasses(), "Classes");
//        viewPager.setAdapter(adapter);
//    }
//}