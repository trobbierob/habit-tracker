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

import java.util.Random;

import es.dmoral.toasty.Toasty;

import static com.example.android.habittracker.data.HabitContract.HabitEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FAB will open Habit Category activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HabitCreator.class);
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
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_DAYS};

        Cursor cursor = db.query(TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_database_number);

        try {
            displayView.setText("Number of rows in database table: " + cursor.getCount() + "\n\n");
            displayView.append(HabitEntry._ID + "   x   "
                            + HabitEntry.COLUMN_HABIT_NAME + "   x   " + HabitEntry.COLUMN_DAYS);

            count = cursor.getCount();

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int dayColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DAYS);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentDayAmount = cursor.getInt(dayColumnIndex);

                displayView.append("\n" + currentID + "   x   "
                                    + currentName + "   x   " + currentDayAmount);
            }

        } finally {
            cursor.close();
        }
    }

    /**
     *  dummyData method provides sample habits for users
     */
    private void dummyData() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        Random rand = new Random();
        int num = rand.nextInt(12);

        switch (num) {
            case 0:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Fire someone");
                values.put(HabitEntry.COLUMN_DAYS, 1);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 1:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Stay up late to feed chickens");
                values.put(HabitEntry.COLUMN_DAYS, 2);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 2:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Make Dinner");
                values.put(HabitEntry.COLUMN_DAYS, 7);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 3:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Don't die");
                values.put(HabitEntry.COLUMN_DAYS, 7);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 4:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Take chill pill");
                values.put(HabitEntry.COLUMN_DAYS, 7);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 5:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Barter organs");
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 6:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Skip lunch");
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 7:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Read gossip column");
                values.put(HabitEntry.COLUMN_DAYS, 7);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 8:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Spend money");
                values.put(HabitEntry.COLUMN_DAYS, 4);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 9:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Ask for a hug");
                values.put(HabitEntry.COLUMN_DAYS, 1);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 10:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Take 'No' for an answer");
                values.put(HabitEntry.COLUMN_DAYS, 3);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 11:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Laugh");
                values.put(HabitEntry.COLUMN_DAYS, 7);
                db.insert(TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            default:
                Toasty.error(MainActivity.this, "No Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
        }
    }

    /**
     * delete() deletes all database entries and resets the number of items in database
     */
    private void delete() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        if (count == 0) {
            Toasty.error(MainActivity.this, "Nothing to Delete", Toast.LENGTH_SHORT).show();
        } else {
            db.delete(TABLE_NAME, null, null);
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
        }
    }

    /**
     * @param menu
     * Method inflates overflow menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * @param item
     * Method calls dummyData method to insert fake data rows
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                dummyData();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete:
                delete();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}