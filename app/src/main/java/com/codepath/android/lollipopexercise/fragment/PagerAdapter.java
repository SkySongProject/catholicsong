package com.codepath.android.lollipopexercise.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by FR-PHILIPPE on 11/11/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    // return the total # of Fragment
    private String tabTitle[]= new String[]{"Derniers Vues","Favoris","Top"};
    private Context context;
    public PagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context=context;
    }
    @Override
    public int getCount() {
        return 3;
    }

    // return the fragment to use depending on the position

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new RecentlyFragment();
        }else if(position==1){
            return  new FavoriteFragment();
        }else if(position==2){
            return  new TopSongFragment();
        }else {
            return  null;
        }
    }

    // return the fragment title


    @Override
    public CharSequence getPageTitle(int position) {
        //ggenerate title based on item position
        return tabTitle[position];
    }
}
