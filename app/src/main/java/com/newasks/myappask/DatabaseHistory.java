package com.newasks.myappask;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.newasks.myappask.Model.Item;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHistory extends SQLiteAssetHelper {


    private SQLiteDatabase sql;
    public static final String database = "history.db";

    public static final String TABLE = "dates";
    public static final String UID = "id";
    public static final String AsK = "ask";
    public static final String SELECt1 = "Selection1";
    public static final String SELECt2 = "Selection2";
    public static final String SELECt3 = "Selection3";
    public static final String SELECt4 = "Selection4";
    public static final String ANSWeR = "Answer";


    public DatabaseHistory(Context context) {
        super(context, database, null, 1);

    }

    public ArrayList<Item> GetDatahistory() {

        ArrayList<Item> arrayList = new ArrayList<>();

        sql = getReadableDatabase();
        Cursor cursor = sql.rawQuery("SELECT*FROM " + TABLE, null);


        if (cursor.moveToFirst() && cursor != null) {

            do {
                int n = cursor.getInt(cursor.getColumnIndex(UID));
                String m = cursor.getString(cursor.getColumnIndex(AsK));
                String k = cursor.getString(cursor.getColumnIndex(SELECt1));
                String l = cursor.getString(cursor.getColumnIndex(SELECt2));
                String o = cursor.getString(cursor.getColumnIndex(SELECt3));
                String t = cursor.getString(cursor.getColumnIndex(SELECt4));
                String r = cursor.getString(cursor.getColumnIndex(ANSWeR));


                arrayList.add(new Item(n, m, k, l, o, t, r));

            } while (cursor.moveToNext());

            cursor.close();
            return arrayList;
        }
        return arrayList;

    }


}
