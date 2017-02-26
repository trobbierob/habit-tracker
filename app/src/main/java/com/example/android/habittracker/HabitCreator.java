package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

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

    private int sunInt = 0;
    private int monInt = 0;
    private int tuesInt = 0;
    private int wedInt = 0;
    private int thursInt = 0;
    private int friInt = 0;
    private int satInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_creator);

        ButterKnife.bind(this);

        mCbSunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                sunInt = 1;
            }
        });

        mCbMonday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                monInt = 1;
            }
        });

        mCbTuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                tuesInt = 1;
            }
        });

        mCbWednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                wedInt = 1;
            }
        });

        mCbThursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                thursInt = 1;
            }
        });

        mCbFriday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                friInt = 1;
            }
        });

        mCbSaturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                satInt = 1;
            }
        });
    }

    private void insertHabit() {
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String habitName = mHabitEditText.getText().toString().trim();

        int habitDays = sunInt + monInt + tuesInt + wedInt + thursInt + friInt
                        + satInt;

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habitName);
        values.put(HabitEntry.COLUMN_DAYS, habitDays);

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
