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

public class MainActivity extends AppCompatActivity {

    private TextView headerName, coachName;
    SharedPreferences pref;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    headerName.setText(R.string.header_home);
                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, homeFragment).commit();
                    return true;
                case R.id.nav_profile:
                    headerName.setText(R.string.header_profile);
                    ProfileFragment profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, profileFragment).commit();
                    return true;
                case R.id.nav_classes:
                    headerName.setText(R.string.header_classes);
                    CoachClasses classesFragment = new CoachClasses();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, classesFragment).commit();
                    return true;
                case R.id.nav_workout:
                    headerName.setText(R.string.header_workout);
                    WorkoutFragment workoutFragment = new WorkoutFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, workoutFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("sharedPref", MODE_PRIVATE);

        String id = pref.getString("id", "0");
        String firstName = pref.getString("firstName", "No name");
        String lastName = pref.getString("lastName", "No name");

        headerName = (TextView) findViewById(R.id.header_name);
        coachName = (TextView) findViewById(R.id.coach_name);

        headerName.setText(R.string.header_home);
        coachName.setText(lastName + ", " + firstName);


        coachName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ProfileFragment profileFragment = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, profileFragment).commit();
                headerName.setText(R.string.header_profile);
            }
        });

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        navigation.enable
        Log.d("Main Activity Pref", id);
    }

}
