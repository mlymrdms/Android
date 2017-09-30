package com.capstone.mobile.coachwithoutsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class WorkoutFragment extends Fragment {

    private ImageView btnAdd, btnBMTT, btnBBT, btnMTTR, btnMLBR, btnCBR;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

//        btnAdd = (ImageView) view.findViewById(R.id.addButton);
//        btnBMTT = (ImageView) view.findViewById(R.id.btnNext);
//        btnBBT = (ImageView) view.findViewById(R.id.btnBBT);
//        btnMTTR = (ImageView) view.findViewById(R.id.btnMTTR);
//        btnMLBR = (ImageView) view.findViewById(R.id.btnMLBR);
//        btnCBR = (ImageView) view.findViewById(R.id.btnCBR);
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent addRoutine = new Intent(getActivity(), AddRoutineActivity.class);
//                startActivity(addRoutine);
//            }
//        });
//
//        btnBMTT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent openRoutine = new Intent(getActivity(), BMTTActivity.class);
//                startActivity(openRoutine);
//            }
//        });
//
//        btnBBT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent openRoutine = new Intent(getActivity(), BBTActivity.class);
//                startActivity(openRoutine);
//            }
//        });
//
//        btnMTTR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent openRoutine = new Intent(getActivity(), MTTRActivity.class);
//                startActivity(openRoutine);
//            }
//        });
//
//        btnMLBR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent openRoutine = new Intent(getActivity(), MLBRActivity.class);
//                startActivity(openRoutine);
//            }
//        });
//
//        btnCBR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent openRoutine = new Intent(getActivity(), CBRActivity.class);
//                startActivity(openRoutine);
//            }
//        });

        return view;
    }
}
