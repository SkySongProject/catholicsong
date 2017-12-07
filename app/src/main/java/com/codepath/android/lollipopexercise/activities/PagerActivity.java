package com.codepath.android.lollipopexercise.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.fragment.PagerAdapter;

public class PagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        // get the view pager
        ViewPager vPager= (ViewPager)findViewById(R.id.viewPager);
        //set the adapter for the pager
        vPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),this));
        //setup the tablayout to ise the view pager
        TabLayout tabLayout=(TabLayout)findViewById(R.id.slinding_tabs);
        tabLayout.setupWithViewPager(vPager);

    }



}
