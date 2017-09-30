package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Lenovo on 01/10/2017.
 */

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BmiActivity extends Fragment {
    View view;
    EditText et_weight, et_height;
    TextView tv_calculation, tv_result;
    Button bt_calculate;
    Spinner sp_HUnit, sp_WUnit;
    Double weight, height, mHeight, kgWeight;
    String unitW, unitH;


    public static BmiActivity newInstance() {
        BmiActivity fragment = new BmiActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_bmi, container, false);

        tv_calculation = (TextView) view.findViewById(R.id.tv_calculation);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        bt_calculate = (Button) view.findViewById(R.id.bt_calculate);

        bt_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting value from editText
                et_weight = (EditText) view.findViewById(R.id.et_weight);
                et_height = (EditText) view.findViewById(R.id.et_height);
                //converting string to double
                weight = Double.parseDouble(et_weight.getText().toString());
                height = Double.parseDouble(et_height.getText().toString());
                //initiating the spinner
                sp_HUnit = (Spinner) view.findViewById(R.id.sp_HUnit);
                sp_WUnit = (Spinner) view.findViewById(R.id.sp_WUnit);
                //getting the value of spinner
                unitH = sp_HUnit.getSelectedItem().toString();
                unitW = sp_WUnit.getSelectedItem().toString();
                //array adapter
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.WUnit, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                sp_WUnit.setAdapter(weightAdapter);



                Double bmi;
                //if ang weight == kg
                if (unitW == "kg") {
                    if (unitH == "in") { // height == in
                        mHeight = height/39.37;
                        kgWeight = weight;
                    } else { //height == m
                        mHeight = height;
                        kgWeight = weight;
                    }
                } else if(unitW == "lbs") { // if ang unit == lbs
                    if (unitH == "in"){ // height == in
                        kgWeight = weight/2.2;
                        mHeight = height/39.37;
                    } else { //height == m
                        kgWeight = weight/2.2;
                        mHeight = height;
                    }
                }

                bmi = kgWeight/(mHeight*mHeight);

                tv_calculation.setText(unitW);
                tv_result.setText(unitH);
                /*
                if(bmi <18.5){
                    tv_result.setText("Underweight");
                }else if(bmi >= 18.5  && bmi <=24.9){
                    tv_result.setText("Normal");
                }else if(bmi == 25 && bmi <= 29.9){
                    tv_result.setText("Overweight");
                } else {
                    tv_result.setText("Obese");
                }
                */
            }


        });
        return view;
    }
}
