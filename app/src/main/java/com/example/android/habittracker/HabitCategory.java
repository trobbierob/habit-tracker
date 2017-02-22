package com.example.android.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

public class HabitCategory extends AppCompatActivity {

    @BindView(R.id.coding) TextView codingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_category);

        TextView codingTextView = (TextView) findViewById(R.id.coding);
    }
}
