package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Sogekingu on 2/20/17.
 */

public final class HabitContract {

    private HabitContract() {}

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "task";

        public static final String COLUMN_THING_1 = "thing_1";
        public static final String COLUMN_THING_2 = "thing_2";
        public static final String COLUMN_THING_3 = "thing_3";
    }
}
