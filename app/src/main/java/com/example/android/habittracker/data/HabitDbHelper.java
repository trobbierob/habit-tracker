package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Sogekingu on 2/20/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "routine.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String TEXT_TYPE_NOT_NULL = " TEXT NOT NULL";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + HabitEntry.TABLE_NAME + " (" + HabitEntry._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT"
            + COMMA_SEP + HabitEntry.COLUMN_HABIT_NAME
            + TEXT_TYPE_NOT_NULL + ")";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
