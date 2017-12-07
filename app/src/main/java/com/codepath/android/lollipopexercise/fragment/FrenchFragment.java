package com.codepath.android.lollipopexercise.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

import static com.codepath.android.lollipopexercise.activities.CategoriesListActivity.API_BASE_URL;

/**
 * Created by FR-PHILIPPE on 11/11/2017.
 */

public class FrenchFragment extends TweetsListFragment {
    AsyncHttpClient client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the client
        client = new AsyncHttpClient();
        getConfiguration();
    }

    public void getConfiguration(){
// create the url
        String url = API_BASE_URL;
        //set the request parameter
        RequestParams param = new RequestParams();
        param.put("lang","francais");
        // execute a GET request  expecting JSON object response
        client.get(url, param, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
            addSong(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

            }
        });
    }

}
