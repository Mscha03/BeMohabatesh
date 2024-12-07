package com.example.bemohabatesh.data.model.database.monthlyHabit

class MonthlyHabitDataBaseInformation {
    companion object{
        const val TABLE_NAME = "MONTHLY_HABIT"

        const val COLUMN_ID = "ID"
        const val COLUMN_TASK_TITLE = "TASK_TITLE"
        const val COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION"
        const val COLUMN_TASK_CREATE_DAY = "TASK_CREATE_DAY"
        const val COLUMN_TASK_CREATE_MONTH = "TASK_CREATE_MONTH"
        const val COLUMN_TASK_CREATE_YEAR = "TASK_CREATE_YEAR"

        val TABLE_COLUMNS_NAME = arrayOf(
            COLUMN_ID,
            COLUMN_TASK_TITLE,
            COLUMN_TASK_DESCRIPTION,
            COLUMN_TASK_CREATE_DAY,
            COLUMN_TASK_CREATE_MONTH,
            COLUMN_TASK_CREATE_YEAR
        ).toCollection(ArrayList())

        val TABLE_COLUMNS_TYPE = arrayOf(
            "INTEGER", "TEXT", "TEXT", "INTEGER", "INTEGER", "INTEGER"
        ).toCollection(ArrayList())
    }
}