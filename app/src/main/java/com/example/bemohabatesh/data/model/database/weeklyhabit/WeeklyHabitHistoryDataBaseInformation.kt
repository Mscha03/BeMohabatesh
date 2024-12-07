package com.example.bemohabatesh.data.model.database.weeklyhabit

class WeeklyHabitHistoryDataBaseInformation {
    companion object{
        const val TABLE_NAME = "WEEKLY_HABIT_HISTORY"

        const val COLUMN_ID = "ID"
        const val COLUMN_DATE_ID = "WEEK_ID"
        const val COLUMN_DATE_YEAR = "WEEK_YEAR"
        const val COLUMN_DATE_MONTH = "WEEK_MONTH"
        const val COLUMN_DATE_WEEK = "WEEK_WEEK"
        const val COLUMN_DATE_DAY = "WEEK_DAY"
        const val COLUMN_DATE_IS_DONE = "IS_DONE"

        val TABLE_COLUMNS_NAME = arrayOf(
            COLUMN_ID,
            COLUMN_DATE_ID,
            COLUMN_DATE_YEAR,
            COLUMN_DATE_MONTH,
            COLUMN_DATE_WEEK,
            COLUMN_DATE_DAY,
            COLUMN_DATE_IS_DONE
        ).toCollection(ArrayList())

        val TABLE_COLUMNS_TYPE = arrayOf(
            "INTEGER", "INTEGER", "INTEGER", "INTEGER","INTEGER", "INTEGER", "INTEGER"
        ).toCollection(ArrayList())
    }
}