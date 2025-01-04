package com.example.bemohabatesh.data.repository.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bemohabatesh.data.model.database.simpletask.SimpleTaskDataBaseInformation
import com.example.bemohabatesh.data.model.database.simpletask.SimpleTaskReminderDataBaseInformation
import com.example.bemohabatesh.data.model.tasks.SimpleTask
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.repository.interfacess.EditableTask
import com.example.bemohabatesh.data.repository.interfacess.RemindingTask
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar


class SimpleTaskDataBaseHelper(context: Context) : EditableTask, RemindingTask {

    private val taskDb = DataBaseHelper(
        context = context,
        tableName = SimpleTaskDataBaseInformation.TABLE_NAME,
        columnsName = SimpleTaskDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = SimpleTaskDataBaseInformation.TABLE_COLUMNS_TYPE
    )

    private val remindDb = DataBaseHelper(
        context = context,
        tableName = SimpleTaskReminderDataBaseInformation.TABLE_NAME,
        columnsName = SimpleTaskReminderDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = SimpleTaskReminderDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            SimpleTaskReminderDataBaseInformation.COLUMN_TASK_ID,
            SimpleTaskDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )


    override fun insertTask(task: Task): Long {
        val simpleTask = task as SimpleTask
        val values = ContentValues()

        values.put(SimpleTaskDataBaseInformation.COLUMN_TASK_TITLE, simpleTask.title)
        values.put(SimpleTaskDataBaseInformation.COLUMN_TASK_DESCRIPTION, simpleTask.description)
        values.put(SimpleTaskDataBaseInformation.COLUMN_TASK_IS_DONE, simpleTask.isDone)
        values.put(
            SimpleTaskDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            simpleTask.createdAt.day
        )
        values.put(
            SimpleTaskDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            simpleTask.createdAt.month
        )
        values.put(
            SimpleTaskDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            simpleTask.createdAt.year
        )

        return taskDb.insert(values)
    }

    override fun readTask(taskId: Int): Cursor {
        return taskDb.read(
            arrayOf(SimpleTaskDataBaseInformation.COLUMN_ID).toCollection(ArrayList()),
            arrayOf(taskId.toString()).toCollection(ArrayList())
        )
    }

    override fun readAllTask(): Cursor {
        return taskDb.readAll(SimpleTaskDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTask(task: Task): Int {
        val simpleTask = task as SimpleTask
        val values = ContentValues()

        values.put(SimpleTaskDataBaseInformation.COLUMN_TASK_TITLE, simpleTask.title)
        values.put(SimpleTaskDataBaseInformation.COLUMN_TASK_DESCRIPTION, simpleTask.description)
        values.put(SimpleTaskDataBaseInformation.COLUMN_TASK_IS_DONE, simpleTask.isDone)
        values.put(
            SimpleTaskDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            simpleTask.createdAt.day
        )
        values.put(
            SimpleTaskDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            simpleTask.createdAt.month
        )
        values.put(
            SimpleTaskDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            simpleTask.createdAt.year
        )

        return taskDb.update(values, SimpleTaskDataBaseInformation.COLUMN_ID, simpleTask.id)
    }

    override fun deleteTask(taskId: Int): Int {
        return taskDb.delete(SimpleTaskDataBaseInformation.COLUMN_ID, taskId)
    }

    override fun insertReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
        remindDb.insert(values)
    }

    override fun readReminder(mainTaskId: Int): Cursor {
        return remindDb.read(
            arrayOf(SimpleTaskReminderDataBaseInformation.COLUMN_TASK_ID).toCollection(ArrayList()),
            arrayOf(mainTaskId.toString()).toCollection(ArrayList())
        )
    }

    override fun updateReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(SimpleTaskReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
        remindDb.update(
            values,
            SimpleTaskReminderDataBaseInformation.COLUMN_TASK_ID,
            mainTaskId
        )
    }

    override fun deleteReminder(mainTaskId: Int) {
        remindDb.delete(SimpleTaskReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
    }

}