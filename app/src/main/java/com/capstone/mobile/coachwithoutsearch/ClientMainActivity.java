package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 30/09/2017.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ClientMainActivity extends AppCompatActivity {
    SharedPreferences pref;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_routines:
                    ClientRoutinesActivity routinesActivity = new ClientRoutinesActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, routinesActivity).commit();
                    return true;
                case R.id.navigation_progress:
                    ProgressActivity progressActivity = new ProgressActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, progressActivity).commit();
                    return true;
                case R.id.navigation_logs:
                    LogsActivity logsActivity = new LogsActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, logsActivity).commit();
                    return true;
                case R.id.navigation_bmi:
                    BmiActivity bmiActivity = new BmiActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, bmiActivity).commit();
                    return true;
                case R.id.navigation_more:
                    MoreActivity moreActivity = new MoreActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, moreActivity).commit();
                    return true;
            }
            return false;
        }

    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

        pref = getSharedPreferences("sharedPref", MODE_PRIVATE);

        String id = pref.getString("id", "0");

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Log.d("Main Activity Pref", id);
    }
}
