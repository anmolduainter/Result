package com.example.joginderpal.result_btech;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bvapp.arcmenulibrary.ArcMenu;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by joginderpal on 15-03-2017.
 */
@SuppressLint("ValidFragment")
public class FragmentOne extends Fragment {

    int i;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RequestQueue rq;
   // String link;
    List<String> li;
//   List<Student> rank;

   ArrayList<Student> student;

    List<String> li0;
    List<String> li1;
    List<String> li2;
    String crank;
    String college;
    String urank;
    CoordinatorLayout coordinatorLayout;
    String rno;
    int pos;

    private static final int[] ITEM_DRAWABLES = {R.drawable.medalone,R.drawable.medalone};

    private static final String[] STR = {"College Rank","University Rank"};

    public FragmentOne(){

    }


    @SuppressLint("ValidFragment")
    public FragmentOne(int i, String link) {
        this.i=i;
       // this.link=link;
        rno=link;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rq= Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_one, container, false);

        recyclerView= (RecyclerView) v.findViewById(R.id.rvactivitymain);
        ArcMenu menu = (ArcMenu) v.findViewById(R.id.arcMenu);
        coordinatorLayout= (CoordinatorLayout) v.findViewById(R.id.coordinate);
        menu.attachToRecyclerView(recyclerView);
        menu.showTooltip(true);
        menu.setToolTipBackColor(Color.WHITE);

        menu.setToolTipCorner(12f);
        menu.setToolTipPadding(12f);
        menu.setToolTipTextColor(Color.BLUE);
        menu.setAnim(300,300,ArcMenu.ANIM_MIDDLE_TO_DOWN,ArcMenu.ANIM_MIDDLE_TO_DOWN,
                     ArcMenu.ANIM_INTERPOLATOR_ACCELERATE_DECLERATE, ArcMenu.ANIM_INTERPOLATOR_ACCELERATE_DECLERATE);

        final int itemCount = ITEM_DRAWABLES.length;
        final int a=i;
        for (int i = 0; i < itemCount; i++) {
            ImageView item = new ImageView(getContext());
            item.setImageResource(ITEM_DRAWABLES[i]);
            final int position = i;
            menu.addItem(item, STR[i], new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   if (STR[position].contains("University")){


                        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "University Rank", false, false);
                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://raw.githubusercontent.com/Himanshuarora97/Result/master/jsonFiles/"+a+"Sem.json", null,

                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        int i1=0;
                                        student=new ArrayList<Student>();
                                        progressDialog.dismiss();
                                        for (Iterator<String> itr=response.keys();itr.hasNext();){

                                            String roll=itr.next();
                                            String uran;
                                            try {
                                                JSONObject jsonObject=response.getJSONObject(roll);
                                                uran=jsonObject.getString("uRank");
                                                if(uran.equals(urank)){
                                                    pos=i1;
                                                }
                                                i1++;
                                                student.add(new Student(roll,Integer.parseInt(uran)));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }


                                        Collections.sort(student,new cmpRank());


                                        Bundle extra = new Bundle();
                                        extra.putSerializable("student_list",student);
                                        Intent i=new Intent(getContext(),UniversityRank.class);
                                        i.putExtra("extra",extra);
                                        i.putExtra("rno",pos);
                                        i.putExtra("cla",1);
                                        i.putExtra("lin",a);
                                        startActivity(i);


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

                    else if (STR[position].contains("College")){



                       final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "University Rank", false, false);
                       JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://raw.githubusercontent.com/Himanshuarora97/Result/master/jsonFiles/"+a+"Sem.json", null,

                               new Response.Listener<JSONObject>() {
                                   @Override
                                   public void onResponse(JSONObject response) {

                                       int i1=0;
                                       student=new ArrayList<Student>();
                                       progressDialog.dismiss();
                                       for (Iterator<String> itr=response.keys();itr.hasNext();){

                                           String roll=itr.next();
                                           String uran;
                                           try {
                                               JSONObject jsonObject=response.getJSONObject(roll);
                                               uran=jsonObject.getString("uRank");
                                               String coll=jsonObject.getString("college");
                                               if(uran.equals(urank)){
                                                   pos=i1;
                                               }
                                               i1++;
                                               if (coll.equals(college)) {
                                                   student.add(new Student(roll, Integer.parseInt(uran)));
                                               }
                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                       }


                                       Collections.sort(student,new cmpRank());


                                       Bundle extra = new Bundle();
                                       extra.putSerializable("student_list",student);
                                       Intent i=new Intent(getContext(),UniversityRank.class);
                                       i.putExtra("extra",extra);
                                       i.putExtra("rno",pos);
                                       i.putExtra("cla",2);
                                       i.putExtra("lin",a);
                                       startActivity(i);


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


                  //  Snackbar.make(coordinatorLayout,STR[position],Snackbar.LENGTH_LONG).show();



                }
            });
        }















        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Thanks For Waiting", false, false);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://raw.githubusercontent.com/Himanshuarora97/Result/master/jsonFiles/"+i+"Sem.json", null,


                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            li=new ArrayList<>();
                            li0=new ArrayList<>();
                            li1=new ArrayList<>();
                            li2=new ArrayList<>();
                           progressDialog.dismiss();
                            JSONObject jsonObject=response.getJSONObject(rno);
                            String name =jsonObject.getString("name");
                           college=jsonObject.getString("college");
                            crank=jsonObject.getString("cRank");
                            urank=jsonObject.getString("uRank");
                            String per=jsonObject.getString("percentage");
                            String cper=jsonObject.getString("cPercentage");

                            JSONObject jsonObject1=jsonObject.getJSONObject("exams");
                            for(  Iterator<String> n=jsonObject1.keys();n.hasNext();){
                                li.add(n.next());
                            }
                            for (int i=0;i<li.size();i++){

                                JSONArray jsonArray=jsonObject1.getJSONArray(li.get(i));
                                li0.add((String) jsonArray.get(0));
                                li1.add((String) jsonArray.get(1));
                                li2.add((String) jsonArray.get(2));

                            }

                      /*      li.add(name);

                            li.add(crank);
                            li.add(urank);
                            li.add(per);
                            li.add(cper);
*/
                            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new RecyclerAdapter(li,li0,li1,li2,name,college,crank,urank,per,cper);
                            recyclerView.setAdapter(adapter);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        rq.add(jsonObjectRequest);

        return v;
    }

}
