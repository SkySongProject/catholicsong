package com.codepath.android.lollipopexercise.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.activities.CategoriesListActivity;
import com.codepath.android.lollipopexercise.models.Categorie;

import org.parceler.Parcels;

import java.util.List;

// Provide the underlying view for an individual list item.
public class CategoriesAdapter extends  RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{
    private Activity mContext;
    private List<Categorie> mChants;
    Context context;
    CategoriesAdapter adapter;

    public CategoriesAdapter(Activity context, List<Categorie> categories) {
        mContext = context;
        if (categories == null) {
            throw new IllegalArgumentException("categories must not be null");
        }
        mChants = categories;
    }

    // Inflate the view based on the viewType provided.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // get the context and create the inflater
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create the view using the item_list layout
        View listView = inflater.inflate(R.layout.item_categories, parent, false);
        // return a new viewHolder
        return new ViewHolder(listView);
    }

    // Display data at the specified position
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Categorie categorie = mChants.get(position);
        holder.rootView.setTag(categorie);
        holder.tvName.setText(categorie.getName());
        Glide.with(mContext).load(categorie.getThumbnailDrawable()).centerCrop().into(holder.ivProfile);
    }

    @Override
    public int getItemCount() {
        return mChants.size();
    }

    // Provide a reference to the views for each categorie item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final View rootView;
        final ImageView ivProfile;
        final TextView tvName;
        final View vPalette;

        public ViewHolder( View itemView) {
            super(itemView);
            rootView = itemView;
            ivProfile = (ImageView)itemView.findViewById(R.id.ivProfile);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            vPalette = itemView.findViewById(R.id.vPalette);
            // add this as the itemView's OnClickListener
            itemView.setOnClickListener(this);
        }

            @Override
            public void onClick(View itemView) {
                // gets item position
                int position = getAdapterPosition();
                // make sure the position is valid, i.e. actually exists in the view
                if (position != RecyclerView.NO_POSITION) {
                    // get the categorie at the position, this won't work if the class is static
                    Categorie categorie = mChants.get(position);
                    // create intent for the new activity
                    Intent intent = new Intent(context, CategoriesListActivity.class);
                    // serialize the categorie using parceler, use its short name as a key
                    intent.putExtra(Categorie.class.getSimpleName(), Parcels.wrap(categorie));
                    intent.putExtra("categorie", tvName.getText());
                    // show the activity
                    context.startActivity(intent);
                }
            }
        }

}
