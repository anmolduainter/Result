package com.example.joginderpal.result_btech;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;
import java.util.Map;

/**
 * Created by joginderpal on 16-03-2017.
 */
public class RecyclerAdapter   extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context ctx;
    String name;
    String college;
    String crank;
    String urank;
    String per;
    List<String> li,li0,li1,li2;
    String cper;

    public RecyclerAdapter(List<String> li, List<String> li0, List<String> li1, List<String> li2, String name, String college, String crank, String urank, String per, String cper) {

        this.name=name;
        this.college=college;
        this.urank=urank;
        this.li=li;
        this.li0=li0;
        this.li1=li1;
        this.li2=li2;
        this.crank=crank;
        this.per=per;
        this.cper=cper;

    }


    class ViewHolder0 extends RecyclerView.ViewHolder{

       public TextView tx1,tx2,tx3,tx7;

        public ViewHolder0(View itemView) {
            super(itemView);
            tx1= (TextView) itemView.findViewById(R.id.urank);
            tx2= (TextView) itemView.findViewById(R.id.crank);
            tx3= (TextView) itemView.findViewById(R.id.per);
            tx7= (TextView) itemView.findViewById(R.id.cper);
          //  tx4= (TextView) itemView.findViewById(R.id.cper);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                }
            });
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{

        public TextView tx6,tx4,tx5;

        public ViewHolder1(View itemView) {
            super(itemView);
            tx4= (TextView) itemView.findViewById(R.id.subject);
            tx5= (TextView) itemView.findViewById(R.id.totalm);
            tx6= (TextView) itemView.findViewById(R.id.credit);
            //  tx4= (TextView) itemView.findViewById(R.id.cper);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                }
            });
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder v = null;
        switch(viewType){
            case 0:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
                 v=new ViewHolder0(view);
                 return  v;
            case 1:
                 view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout_one,parent,false);
                v=new ViewHolder1(view);
                return v;
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


      //  int lastpos=-1;

   //     if (position>lastpos){

          //  YoYo.with(Techniques.FadeInDown).duration(1000).playOn(holder.itemView);
     //       lastpos++;

     //   }

        if (holder.getItemViewType()==0) {
            ViewHolder0 v = (ViewHolder0) holder;
            v.tx1.setText(urank);
            v.tx2.setText(crank);
            v.tx3.setText(per);
            v.tx7.setText(cper);
            //  holder.tx4.setText(cper);
       //     YoYo.with(Techniques.Shake).playOn(holder.itemView.findViewById(R.id.tx2));
       //     YoYo.with(Techniques.Wobble).playOn(holder.itemView.findViewById(R.id.textView3));
       //     YoYo.with(Techniques.RubberBand).playOn(holder.itemView.findViewById(R.id.textView4));

        }
        if (holder.getItemViewType()==1) {
            ViewHolder1 v1= (ViewHolder1) holder;
            v1.tx4.setText(li.get(position));
            YoYo.with(Techniques.RubberBand).duration(2000).playOn(((ViewHolder1) holder).tx4);
            v1.tx5.setText(li1.get(position));
            YoYo.with(Techniques.Tada).duration(2000).playOn(((ViewHolder1) holder).tx5);
            v1.tx6.setText(li2.get(position));
        }



    }
    @Override
    public int getItemCount() {
        return li.size();

    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        else{
            return 1;
        }
    }
}

