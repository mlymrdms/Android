package com.capstone.mobile.coachwithoutsearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Emily Marie Adams on 10/5/2017.
 */

public class ViewWorkoutListAdapter extends ArrayAdapter<ViewWorkoutList> {

    private final ViewWorkoutListAdapter adapter;
    int cResource;
    Context context;

    public ViewWorkoutListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ViewWorkoutList> objects) {
        super(context, resource, objects);
        this.cResource = resource;
        this.adapter = this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final String acpid = getItem(position).getAcpid();
        String actname = getItem(position).getActname();
        String wrasets = getItem(position).getWrasets();
        final String actstatus = getItem(position).getActstatus();

        ViewWorkoutList viewWorkoutList = new ViewWorkoutList(acpid, actname, wrasets, actstatus);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(cResource, parent, false);

        TextView actName = (TextView) convertView.findViewById(R.id.vwActivityName);
        TextView wraSets = (TextView) convertView.findViewById(R.id.vwSets);
        final TextView acpID = (TextView) convertView.findViewById(R.id.txtacp);
        final TextView actStatus = (TextView) convertView.findViewById(R.id.vwStatus);
        Button completebtn = (Button) convertView.findViewById(R.id.btnComplete);
        Button incompletebtn = (Button) convertView.findViewById(R.id.btnIncomplete);
        Button skippedbtn = (Button) convertView.findViewById(R.id.btnSkipped);
        SwipeRefreshLayout swipeListView = (SwipeRefreshLayout) convertView.findViewById(R.id.swipe);

        actName.setText(actname);
        wraSets.setText(wrasets);
        actStatus.setText(actstatus);

        if(actstatus.equals("COMPLETE")){
            completebtn.setVisibility(View.GONE);
            incompletebtn.setVisibility(View.GONE);
            skippedbtn.setVisibility(View.GONE);
        } else if(actstatus.equals("INCOMPLETE")){
            incompletebtn.setVisibility(View.GONE);
            skippedbtn.setVisibility(View.GONE);
        } else if(actstatus.equals("SKIPPED")){
            skippedbtn.setVisibility(View.GONE);
        }

        completebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "ACP ID: " + acpid, Toast.LENGTH_SHORT).show();
                acpID.setText(acpid);
                actStatus.setText("COMPLETE");

                if (isNetworkAvailable()) {
                    //run AsyncTask JSONParser
                    Log.d("is it connected?", "Yes it is");

                    String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/activityprogress.php?acp_id=" + acpid +"&status=" + actStatus.getText();
//                    Toast.makeText(getContext(), "LINK: " + temp, Toast.LENGTH_SHORT).show();
                    setComplete(temp);
//                    adapter.notifyDataSetChanged();
//                    getContext().startActivity(new Intent(getContext(), CustomerViewWorkout.class));
//                    ((Activity)context).finish();
                }
            }
        });

        incompletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acpID.setText(acpid);
                actStatus.setText("INCOMPLETE");

                if (isNetworkAvailable()) {
                    //run AsyncTask JSONParser
                    Log.d("is it connected?", "Yes it is");

                    String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/activityprogress.php?acp_id=" + acpid +"&status=" + actStatus.getText();
//                    Toast.makeText(getContext(), "LINK: " + temp, Toast.LENGTH_SHORT).show();
                    setComplete(temp);
                    adapter.notifyDataSetChanged();
//                    getContext().startActivity(new Intent(getContext(), CustomerViewWorkout.class));
//                    ((Activity)getContext()).finish();
                }
//                Toast.makeText(getContext(), "ACPID: " + acpid, Toast.LENGTH_SHORT).show();
//                View complete = v.findViewById(R.id.btnComplete);
//                View incomplete = v.findViewById(R.id.btnIncomplete);
//                View skipped = v.findViewById(R.id.btnSkipped);
//                complete.setVisibility(View.GONE);
//                incomplete.setVisibility(View.GONE);
//                skipped.setVisibility(View.GONE);
            }
        });

        skippedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acpID.setText(acpid);
                actStatus.setText("SKIPPED");

                if (isNetworkAvailable()) {
                    //run AsyncTask JSONParser
                    Log.d("is it connected?", "Yes it is");

                    String temp = "http://sixonezerozeromaf.000webhostapp.com/app/coach/activityprogress.php?acp_id=" + acpid +"&status=" + actStatus.getText();
//                    Toast.makeText(getContext(), "LINK: " + temp, Toast.LENGTH_SHORT).show();
                    setComplete(temp);
//                    adapter.notifyDataSetChanged();
//                    getContext().startActivity(new Intent(getContext(), CustomerViewWorkout.class));
//                    ((Activity)getContext()).finish();
                }
//                Toast.makeText(getContext(), "ACPID: " + acpid, Toast.LENGTH_SHORT).show();
//                View complete = v.findViewById(R.id.btnComplete);
//                View incomplete = v.findViewById(R.id.btnIncomplete);
//                View skipped = v.findViewById(R.id.btnSkipped);
//                complete.setVisibility(View.GONE);
//                incomplete.setVisibility(View.GONE);
//                skipped.setVisibility(View.GONE);
            }
        });

//        swipeListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeListView.setRefreshing(false);
//            }
//        });


        return convertView;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void setComplete(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("GA CONNECT:", response);
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
        RequestQueue requestQueue = Volley.newRequestQueue(ViewWorkoutListAdapter.this.getContext());
        requestQueue.add(stringRequest);

    }
}
