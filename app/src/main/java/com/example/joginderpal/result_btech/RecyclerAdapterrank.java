package com.example.joginderpal.result_btech;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;
import java.util.Map;

/**
 * Created by joginderpal on 19-03-2017.
 */
public class RecyclerAdapterrank  extends RecyclerView.Adapter<RecyclerAdapterrank.ViewHolder> {


    Context ctx;
    int lastposition=-1;
    List<String> li1,li2;
    int i;
    int cla;

    public RecyclerAdapterrank(List<String> li1, List<String> li2, int i1,int cla) {

        this.li1=li1;
    //    this.ctx=ctx;
        this.li2=li2;
        this.cla=cla;
        this.i=i;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,urank;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.name);
            urank= (TextView) itemView.findViewById(R.id.univ);
            img= (ImageView) itemView.findViewById(R.id.medal_rank);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();


                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder v = null;
        if(cla==1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_rank, parent, false);
           v = new ViewHolder(view);
        }
        else if (cla==2){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_rank_one, parent, false);
            v = new ViewHolder(view);

        }


        return (ViewHolder) v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int pos=holder.getAdapterPosition();

        if(pos==(i+1)) {

            YoYo.with(Techniques.Tada).playOn(holder.itemView);

        }

        holder.name.setText(li1.get(position));
        holder.urank.setText(li2.get(position));

        if (position==0){
            holder.img.setImageResource(R.drawable.first);
        }
        else if(position>0 && position <=50){
            holder.img.setImageResource(R.drawable.medal);
        }

        else if (position>50 && position<150){

            holder.img.setImageResource(R.drawable.first_two);

        }
        else{
            holder.img.setImageResource(R.drawable.first_three);
        }

    }

    @Override
    public int getItemCount() {
        return li1.size();
    }
}


