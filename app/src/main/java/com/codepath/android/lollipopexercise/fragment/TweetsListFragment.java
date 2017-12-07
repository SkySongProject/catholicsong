package com.codepath.android.lollipopexercise.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.adapters.MyAdapter;
import com.codepath.android.lollipopexercise.models.Chant;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static com.codepath.android.lollipopexercise.activities.CategoriesListActivity.TAG;

/**
 * Created by FR-PHILIPPE on 11/11/2017.
 */

public class TweetsListFragment extends Fragment {

    // the list of currently playing list
    ArrayList<Chant> songs;
    // the recycle view
    RecyclerView rvSong;
    MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragments_song_list,container,false);
        // find the recycleView
        rvSong=(RecyclerView)v.findViewById(R.id.rvSong);
        // init the arraylist (data source)
        songs=new ArrayList<>();
        // Construct the adapter from data source
        adapter = new MyAdapter(songs);
        //RecycleView setup (Layout manager, user adapter)
        rvSong.setLayoutManager(new LinearLayoutManager(getContext()));
        // set the adapter
        rvSong.setAdapter(adapter);
        return v;
    }

        public void addSong(JSONArray response) {
            try {
                // iterate through result set and create movie object
                for(int i = 0; i < response.length(); i++){
                    Chant cat= new Chant(response.getJSONObject(i));
                    songs.add(cat);
                    // notify adapter that a row was added
                    adapter.notifyItemInserted(songs.size()-1);
                }
                Log.i(TAG, String.format("Loaded %s titres", response.length()));
            } catch (JSONException e) {
                logError("Failed to param list titre de chant", e, true);
            }
        }


    private void logError(String s, JSONException e, boolean b) {    }

}
