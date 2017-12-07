package com.codepath.android.lollipopexercise.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.activities.DetailsActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Hp on 3/18/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context c;
    ArrayList<com.codepath.android.lollipopexercise.models.Chant> chants;

    public MyAdapter( ArrayList<com.codepath.android.lollipopexercise.models.Chant> chants) { this.chants = chants;  }

    @Override
    public int getItemCount() {
        return chants.size();
    }

    //BIND VIEW TO DATA
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // get the list data at the specified position
        com.codepath.android.lollipopexercise.models.Chant list = chants.get(position);
        // populate the view with list data
        holder.tvTitle.setText(fromHtml(fromHtml(list.getTitle()).toString()));
        holder.tvId.setText("#"+list.getId().toString()+" - ");
    }
    //INITIALIZE VIEWHODER
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // get the context and create the inflater
        c = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(c);
        // create the view using the item_list layout
        View v = inflater.inflate(R.layout.item_list, parent, false);

        //HOLDER
        ViewHolder holder=new ViewHolder(v);

        return holder;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle,tvId;


        public ViewHolder(View itemView) {
            super(itemView);


            tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
            tvId= (TextView) itemView.findViewById(R.id.tvId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the list at the position, this won't work if the class is static
                com.codepath.android.lollipopexercise.models.Chant list = chants.get(position);
                // create intent for the new activity
                Intent intent = new Intent(c, DetailsActivity.class);
                // serialize the list using parceler, use its short name as a key
                intent.putExtra(com.codepath.android.lollipopexercise.models.Chant.class.getSimpleName(), Parcels.wrap(list));
                // show the activity
                c.startActivity(intent);
            }
        }


    }


    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if(Build.VERSION.SDK_INT >= 24){
            result = Html.fromHtml(html, Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV);
        } else{
            result = Html.fromHtml(html);
        }
        return result;
    }
}
