package com.example.wb773.simplefragment.DBAdapter;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by wb773 on 15/05/06.
 */
public class TodoListDBAdapter {

    static final String DATABASE_NAME = "mynote.db";
    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "todolist";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_CREATEDATE = "createpdate";
    public static final String COL_LASTUPDATE = "lastupdate";

    protected final Context context;
    protected DatabaseHelper dbHelper;
    protected SQLiteDatabase db;

    public TodoListDBAdapter(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, TABLE_NAME, null, DATABASE_VERSION);
        }

        /**
         * DB作成
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTO INCREMENT," +
                    COL_TITLE + " TEXT NOT NULL," +
                    COL_DESCRIPTION + " TEXT NOT NULL," +
                    COL_CREATEDATE + " TEXT NOT NULL," +
                    COL_LASTUPDATE + " TEXT NOT NULL);"
            );
        }

        /**
         * DB更新
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    //
    //  Adapterメソッド
    //

    public TodoListDBAdapter open(){
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
        db = null;
    }


    //
    //  APPメソッド
    //

    //ーーーーー削除ーーーーー
    public boolean deleteAllTodo(){
        return db.delete(TABLE_NAME,null,null)>0;
    }

    public boolean deleteTodo(int id){
        return db.delete(TABLE_NAME,COL_ID + "=" + id, null) > 0;
    }


    //ーーーーー取得ーーーーー
    public Cursor getAllTodo(){
        return db.query(TABLE_NAME,null,null,null,null,null,null);
    }


    //ーーーーー登録ーーーーー
    public void saveTodo(String title,String description){
        Date dateNow = new Date();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE,title);
        values.put(COL_DESCRIPTION,description);
        values.put(COL_CREATEDATE,dateNow.toString());
        db.insertOrThrow(TABLE_NAME,null,values);
    }

}
