package com.example.bemohabatesh.data.repository.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bemohabatesh.data.model.database.dailyhabit.DailyHabitDataBaseInformation
import com.example.bemohabatesh.data.model.database.dailyhabit.DailyHabitHistoryDataBaseInformation
import com.example.bemohabatesh.data.model.database.dailyhabit.DailyHabitReminderDataBaseInformation
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.model.tasks.habit.DailyHabit
import com.example.bemohabatesh.data.repository.interfacess.EditableTask
import com.example.bemohabatesh.data.repository.interfacess.HistoricalTask
import com.example.bemohabatesh.data.repository.interfacess.RemindingTask
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

class DailyHabitDataBaseHelper(context: Context) : EditableTask, RemindingTask, HistoricalTask {

    private val taskDb = DataBaseHelper(
        context = context,
        tableName = DailyHabitDataBaseInformation.TABLE_NAME,
        columnsName = DailyHabitDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DailyHabitDataBaseInformation.TABLE_COLUMNS_TYPE
    )

    private val historyDb = DataBaseHelper(
        context = context,
        tableName = DailyHabitHistoryDataBaseInformation.TABLE_NAME,
        columnsName = DailyHabitHistoryDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DailyHabitHistoryDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            DailyHabitHistoryDataBaseInformation.COLUMN_DATE_ID,
            DailyHabitDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )

    private val remindDb = DataBaseHelper(
        context = context,
        tableName = DailyHabitReminderDataBaseInformation.TABLE_NAME,
        columnsName = DailyHabitReminderDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DailyHabitReminderDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            DailyHabitReminderDataBaseInformation.COLUMN_TASK_ID,
            DailyHabitDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )

    override fun insertTask(task: Task): Long {
        val dailyHabit = task as DailyHabit
        val values = ContentValues()

        values.put(DailyHabitDataBaseInformation.COLUMN_TASK_TITLE, dailyHabit.title)
        values.put(DailyHabitDataBaseInformation.COLUMN_TASK_DESCRIPTION, dailyHabit.description)
        values.put(
            DailyHabitDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            dailyHabit.createdAt.day
        )
        values.put(
            DailyHabitDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            dailyHabit.createdAt.month
        )
        values.put(
            DailyHabitDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            dailyHabit.createdAt.year
        )

        return taskDb.insert(values)
    }

    override fun readTask(taskId: Int): Cursor {
        return taskDb.read(
            arrayOf(DailyHabitDataBaseInformation.COLUMN_ID).toCollection(ArrayList()),
            arrayOf(taskId.toString()).toCollection(ArrayList())
        )
    }

    override fun readAllTask(): Cursor {
        return taskDb.readAll(DailyHabitDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTask(task: Task): Int {
        val dailyHabit = task as DailyHabit
        val values = ContentValues()

        values.put(DailyHabitDataBaseInformation.COLUMN_TASK_TITLE, dailyHabit.title)
        values.put(DailyHabitDataBaseInformation.COLUMN_TASK_DESCRIPTION, dailyHabit.description)
        values.put(
            DailyHabitDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            dailyHabit.createdAt.day
        )
        values.put(
            DailyHabitDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            dailyHabit.createdAt.month
        )
        values.put(
            DailyHabitDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            dailyHabit.createdAt.year
        )

        return taskDb.update(values, DailyHabitDataBaseInformation.COLUMN_ID, dailyHabit.id)
    }

    override fun deleteTask(taskId: Int): Int {
        return taskDb.delete(DailyHabitDataBaseInformation.COLUMN_ID, taskId)
    }

    override fun insertReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(DailyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
        remindDb.insert(values)
    }

    override fun readReminder(mainTaskId: Int): Cursor {
        return remindDb.read(
            arrayOf(DailyHabitReminderDataBaseInformation.COLUMN_TASK_ID).toCollection(ArrayList()),
            arrayOf(mainTaskId.toString()).toCollection(ArrayList())
        )
    }

    override fun updateReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(DailyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(DailyHabitReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
        remindDb.update(
            values,
            DailyHabitReminderDataBaseInformation.COLUMN_TASK_ID,
            mainTaskId
        )
    }

    override fun deleteReminder(mainTaskId: Int) {
        remindDb.delete(DailyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
    }

    override fun insertAllHistoryItem(mainTaskId: Int, days: ArrayList<ShamsiCalendar>) {
        days.forEach { day ->
            val values = ContentValues()
            values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_ID, mainTaskId)
            values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_YEAR, day.year)
            values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_MONTH, day.month)
            values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_DAY, day.day)
            values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_IS_DONE, 0)

            historyDb.insert(values)
        }
    }

    override fun readAllHistoryItem(mainTaskId: Int): Cursor {
        return historyDb.readAll(DailyHabitHistoryDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTaskHistory(mainTaskId: Int, day: ShamsiCalendar, doneState: Int) {
        val values = ContentValues()
        values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_ID, mainTaskId)
        values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_YEAR, day.year)
        values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_MONTH, day.month)
        values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_DAY, day.day)
        values.put(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_IS_DONE, doneState)

        historyDb.update(values, DailyHabitHistoryDataBaseInformation.COLUMN_ID, mainTaskId)
    }

    override fun deleteAllHistoryItem(mainTaskId: Int) {
        historyDb.deleteAll(DailyHabitHistoryDataBaseInformation.COLUMN_DATE_ID)
    }

}