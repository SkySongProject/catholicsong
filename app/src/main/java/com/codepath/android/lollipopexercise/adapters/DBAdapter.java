package com.codepath.android.lollipopexercise.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Hp on 3/18/2016.
 */
public class DBAdapter {
    Context c;

    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper=new DBHelper(c);
    }

    //OPEN DATABASE
    public DBAdapter openDB()
    {
        try {
            db=helper.getWritableDatabase();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    //CLOSE DATABASE
    public void closeDB()
    {
        try {
          helper.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    //INSERT
    public long add(com.codepath.android.lollipopexercise.models.Chant chant)
    {
        try
        {
            ContentValues value=new ContentValues();
            value.put(Constants.TITLE, chant.getTitle());
            value.put(Constants.REFRAIN, chant.getRefrain());
            value.put(Constants.BODY, chant.getCouplet());
            value.put(Constants.CAT, chant.getCategorie());
            value.put(Constants.LANG, chant.getLangue());

            return db.insert(Constants.TB_NAME,Constants.ID,value);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    //RETRIEVE
    public Cursor getAllSongs()
    {
        String[] columns={Constants.ID,Constants.TITLE,Constants.REFRAIN,Constants.BODY,Constants.CAT,Constants.LANG,Constants.DESC};

        return db.query(Constants.TB_NAME,columns,null,null,null,null,null);

    }
    public Cursor getSongs(String cat)
    {
        String[] categorie = {cat};
        String selection = "categorie";
        String[] columns={Constants.ID,Constants.TITLE,Constants.REFRAIN,Constants.BODY,Constants.CAT,Constants.LANG,Constants.DESC};

        return db.query(Constants.TB_NAME,columns,selection,categorie,null,null,null);
    }
}














