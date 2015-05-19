package com.example.wb773.simplefragment.db;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by wb773 on 15/05/18.
 */
public class DBTesting extends AndroidTestCase {

    public void testDropDB(){
        assertTrue(mContext.deleteDatabase(DBHelper.DATABASE_NAME));
    }

    public void testCreateDB(){
        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        assertTrue("message：" + db.isOpen(),!db.isOpen());
        Log.i("tag_name", "debuglog text");
    }



}
