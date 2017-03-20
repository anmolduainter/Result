package com.example.joginderpal.result_btech;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

         RequestQueue rq;
         Toolbar t;
       SharedPreferences sd;
    String college;
    SharedPreferences.Editor editor;
         List<Integer> li1;
    List<String> li;
    EditText ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        Toolbar t = (Toolbar) findViewById(R.id.tool);
        TextInputLayout t1 = (TextInputLayout) findViewById(R.id.editt);
        ed1 = (EditText) findViewById(R.id.edit);
        setSupportActionBar(t);
        // ButterKnife.bind(this);
        li1 = new ArrayList<>();
        li = new ArrayList<>();
        YoYo.with(Techniques.Tada).playOn(findViewById(R.id.toolbarresult));
        YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.button));
        rq = Volley.newRequestQueue(MainActivity.this);
        //    getSupportActionBar().setTitle("RESULT");
        //    getSupportActionBar().setSubtitle("IPU");
        //    t.setLogo(R.drawable.exam);
        //   t.setTitleTextColor(Color.WHITE);
        Button b1 = (Button) findViewById(R.id.button);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    json();

              /*  Intent i=new Intent(MainActivity.this,second.class);
                i.putExtra("link",ed1.getText().toString());
                startActivity(i);
                */
                }
            });

        }

        else {

            new AlertDialog.Builder(this)
                    .setMessage("WIFI is Not Connected ....")
                    .setCancelable(false)
                    .setPositiveButton("First Connect it ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .show();

        }



        }

    private void json() {


        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "Please Wait..", false, false);
        JsonObjectRequest json = null;
        for (int i = 1; i < 9; i++) {

            final int finalI = i;

            json = new JsonObjectRequest(Request.Method.GET, "https://raw.githubusercontent.com/Himanshuarora97/Result/master/jsonFiles/" + i + "Sem.json", null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //  progressDialog.dismiss();
                            //   sd = getApplicationContext().getSharedPreferences(finalI +"SEM", MODE_PRIVATE);
                            //   editor = sd.edit();
                            li1.add(finalI);
                            try {
                                JSONObject object = response.getJSONObject(ed1.getText().toString());
                                String name = object.getString("name");
                                college=object.getString("college");
                                li.add(name);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                                  /*  Intent i1 = new Intent(MainActivity.this, second.class);
                                    i1.putExtra("link", ed1.getText().toString());
                                    i1.putExtra("link1", (Serializable) li1);
                                    i1.putExtra("name",li.get(0));
                                    startActivity(i1);*/
                            //  ed1.setText(String.valueOf(finalI));
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressDialog.dismiss();


                        }
                    }

            );
            rq.add(json);
        }

        pass();


    }


    private void pass() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {



                        if (!li1.isEmpty()) {

                            Intent i1 = new Intent(MainActivity.this, second.class);
                            i1.putExtra("link", ed1.getText().toString());
                            i1.putExtra("college",college);
                            i1.putExtra("link1", (Serializable) li1);
                            i1.putExtra("name", li.get(0));
                            startActivity(i1);
                        }

                        else{

                            new AlertDialog.Builder(MainActivity.this)
                                    .setMessage("Internet To Slow  :(")
                                    .setCancelable(false)
                                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            li1.clear();
                                            json();
                                        }
                                    })
                                    .show();



                        }




                    }

            },3000);
    }





    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

    @Override
    protected void onResume() {
        super.onResume();
        li1.clear();
        li.clear();
        college=null;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
