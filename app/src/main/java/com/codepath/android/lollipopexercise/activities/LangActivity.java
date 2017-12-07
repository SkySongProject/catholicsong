package com.codepath.android.lollipopexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.adapters.MyAdapter;
import com.codepath.android.lollipopexercise.fragment.LangAdapter;
import com.codepath.android.lollipopexercise.models.Chant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.codepath.android.lollipopexercise.activities.CategoriesActivity.TAGG;
import static com.codepath.android.lollipopexercise.activities.CategoriesListActivity.API_BASE_URL;


public class LangActivity extends AppCompatActivity {
    TextView textView;
    AsyncHttpClient client;
    LinearLayout lLayout;
    ArrayList list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang);
        // get the view pager
        ViewPager vPager= (ViewPager)findViewById(R.id.viewPage);
        //set the adapter for the pager
        vPager.setAdapter(new LangAdapter(getSupportFragmentManager(),this));
        //setup the tablayout to ise the view pager
        TabLayout tabLayout=(TabLayout)findViewById(R.id.slinding_tab);
        tabLayout.setupWithViewPager(vPager);
        // find the textView and specify the group to category
        textView = findViewById(R.id.tvCate);
        textView.setText("Grouper par :  Langue");
        // initialize the client
        client = new AsyncHttpClient();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_lang, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                rechercher(query);
                searchItem.expandActionView();
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                rechercher(newText);
                return true;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }

    public  void rechercher(String query){
        client = new AsyncHttpClient();
        lLayout = findViewById(R.id.linearLayout);
        lLayout.setVisibility(LinearLayout.GONE);
        list = new ArrayList<>();

        // initialize the adapter
        adapter = new MyAdapter(list);
        // create the url
        String url = API_BASE_URL;
        //set the request parameter
        RequestParams param = new RequestParams();
        param.put("q", query);
        // execute a GET request  expecting JSON object response
        client.get(url, param, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                list.clear();
                try {
                    // iterate through result set and create movie object
                    for(int i = 0; i < response.length(); i++){
                        Chant cat= new Chant(response.getJSONObject(i));
                        list.add(cat);
                        // notify adapter that a row was added
                        adapter.notifyItemInserted(list.size()-1);
                    }
                    Log.i(TAGG, String.format("Loaded %s titres", response.length()));
                } catch (JSONException e) {
                    logError("Failed to param list titre de chant", e, true);
                }
                Log.i(String.valueOf(this), String.format("Loaded %s resultat de chant", response.length()));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void logError(String s, JSONException e, boolean b) {
    }

    public void onClickCat(MenuItem item){
        // launch the tab favorite and recent view
        Intent i= new Intent(this, CategoriesActivity.class);
        startActivity(i);
    }
    public void onClickAbout(MenuItem item){
        // launch the tab favorite and recent view
        Intent i= new Intent(this, AboutActivity.class);
        startActivity(i);
    }
    public void onClickShare(MenuItem item){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://lamalarson.96.lt");
        startActivity(Intent.createChooser(shareIntent, "Share using"));
    }
    public void onClickFavoris(View view){
        // launch the tab favorite and recent view
        Intent i= new Intent(this, PagerActivity.class);
        startActivity(i);
    }

}
