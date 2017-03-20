package com.example.joginderpal.result_btech;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by joginderpal on 18-03-2017.
 */
public class UniversityRank extends AppCompatActivity{

    RequestQueue rq;
    Toolbar t1;

    List<String> li1,li2;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    CollapsingToolbarLayout t2;


    int rno;
    int pass;
    int cla;
    String que;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank);
        CoordinatorLayout coordinatorLayout= (CoordinatorLayout) findViewById(R.id.coordinate_rank);
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.slide);
        coordinatorLayout.startAnimation(anim);
        recyclerView = (RecyclerView) findViewById(R.id.rvrank);
        Animation anim1=AnimationUtils.loadAnimation(this,R.anim.slide_recycler);
        recyclerView.startAnimation(anim1);
        rq=Volley.newRequestQueue(this);
        cla=getIntent().getExtras().getInt("cla");
        t1= (Toolbar) findViewById(R.id.toolbar_rank);
        t2= (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        setSupportActionBar(t1);

        li1=new ArrayList<>();
        li2=new ArrayList<>();
        rno=getIntent().getExtras().getInt("rno");
        AppBarLayout appBarLayout= (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isShow=true;
            int scroll=-1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scroll==-1){
                    scroll=appBarLayout.getTotalScrollRange();
                }
                if (scroll+verticalOffset==0){

                    if (cla==1) {
                        t2.setTitle("University Rank");
                    }else if (cla==2){
                        t2.setTitle("College Rank");
                    }
                  //  t2.setScrimAnimationDuration(1000);
                    t2.setCollapsedTitleTextColor(Color.WHITE);
                    t2.setCollapsedTitleTypeface(Typeface.SERIF);

                    isShow=true;
                }
                else if (isShow){

                    if (cla==1) {
                        t2.setTitle("University Rank");
                    }
                    else if (cla==2){
                        t2.setTitle("College Rank");
                    }
                    t2.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
                    t2.setExpandedTitleMarginBottom(40);
                    t2.setExpandedTitleMarginStart(220);
                    //t2.setExp);
                    isShow=false;
                }


            }
        });

      int a=getIntent().getExtras().getInt("lin");

        Bundle extra = getIntent().getBundleExtra("extra");
        final ArrayList<Student> student= (ArrayList<Student>) extra.getSerializable("student_list");
        if (cla==1) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://raw.githubusercontent.com/Himanshuarora97/Result/master/jsonFiles/" + a + "Sem.json", null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            for (Iterator<Student> itr = student.iterator(); itr.hasNext(); ) {
                                try {
                                    String enroll = itr.next().getRollnumber();
                                    // pass = 1;
                                    // que = "";
                                    JSONObject jsonObject = response.getJSONObject(enroll);
                                    String name = jsonObject.getString("name");
                                    String urank = jsonObject.getString("uRank");
                                    li1.add(name);
                                    li2.add(urank);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                            layoutManager = new LinearLayoutManager(UniversityRank.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new RecyclerAdapterrank(li1, li2, (rno),cla);
                            //   recyclerView.findViewHolderForAdapterPosition(45);
                            layoutManager.scrollToPosition(rno);
                            recyclerView.setAdapter(adapter);


                        }
                    },


                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }

            );

            rq.add(jsonObjectRequest);
        }
        else if (cla==2){


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://raw.githubusercontent.com/Himanshuarora97/Result/master/jsonFiles/" + a + "Sem.json", null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            for (Iterator<Student> itr = student.iterator(); itr.hasNext(); ) {
                                try {
                                    String enroll = itr.next().getRollnumber();
                                    // pass = 1;
                                    // que = "";
                                    JSONObject jsonObject = response.getJSONObject(enroll);
                                    String name = jsonObject.getString("name");
                                    String urank = jsonObject.getString("cRank");
                                    li1.add(name);
                                    li2.add(urank);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                            layoutManager = new LinearLayoutManager(UniversityRank.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new RecyclerAdapterrank(li1, li2,(rno),cla);
                            //   recyclerView.findViewHolderForAdapterPosition(45);
                            layoutManager.scrollToPosition(rno);
                            recyclerView.setAdapter(adapter);


                        }
                    },


                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }

            );

            rq.add(jsonObjectRequest);




        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.menusearchone);
        SearchView searchView= (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                String query=newText.toUpperCase();
                final List<String> filteredList = new ArrayList<>();
                final List<String> filteredList1 = new ArrayList<>();
                for (int i = 0; i < li1.size(); i++) {

                    final String text = li1.get(i);
                    if (text.contains(query)) {
                        filteredList.add(li1.get(i));
                        filteredList1.add(li2.get(i));
                    }
                }

                layoutManager = new LinearLayoutManager(UniversityRank.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RecyclerAdapterrank(filteredList,filteredList1,(rno-2),cla);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
