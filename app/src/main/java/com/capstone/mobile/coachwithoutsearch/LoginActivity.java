package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class    LoginActivity extends AppCompatActivity {
    public String webPage;
    EditText stfEmail, stfPassword;
    String email, password;
    JSONObject object;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String PREFS_NAME = "sharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        editor = pref.edit();

        Boolean login = pref.getBoolean("login", false);
        String level = pref.getString("level", "COACH");
//        String login = pref.getString("login","false");
//        Log.d("PREFS",login);

        if(login){
            //if coach level start main coach activity
            /*if(level.equals("COACH")) {
                Intent straight = new Intent(this, MainActivity.class);
                startActivity(straight);
            }
            else {
                //client activity
                Intent client = new Intent(this, ClientMainActivity.class);
                startActivity(client);
            }*/
            Log.d("DA LEVEL", level);
            redirect(level);
        }
        else{
            Intent loginpage = new Intent(this, LoginActivity.class);
            new Intent(this, LoginActivity.class);
        }
    }

    public void redirect(String level){
        if(level == "COACH"){
            Intent straight = new Intent(this, MainActivity.class);
            startActivity(straight);
        }
        else{
            Intent client = new Intent(this, ClientMainActivity.class);
            startActivity(client);
        }
    }

    public void login(View view){
        stfEmail = (EditText) findViewById(R.id.stfEmail);
        stfPassword = (EditText) findViewById(R.id.stfPassword);

        //check if network is available
        if(isNetworkAvailable()){
            //run AsyncTask JSONParser
            /*new MainActivity.JSONParser().execute("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson");*/
            Log.d("is it connected?", "Yes it is");
            Log.d("email", stfEmail.getText().toString());
            Log.d("password", stfPassword.getText().toString());

            email = stfEmail.getText().toString();
            password = stfPassword.getText().toString();

            String temp = "http://sixonezerozeromaf.000webhostapp.com/app/login.php" + "?email=" + email + "&password=" + password;
//            String temp = "http://192.168.43.144/Capstone/app/login.php" + "?email=" + email + "&password=" + password;
            checkUser(temp);
//            new LoginActivity.JSONParser().execute("http://192.168.8.101/capstone_main/app/coach/login.php" + "?email=" + email + "&password=" + password);
        }
    }


    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkUser(String url) {
        final SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOGIN: ",response);
                        try {
                            JSONObject reader = new JSONObject(response);
                            Log.d("ACTION: ", reader.getString("action"));
                            Log.d("ID: ", reader.getString("id"));
                            Log.d("FIRSTNAME: ", reader.getString("firstName"));
                            Log.d("LASTNAME: ", reader.getString("lastName"));
                            Log.d("LEVEL: ", reader.getString("lvl"));

                            editor.putString("id", reader.getString("id"));
                            editor.putString("firstName", reader.getString("firstName"));
                            editor.putString("lastName", reader.getString("lastName"));
                            editor.putString("level", reader.getString("lvl"));
                            editor.putBoolean("login", true);
                            editor.apply();

                            Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mainActivity);
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
