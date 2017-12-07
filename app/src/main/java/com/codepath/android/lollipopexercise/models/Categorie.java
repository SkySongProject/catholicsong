package com.codepath.android.lollipopexercise.models;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.codepath.android.lollipopexercise.R;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


// annotation indicates class is Parcelable
@Parcel // Container class to hold Contact information.
public class Categorie  {
    public String mName;
    public int mThumbnailDrawable;
    public String mNumber;


    public Categorie(){}

    public Categorie(String name, int thumbnailDrawable, String number) {
        mName = name;
        mThumbnailDrawable = thumbnailDrawable;
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public int getThumbnailDrawable() {
        return mThumbnailDrawable;
    }

    public String getNumber() {
        return mNumber;
    }

    // Returns a list of chants
    public static List<Categorie> getCategories() {
        List<Categorie> chants = new ArrayList<>();
        chants.add(new Categorie("Entrée", R.drawable.enter, "4153508881"));
        chants.add(new Categorie("Kyrié", R.drawable.kyrieeleison, "4153508882"));
        chants.add(new Categorie("Gloria", R.drawable.gloria, "4153508883"));
        chants.add(new Categorie("Acclamation", R.drawable.evangile, "4153508884"));
        chants.add(new Categorie("Credo", R.drawable.croos, "4153508885"));
        chants.add(new Categorie("Prière Universelle", R.drawable.universel, "4153508886"));
        chants.add(new Categorie("Offertoire", R.drawable.communion, "4153508887"));
        chants.add(new Categorie("Sanctus", R.drawable.sanctus, "4153508888"));
        chants.add(new Categorie("Anamnèse", R.drawable.anamnese, "4153508889"));
        chants.add(new Categorie("Baiser de paix", R.drawable.paix, "4153508890"));
        chants.add(new Categorie("Agneau de Dieu", R.drawable.agneau, "4153508887"));
        chants.add(new Categorie("Communion", R.drawable.communion, "4153508888"));
        chants.add(new Categorie("Action de gràce", R.drawable.obseques, "4153508889"));
        chants.add(new Categorie("Sortie", R.drawable.sortie, "4153508890"));
        return chants;
    }

    // Returns a random chant
    public static Categorie getRandomChant(Context context) {

        Resources resources = context.getResources();

        TypedArray chantNames = resources.obtainTypedArray(R.array.cat_names);
        int name = (int) (Math.random() * chantNames.length());

        TypedArray chantThumbnails = resources.obtainTypedArray(R.array.cat_thumbnails);
        int thumbnail = (int) (Math.random() * chantThumbnails.length());

        TypedArray chantNumbers = resources.obtainTypedArray(R.array.cat_numbers);
        int number = (int) (Math.random() * chantNumbers.length());

        return new Categorie(chantNames.getString(name), chantThumbnails.getResourceId(thumbnail, R.drawable.bible), chantNumbers.getString(number));
    }


}
