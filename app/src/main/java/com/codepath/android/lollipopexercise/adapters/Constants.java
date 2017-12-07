package com.codepath.android.lollipopexercise.adapters;

/**
 * Created by Hp on 3/18/2016.
 */
public class Constants {
    //COLUMNS
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String REFRAIN = "refrain";
    public static final String BODY = "body";
    public static final String CAT = "categorie";
    public static final String LANG = "lang";
    public static final String DESC = "description";

    //DB PROPERTIES
    static final String DB_NAME="d_DB";
    static final String TB_NAME="d_TB";
    static final int DB_VERSION='1';

    static final String CREATE_TB = "CREATE TABLE d_TB("
            + ID + "INTEGER PRIMARY KEY ,"
            + TITLE + "TEXT,"
            + REFRAIN + "TEXT,"
            + BODY + "TEXT,"
            + LANG + "TEXT,"
            + CAT + "TEXT,"
            + DESC + "TEXT);";
}
