package com.example.bemohabatesh.data.model.database.monthlyHabit

class MonthlyHabitHistoryDataBaseInformation {
    companion object{
        const val TABLE_NAME = "WEEKLY_HABIT_HISTORY"

        const val COLUMN_ID = "ID"
        const val COLUMN_DATE_ID = "DATE_ID"
        const val COLUMN_DATE_YEAR = "DATE_YEAR"
        const val COLUMN_DATE_MONTH = "DATE_MONTH"
        const val COLUMN_DATE_DAY = "DATE_DAY"
        const val COLUMN_DATE_IS_DONE = "IS_DONE"

        val TABLE_COLUMNS_NAME = arrayOf(
            COLUMN_ID,
            COLUMN_DATE_ID,
            COLUMN_DATE_YEAR,
            COLUMN_DATE_MONTH,
            COLUMN_DATE_DAY,
            COLUMN_DATE_IS_DONE
        ).toCollection(ArrayList())

        val TABLE_COLUMNS_TYPE = arrayOf(
            "INTEGER", "INTEGER", "INTEGER","INTEGER", "INTEGER", "INTEGER"
        ).toCollection(ArrayList())
    }
}