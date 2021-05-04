package com.newasks.myappask;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newasks.myappask.Model.Item;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class Databasesciences extends SQLiteAssetHelper {


    private SQLiteDatabase sql;
    public static final String database = "sciences.db";

    public static final String Table = "science";
    public static final String uidi = "id";
    public static final String ASK = "ask";
    public static final String SELECT1 = "Selection1";
    public static final String SELECT2 = "Selection2";
    public static final String SELECT3 = "Selection3";
    public static final String SELECT4 = "Selection4";
    public static final String ANSWER = "Answer";


    public Databasesciences(Context context) {
        super(context, database, null, 1);

    }

    public ArrayList<Item> GetDataSciences() {

        ArrayList<Item> arrayList = new ArrayList<>();

        sql = getReadableDatabase();

        Cursor cursor = sql.rawQuery("SELECT*FROM " + Table, null);
        if (cursor.moveToFirst() && cursor != null) {

            do {
                int n = cursor.getInt(cursor.getColumnIndex(uidi));
                String m = cursor.getString(cursor.getColumnIndex(ASK));
                String k = cursor.getString(cursor.getColumnIndex(SELECT1));
                String l = cursor.getString(cursor.getColumnIndex(SELECT2));
                String o = cursor.getString(cursor.getColumnIndex(SELECT3));
                String t = cursor.getString(cursor.getColumnIndex(SELECT4));
                String r = cursor.getString(cursor.getColumnIndex(ANSWER));


                arrayList.add(new Item(n, m, k, l, o, t, r));

            } while (cursor.moveToNext());
            cursor.close();

            return arrayList;
        }
        return arrayList;

    }


}



