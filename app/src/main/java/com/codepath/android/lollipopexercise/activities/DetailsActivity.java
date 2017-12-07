package com.codepath.android.lollipopexercise.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.models.Chant;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {

    Chant list;
    TextView tvTitle;
    TextView tvRefrain;
    TextView tvBody;
    String shareText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

       // categorie = getIntent().getStringExtra("categorie");
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Paroles de chant"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title

        tvTitle = findViewById(R.id.tvTitle);
        tvRefrain = findViewById(R.id.tvRefrain);
        tvBody = findViewById(R.id.tvBody);

        // unwrap the movie passed in via intent, using its simple name as a key
        list = (Chant) Parcels.unwrap(getIntent().getParcelableExtra(Chant.class.getSimpleName()));

        // set the title and overview
        tvTitle.setText(fromHtml(fromHtml(list.getTitle()).toString()));
        tvRefrain.setText(fromHtml(fromHtml(list.getRefrain()).toString()));
        tvBody.setText(fromHtml(fromHtml(list.getCouplet()).toString()));
        shareText = tvTitle.getText().toString() + tvRefrain.getText().toString() + tvBody.getText().toString();
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if(Build.VERSION.SDK_INT >= 24){
            result = Html.fromHtml(html, Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH);
        } else{
            result = Html.fromHtml(html);
        }
        return result;
    }
    public void onClickShareSong(View view){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(sharingIntent, "Partager avec "));
    }
}
