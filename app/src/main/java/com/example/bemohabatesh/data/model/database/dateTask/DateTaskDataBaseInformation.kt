package com.example.bemohabatesh.data.model.database.dateTask

class DateTaskDataBaseInformation {
    companion object{
        const val TABLE_NAME = "SIMPLE_TASK"

        const val COLUMN_ID = "ID"
        const val COLUMN_TASK_TITLE = "TASK_TITLE"
        const val COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION"
        const val COLUMN_TASK_IS_DONE = "TASK_IS_DONE"
        const val COLUMN_TASK_CREATE_DAY = "TASK_CREATE_DAY"
        const val COLUMN_TASK_CREATE_MONTH = "TASK_CREATE_MONTH"
        const val COLUMN_TASK_CREATE_YEAR = "TASK_CREATE_YEAR"

        const val COLUMN_TASK_DEADLINE_DAY = "TASK_DEADLINE_DAY"
        const val COLUMN_TASK_DEADLINE_MONTH = "TASK_DEADLINE_MONTH"
        const val COLUMN_TASK_DEADLINE_YEAR = "TASK_DEADLINE_YEAR"

        val TABLE_COLUMNS_NAME = arrayOf(
            COLUMN_ID,
            COLUMN_TASK_TITLE,
            COLUMN_TASK_DESCRIPTION,
            COLUMN_TASK_IS_DONE,
            COLUMN_TASK_CREATE_DAY,
            COLUMN_TASK_CREATE_MONTH,
            COLUMN_TASK_CREATE_YEAR,
            COLUMN_TASK_DEADLINE_DAY,
            COLUMN_TASK_DEADLINE_MONTH,
            COLUMN_TASK_DEADLINE_YEAR
        ).toCollection(ArrayList())

        val TABLE_COLUMNS_TYPE = arrayOf(
            "INTEGER", "TEXT", "TEXT", "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER"
        ).toCollection(ArrayList())
    }
}