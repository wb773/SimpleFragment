package com.example.wb773.simplefragment.db;

import android.provider.BaseColumns;

/**
 * Created by wb773 on 15/05/18.
 */
public class DBConnect {
    public static final class Student implements BaseColumns{
        public static final String TABLE_NAME = "students";

        public static final String COLUMN_NAME = "student_name";
        public static final String COLUMN_AGE = "student_age";
        public static final String COLUMN_ROLL_NO = "student_roll_no";
    }
}
