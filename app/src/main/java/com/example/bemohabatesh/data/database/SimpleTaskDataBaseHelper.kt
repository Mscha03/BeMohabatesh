package com.example.bemohabatesh.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bemohabatesh.data.model.SimpleTask

// tag
private const val TAG = "SIMPLE_TASK_DATA_BASE_HELPER"

// database information
private const val DB_NAME = "SIMPLE_TASK_DATABASE"
private const val DB_VERSION = 1

// table name
private const val TABLE_NAME = "SIMPLE_TASK_TABLE"

// table column
private const val COLUMN_TASK_ID = "ID"
private const val COLUMN_TASK_TITLE = "TITLE"
private const val COLUMN_TASK_DESCRIPTION = "DESCRIPTION"
private const val COLUMN_TASK_IS_DONE = "IS_DONE"
// task create date
private const val COLUMN_TASK_CREATE_DAY = "CREATE_DAY"
private const val COLUMN_TASK_CREATE_MONTH = "CREATE_MONTH"
private const val COLUMN_TASK_CREATE_YEAR = "CREATE_YEAR"



class SimpleTaskDataBaseHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_TASK_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TASK_TITLE TEXT, " +
                    "$COLUMN_TASK_DESCRIPTION TEXT, " +
                    "$COLUMN_TASK_IS_DONE INTEGER, " +
                    "$COLUMN_TASK_CREATE_DAY INTEGER, " +
                    "$COLUMN_TASK_CREATE_MONTH INTEGER, " +
                    "$COLUMN_TASK_CREATE_YEAR INTEGER)"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val updateTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(updateTable)
        onCreate(db)
    }

    // CRUD methods
    private fun insertTask(task: SimpleTask){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_TASK_TITLE, task.getTitle())
        values.put(COLUMN_TASK_DESCRIPTION, task.getDescription())
        values.put(COLUMN_TASK_IS_DONE, task.getIsDone())
        values.put(COLUMN_TASK_CREATE_DAY, task.getCreatedDay().getDay())
        values.put(COLUMN_TASK_CREATE_MONTH, task.getCreatedDay().getMonth())
        values.put(COLUMN_TASK_CREATE_YEAR, task.getCreatedDay().getYear())

        db.insert(TABLE_NAME, null, values)
    }

    private fun getTask(taskId: Int): Cursor{
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            "$COLUMN_TASK_ID = ?",
            arrayOf(taskId.toString()),
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return cursor
    }

    private fun getAllTask(): Cursor{
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            "$COLUMN_TASK_ID = ?",
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return cursor
    }

    private fun updateTask(task: SimpleTask){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_TASK_TITLE, task.getTitle())
        values.put(COLUMN_TASK_DESCRIPTION, task.getDescription())
        values.put(COLUMN_TASK_IS_DONE, task.getIsDone())
        values.put(COLUMN_TASK_CREATE_DAY, task.getCreatedDay().getDay())
        values.put(COLUMN_TASK_CREATE_MONTH, task.getCreatedDay().getMonth())
        values.put(COLUMN_TASK_CREATE_YEAR, task.getCreatedDay().getYear())

        db.update(TABLE_NAME, values, "$COLUMN_TASK_ID = ? ", arrayOf(task.getId().toString()))
        db.close()
    }

    private fun deleteTask(task: SimpleTask){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_TASK_ID = ?", arrayOf(task.getId().toString()))
    }
}