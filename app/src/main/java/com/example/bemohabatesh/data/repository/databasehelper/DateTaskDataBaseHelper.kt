package com.example.bemohabatesh.data.repository.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bemohabatesh.data.model.database.dateTask.DateTaskDataBaseInformation
import com.example.bemohabatesh.data.model.database.dateTask.DateTaskReminderDataBaseInformation
import com.example.bemohabatesh.data.model.tasks.DateTask
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.repository.interfacess.EditableTask
import com.example.bemohabatesh.data.repository.interfacess.RemindingTask
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

class DateTaskDataBaseHelper(context: Context): EditableTask, RemindingTask {

    private val taskDb = DataBaseHelper(
        context = context,
        tableName = DateTaskDataBaseInformation.TABLE_NAME,
        columnsName = DateTaskDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DateTaskDataBaseInformation.TABLE_COLUMNS_TYPE
    )

    private val remindDb = DataBaseHelper(
        context = context,
        tableName = DateTaskReminderDataBaseInformation.TABLE_NAME,
        columnsName = DateTaskReminderDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DateTaskReminderDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            DateTaskReminderDataBaseInformation.COLUMN_TASK_ID,
            DateTaskDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )


    override fun insertTask(task: Task): Long {
        val dateTask = task as DateTask
        val values = ContentValues()

        values.put(DateTaskDataBaseInformation.COLUMN_TASK_TITLE, dateTask.title)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DESCRIPTION, dateTask.description)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_IS_DONE, dateTask.isDone)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_CREATE_DAY, dateTask.createdAt.day)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_CREATE_MONTH, dateTask.createdAt.month)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_CREATE_YEAR, dateTask.createdAt.year)

        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DEADLINE_DAY, dateTask.deadline.day)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DEADLINE_MONTH, dateTask.deadline.month)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DEADLINE_YEAR, dateTask.deadline.year)

        return taskDb.insert(values)
    }

    override fun readTask(taskId: Int): Cursor {
        return taskDb.read(
            arrayOf(DateTaskDataBaseInformation.COLUMN_ID).toCollection(ArrayList()),
            arrayOf(taskId.toString()).toCollection(ArrayList())
        )
    }

    override fun readAllTask(): Cursor {
        return taskDb.readAll(DateTaskDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTask(task: Task): Int {
        val dateTask = task as DateTask
        val values = ContentValues()

        values.put(DateTaskDataBaseInformation.COLUMN_TASK_TITLE, dateTask.title)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DESCRIPTION, dateTask.description)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_IS_DONE, dateTask.isDone)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_CREATE_DAY, dateTask.createdAt.day)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_CREATE_MONTH, dateTask.createdAt.month)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_CREATE_YEAR, dateTask.createdAt.year)

        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DEADLINE_DAY, dateTask.deadline.day)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DEADLINE_MONTH, dateTask.deadline.month)
        values.put(DateTaskDataBaseInformation.COLUMN_TASK_DEADLINE_YEAR, dateTask.deadline.year)

        return taskDb.update(values, DateTaskDataBaseInformation.COLUMN_ID, dateTask.id)

    }

    override fun deleteTask(taskId: Int): Int {
        return taskDb.delete(DateTaskDataBaseInformation.COLUMN_ID, taskId)
    }

    override fun insertReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(DateTaskReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.month)
        remindDb.insert(values)
    }

    override fun readReminder(mainTaskId: Int): Cursor {
        return remindDb.read(
            arrayOf(DateTaskReminderDataBaseInformation.COLUMN_TASK_ID).toCollection(ArrayList()),
            arrayOf(mainTaskId.toString()).toCollection(ArrayList())
        )
    }

    override fun updateReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(DateTaskReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(DateTaskReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.month)
        remindDb.update(
            values,
            DateTaskReminderDataBaseInformation.COLUMN_TASK_ID,
            mainTaskId
        )
    }

    override fun deleteReminder(mainTaskId: Int) {
        remindDb.delete(DateTaskReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
    }
}