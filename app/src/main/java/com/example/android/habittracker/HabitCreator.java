package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract;
import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class HabitCreator extends AppCompatActivity {

    @BindView(R.id.habit_edit_text) EditText mHabitEditText;
    @BindView(R.id.cb_sunday) CheckBox mCbSunday;
    @BindView(R.id.cb_monday) CheckBox mCbMonday;
    @BindView(R.id.cb_tuesday) CheckBox mCbTuesday;
    @BindView(R.id.cb_wednesday) CheckBox mCbWednesday;
    @BindView(R.id.cb_thursday) CheckBox mCbThursday;
    @BindView(R.id.cb_friday) CheckBox mCbFriday;
    @BindView(R.id.cb_saturday) CheckBox mCbSaturday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_creator);

        ButterKnife.bind(this);
    }

    private void insertHabit() {
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String habitName = mHabitEditText.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, habitName);

        long newRowID = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowID == -1) {
            Toasty.error(this, "Habit Not Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toasty.success(HabitCreator.this, "SAVED!", Toast.LENGTH_SHORT, true).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if (menuItemSelected == R.id.action_bar_save) {
            insertHabit();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}