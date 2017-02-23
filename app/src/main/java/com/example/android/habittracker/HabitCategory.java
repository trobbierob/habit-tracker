package com.example.android.habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class HabitCategory extends AppCompatActivity {

    @BindView(R.id.habit_edit_text) EditText habitEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_category);
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
            Toasty.success(HabitCategory.this, "SAVED!", Toast.LENGTH_SHORT, true).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
