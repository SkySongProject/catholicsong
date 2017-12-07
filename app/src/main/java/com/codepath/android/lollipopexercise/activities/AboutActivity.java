package com.codepath.android.lollipopexercise.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.codepath.android.lollipopexercise.R;

public class AboutActivity extends AppCompatActivity {
    TextView tAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tAbout = findViewById(R.id.tvAbout);
        String body = "<p style=\"text-align:center\"><span style=\"font-family:Comic Sans MS,cursive\"><strong><span style=\"font-size:14px\"> CATHOLIC SONG</span></strong></span></p> " +
                " " +
                "<p style=\"text-align:center\">CatholicSong est une application qui combinent une multitude de chants catholiques.</p> " +
                " " +
                "<p style=\"text-align:center\">Plus precis&eacute; ment toute les differentes parties de la messe.</p> " +

                "<p style=\"text-align:center\">Il y a des chants dans plusieurs langue.</p> " +
                " " +
                "<p style=\"text-align:center\">Cette application est une tierce partie d&#39;un projet d&#39;application plus elargi.</p> " +
                " " +
                "<p style=\"text-align:center\">Vous avez une quantit&eacute; illimit&eacute;e de chants a votre disposition pour vous aider a glorifier le seigneur.</p> " +
                " " +
                "<p style=\"text-align:center\">Contact :&nbsp;<a href=\"http://facebook.com/lamalarson\">http://facebook.com/lamalarson</a>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;website:&nbsp;<a href=\"http://catholicsong.org\">http://catholicsong.org</a></p> ";
        tAbout.setText(fromHtml(fromHtml(body).toString()));
    }
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if(Build.VERSION.SDK_INT >= 24){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else{
            result = Html.fromHtml(html);
        }
        return result;
    }
}
