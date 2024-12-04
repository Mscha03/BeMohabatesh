package com.example.bemohabatesh.data.repository.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

private const val TAG = "DATA_BASE_HELPER"

private const val DB_NAME = "TASK_DATABASE"
private const val DB_VERSION = 1


class DataBaseHelper(
    context: Context,
    private val tableName: String,
    private val columnsName: ArrayList<String>,
    private val columnsType: ArrayList<String>,
    private val primaryKey: Boolean = false,
    private val foreignKeyColumns: ArrayList<String> = arrayOf("", "").toCollection(ArrayList())
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    override fun onCreate(
        db: SQLiteDatabase?
    ) {

        if (columnsName.isNotEmpty() && columnsType.isNotEmpty()) {

            val createTable = "CREATE TABLE $tableName ("
            var startFrom = 0

            if (primaryKey) {
                createTable.plus("$ ${columnsName[0]} ${columnsType[0]} PRIMARY KEY AUTOINCREMENT, ")
                startFrom = 1
            }

            if (columnsName.size == columnsType.size) {
                for (i in startFrom..columnsName.size) {
                    createTable.plus("${columnsName[i]} ${columnsType[i]}, ")
                }
            } else {
                Log.d(
                    TAG,
                    "onCreate: $columnsName array size not equals with $columnsType array size"
                )
            }

            if (foreignKeyColumns.size == 3) {
                createTable.plus("FOREIGN KEY(${foreignKeyColumns[0]}) REFERENCES ${foreignKeyColumns[1]}(${foreignKeyColumns[2]})")
            } else {
                Log.d(TAG, "onCreate: $foreignKeyColumns has wrong size")
            }

            createTable.plus(" )")

            db?.execSQL(createTable)
        } else {
            Log.d(TAG, "$columnsName or $columnsType are empty")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val updateTable = "DROP TABLE IF EXISTS $tableName"
        db?.execSQL(updateTable)
        Log.d(TAG, "onUpdate: $tableName updated")
        onCreate(db)
    }


    fun insert(
        values: ContentValues
    ):Long {
        val db = this.writableDatabase
        val id =  db.insert(tableName, null, values)
        Log.d(TAG, "insert: function finished. id = $id")
        return id
    }


    fun insertAll(values: ArrayList<ContentValues>) {
        val db = this.writableDatabase

        values.forEach { v ->
            val id = db.insert(tableName, null, v)
            Log.d(TAG, "insertAll: value inserted. id = $id")
        }
        Log.d(TAG, "insertAll: function finished")

    }

    fun read(columnName: ArrayList<String>, value: ArrayList<String>): Cursor {
        val db = this.readableDatabase

        val selection = ""

        if (columnName.size == value.size) {
            for (i in 0..columnsName.size) {
                if (i != (columnsName.size - 1)) {
                    selection.plus("${columnName[i]} = ? AND ")
                } else {
                    selection.plus("${columnsName[i]} = ?")
                }
            }
            Log.d(TAG, "read: selection = $selection")
        } else {
            Log.d(TAG, "read: columnName size and value size not equals")
        }

        val cursor = db.query(
            tableName,
            null,
            selection,
            value.toTypedArray(),
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        Log.d(TAG, "read: function finished")
        return cursor
    }

    fun readAll(columnName: ArrayList<String>): Cursor {
        val db = this.readableDatabase


        val cursor = db.query(
            tableName,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        Log.d(TAG, "readAll: function finished")
        return cursor
    }

    fun update(values: ContentValues, idColumnName:String, id:Int): Int {
        val db = this.writableDatabase
        val returnValue = db.update(tableName, values, "$idColumnName = ?", arrayOf(id.toString()))
        Log.d(TAG, "update: function finished")
        return returnValue
    }

    fun delete(idColumnName:String, id:Int): Int {
        val db = this.writableDatabase
        return db.delete(tableName, "$idColumnName = ?", arrayOf(id.toString()))
    }

    fun deleteAll(idColumnName:String) {
        val db = this.writableDatabase
        db.delete(tableName, "$idColumnName = ?", null)

    }
}