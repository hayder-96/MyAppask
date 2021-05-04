package com.newasks.myappask;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newasks.myappask.Model.Item;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class Databasesport extends SQLiteAssetHelper {

    private SQLiteDatabase sql;
    public static final String databas = "sports.db";
    public static final String table = "sport";
    public static final String uid = "id";
    public static final String ASk = "Ask";
    public static final String sELECT1 = "Selection1";
    public static final String sELECT2 = "Selection2";
    public static final String sELECT3 = "Selection3";
    public static final String sELECT4 = "Selection4";
    public static final String aNSWER = "Answer";


    public Databasesport(Context context) {
        super(context, databas, null, 1);
    }

    public ArrayList<Item> GetDatasport() {

        ArrayList<Item> arrayList = new ArrayList<>();

        sql = getReadableDatabase();
        Cursor cursor = sql.rawQuery("SELECT*FROM " + table, null);
        if (cursor.moveToFirst() && cursor != null) {

            do {
                int n = cursor.getInt(cursor.getColumnIndex(uid));
                String m = cursor.getString(cursor.getColumnIndex(ASk));
                String k = cursor.getString(cursor.getColumnIndex(sELECT1));
                String l = cursor.getString(cursor.getColumnIndex(sELECT2));
                String o = cursor.getString(cursor.getColumnIndex(sELECT3));
                String t = cursor.getString(cursor.getColumnIndex(sELECT4));
                String r = cursor.getString(cursor.getColumnIndex(aNSWER));


                arrayList.add(new Item(n, m, k, l, o, t, r));

            } while (cursor.moveToNext());
            cursor.close();

            return arrayList;
        }
        return arrayList;

    }


}

