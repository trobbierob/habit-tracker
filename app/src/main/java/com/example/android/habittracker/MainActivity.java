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
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 1:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Stay up late to feed chickens");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 2:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Make Dinner");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 3:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Don't die");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 4:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Take chill pill");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 5:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Barter organs");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 6:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Skip lunch");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 7:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Read gossip column");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 8:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Spend money");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 9:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Ask for a hug");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 10:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Take 'No' for an answer");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            case 11:
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Laugh");
                db.insert(HabitEntry.TABLE_NAME, null, values);
                Toasty.success(MainActivity.this, "Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
            default:
                Toasty.error(MainActivity.this, "No Row Added!", Toast.LENGTH_SHORT, true).show();
                break;
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
        }
        return super.onOptionsItemSelected(item);
    }
}