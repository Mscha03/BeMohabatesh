package com.example.bemohabatesh.data.model.database.monthlyHabit

class MonthlyHabitReminderDataBaseInformation {
    companion object{
        const val TABLE_NAME = "MONTHLY_HABIT_REMINDER"

        const val COLUMN_ID = "ID"
        const val COLUMN_TASK_ID = "TASK_ID"
        const val COLUMN_REMIND_YEAR = "REMIND_YEAR"
        const val COLUMN_REMIND_MONTH = "REMIND_MONTH"
        const val COLUMN_REMIND_DAY = "REMIND_DAY"
        const val COLUMN_REMIND_HOUR = "REMIND_HOUR"
        const val COLUMN_REMIND_MINUTE = "REMIND_MINUTE"

        val TABLE_COLUMNS_NAME = arrayOf(
            COLUMN_ID,
            COLUMN_TASK_ID,
            COLUMN_REMIND_YEAR,
            COLUMN_REMIND_MONTH,
            COLUMN_REMIND_DAY,
            COLUMN_REMIND_HOUR,
            COLUMN_REMIND_MINUTE
        ).toCollection(ArrayList())

        val TABLE_COLUMNS_TYPE = arrayOf(
            "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER",
        ).toCollection(ArrayList())
    }
}