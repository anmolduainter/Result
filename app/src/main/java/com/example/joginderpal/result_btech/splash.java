package com.example.joginderpal.result_btech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by joginderpal on 15-03-2017.
 */
public class splash extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        setupWindowAnimations();
        YoYo.with(Techniques.SlideInDown).duration(1500).playOn(findViewById(R.id.imageView));
      //  YoYo.with(Techniques.FlipOutX).delay(750).duration(750).playOn(findViewById(R.id.imageView));
        YoYo.with(Techniques.Flash).duration(1000).repeat(2).playOn(findViewById(R.id.textView));
        YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(findViewById(R.id.textView2));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(splash.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        },2000);
    }

    private void setupWindowAnimations() {

        Slide slide=new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);

    }
}