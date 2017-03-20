package com.example.joginderpal.result_btech;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 15-03-2017.
 */
public class second extends AppCompatActivity {

        private Toolbar toolbar;
        private TabLayout tabLayout;
        private ViewPager viewPager;

        List<Integer> li1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.second);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

          //  toolbar.setLogo(R.drawable.medal);
            String c=getIntent().getExtras().getString("college");
            String[] a=c.split(" ");
            String c2="";
            char[] c1=new char[a.length];
            for(int i=0;i<a.length;i++){
                c1[i]=a[i].charAt(0);
            }

            for(int i=0;i<a.length;i++){
                c2=c2+c1[i]+".";
            }

            getSupportActionBar().setTitle(getIntent().getExtras().getString("name"));
            getSupportActionBar().setSubtitle("["+getIntent().getExtras().getString("link")+"]"+" College : "+c2.replace("O.",""));
           // getSupportActionBar().setLogo(R.drawable.back);
            li1=new ArrayList<>();
            li1= (List<Integer>) getIntent().getSerializableExtra("link1");
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                @Override
                public void transformPage(View page, float position) {


                    final float normalizedposition = Math.abs(Math.abs(position) - 1);
                    page.setScaleX(normalizedposition);
                  //  page.setRotationX(position * -100);
                    page.setRotationY(position * -90);

                }
            });
            setupViewPager(viewPager);
            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            setupicons();
        }


    public void setupicons(){

              for(int i=0;i<li1.size();i++){
                  tabLayout.getTabAt(i).setIcon(R.drawable.tab);
              }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i=0;i<li1.size();i++) {
            adapter.addFragment(new FragmentOne(li1.get(i),getIntent().getExtras().getString("link")),String.valueOf(li1.get(i))+"SEM");
        }
     //   adapter.addFragment(new TwoFragment(), "TWO");
    //    adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
}





