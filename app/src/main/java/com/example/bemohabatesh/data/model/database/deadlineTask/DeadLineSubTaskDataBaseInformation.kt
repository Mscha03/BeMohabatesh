package com.example.bemohabatesh.data.model.database.deadlineTask

class DeadLineSubTaskDataBaseInformation {
    companion object{
        const val TABLE_NAME = "DEADLINE_SUBTASK"

        const val COLUMN_ID = "ID"
        const val COLUMN_TASK_ID = "TASK_ID"
        const val COLUMN_TASK_TITLE = "TASK_TITLE"
        const val COLUMN_TASK_IS_DONE = "TASK_IS_DONE"


        val TABLE_COLUMNS_NAME = arrayOf(
            COLUMN_ID,
            COLUMN_TASK_ID,
            COLUMN_TASK_TITLE,
            COLUMN_TASK_IS_DONE,
        ).toCollection(ArrayList())

        val TABLE_COLUMNS_TYPE = arrayOf(
            "INTEGER","INTEGER", "TEXT", "INTEGER"
        ).toCollection(ArrayList())
    }
}