package com.example.wb773.simplefragment.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wb773 on 15/05/18.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "school.db";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        // 任意のデータベースファイル名と、バージョンを指定する
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_FOR_STUDENT_TABLE = "CREATE TABLE " + DBConnect.Student.TABLE_NAME + "("+
                DBConnect.Student._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DBConnect.Student.COLUMN_NAME + " TEXT NOT NULL," +
                DBConnect.Student.COLUMN_AGE + " INTEGER NOT NULL," +
                DBConnect.Student.COLUMN_ROLL_NO + " INTEGER NOT NULL);";

        Log.d("DBHelper",SQL_FOR_STUDENT_TABLE);

        db.execSQL(SQL_FOR_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConnect.Student.TABLE_NAME);
    }
}
