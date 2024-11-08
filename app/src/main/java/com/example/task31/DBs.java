package com.example.task31;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBs extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;

    public DBs(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS classmates ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "FIO TEXT, "
                + "added_time DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ");");

        db.execSQL("DELETE FROM classmates;");


        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        for (int i = 1; i <= 5; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("FIO", "Одногруппник " + i);
            db.insert("classmates", null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS classmates;");
        onCreate(db);
    }
}


