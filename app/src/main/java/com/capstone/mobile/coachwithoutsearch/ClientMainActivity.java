package com.capstone.mobile.coachwithoutsearch;


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
    private TextView headerName, coachName;
    SharedPreferences pref;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_routines:
                    headerName.setText(R.string.header_workout);
                    ClientRoutinesActivity routinesActivity = new ClientRoutinesActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, routinesActivity).commit();
                    return true;
                case R.id.navigation_progress:
                    headerName.setText(R.string.header_progress);
                    ProgressActivity progressActivity = new ProgressActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, progressActivity).commit();
                    return true;
                case R.id.navigation_logs:
                    headerName.setText(R.string.header_logs);
                    LogsActivity logsActivity = new LogsActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, logsActivity).commit();
                    return true;
                case R.id.navigation_bmi:
                    headerName.setText(R.string.header_bmi);
                    BmiActivity bmiActivity = new BmiActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, bmiActivity).commit();
                    return true;
                case R.id.navigation_more:
                    headerName.setText(R.string.header_more);
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
        String firstName = pref.getString("firstName", "No name");
        String lastName = pref.getString("lastName", "No name");

        headerName = (TextView) findViewById(R.id.header_name);
        coachName = (TextView) findViewById(R.id.coach_name);

        headerName.setText(R.string.header_home);
        coachName.setText(lastName + ", " + firstName);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        Log.d("Main Activity Pref", id);
    }
}
