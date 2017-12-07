package com.codepath.android.lollipopexercise.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.adapters.DBAdapter;
import com.codepath.android.lollipopexercise.adapters.MyAdapter;
import com.codepath.android.lollipopexercise.models.Chant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CategoriesListActivity extends AppCompatActivity {
    // The base url for the API
    public final static String API_BASE_URL ="http://larson.esy.es/categories/liste.php";
    // Tag of this activity
    public static final String TAG = "CategoriesListActivity";
    // instance field
    AsyncHttpClient client;
    // the list of currently playing list
    ArrayList<Chant> list;
    // the recycle view
    RecyclerView rvlist;
    String categorie;
    // the adapter wired to the recycle view
    MyAdapter adapter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorieslists);

        categorie = getIntent().getStringExtra("categorie");
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Liste des chants"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title
        textView = (TextView) findViewById(R.id.tvCat);
        textView.setText(categorie);
        // initialize the client
        client = new AsyncHttpClient();
        // initialize the list of movie
        list = new ArrayList<>();
        // initialize the adapter
        adapter = new MyAdapter(list);

        // resolve the recycle view and connect a layout manager and the adapter
        rvlist = (RecyclerView) findViewById(R.id.rvList) ;
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(adapter);

        // get configuration on app creation
        getConfiguration();
       // retrieve();
    }

    //RETREIEV
    private void retrieve() {
        list.clear();

        DBAdapter db=new DBAdapter(this);
        db.openDB();

        //RETRIEVE
        Cursor c = db.getSongs(categorie);

        //LOOP AND ADD TO ARRAYLIST
        while (c.moveToNext()) {
            long id = c.getLong(0);
            String titre = c.getString(1);
            String refrain = c.getString(2);
            String couplet = c.getString(3);
            String categorie = c.getString(4);
            String langue = c.getString(5);
            String description = c.getString(6);
           Chant chant = new Chant(id, titre, refrain, couplet, categorie, langue, description);
            list.add(chant);
        }
        //CHECK IF ARRAYLIST ISNT EMPTY
        if(!(list.size()<1))
        {
            rvlist.setAdapter(adapter);
        }

        db.closeDB();;
    }

    public void getConfiguration(){
// create the url
        String url = API_BASE_URL;
        //set the request parameter
        RequestParams param = new RequestParams();
        param.put("categorie",categorie);
        // execute a GET request  expecting JSON object response
        client.get(url, param, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    // iterate through result set and create movie object
                    for(int i = 0; i < response.length(); i++){
                        Chant cat= new Chant(response.getJSONObject(i));
                        list.add(cat);
                        // notify adapter that a row was added
                        adapter.notifyItemInserted(list.size()-1);
                    }
                    Log.i(TAG, String.format("Loaded %s titres", response.length()));
                } catch (JSONException e) {
                    logError("Failed to param list titre de chant", e, true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                retrieve();
            }
        });
    }

    private void logError(String s, JSONException e, boolean b) {    }
}
