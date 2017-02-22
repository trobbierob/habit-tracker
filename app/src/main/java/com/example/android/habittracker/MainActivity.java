package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FAB will open Habit Category activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HabitCategory.class);
                startActivity(intent);
            }
        });

        mDbHelper = new HabitDbHelper(this);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection ={
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME};

        Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_database_number);

        try {
            displayView.setText("Number of rows in database table: " + cursor.getCount() + "\n\n");
            displayView.append(HabitEntry._ID + "x"
                            + HabitEntry.COLUMN_HABIT_NAME);

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);

                displayView.append("\n" + currentID + " x "
                                    + currentName);

            }

        } finally {
            cursor.close();
        }
    }

    private void dummyData() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Feed Turtles");

        db.insert(HabitEntry.TABLE_NAME, null, values);

        Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                dummyData();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}