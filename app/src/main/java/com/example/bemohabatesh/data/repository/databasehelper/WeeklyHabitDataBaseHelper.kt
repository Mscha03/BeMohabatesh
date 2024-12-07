package com.example.bemohabatesh.data.repository.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bemohabatesh.data.model.database.dailyhabit.DailyHabitHistoryDataBaseInformation
import com.example.bemohabatesh.data.model.database.weeklyhabit.WeeklyHabitDataBaseInformation
import com.example.bemohabatesh.data.model.database.weeklyhabit.WeeklyHabitHistoryDataBaseInformation
import com.example.bemohabatesh.data.model.database.weeklyhabit.WeeklyHabitReminderDataBaseInformation
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.model.tasks.habit.WeeklyHabit
import com.example.bemohabatesh.data.repository.interfacess.EditableTask
import com.example.bemohabatesh.data.repository.interfacess.HistoricalTask
import com.example.bemohabatesh.data.repository.interfacess.RemindingTask
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

class WeeklyHabitDataBaseHelper(context: Context) : EditableTask, RemindingTask, HistoricalTask {

    private val taskDb = DataBaseHelper(
        context = context,
        tableName = WeeklyHabitDataBaseInformation.TABLE_NAME,
        columnsName = WeeklyHabitDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = WeeklyHabitDataBaseInformation.TABLE_COLUMNS_TYPE
    )

    private val historyDb = DataBaseHelper(
        context = context,
        tableName = WeeklyHabitHistoryDataBaseInformation.TABLE_NAME,
        columnsName = WeeklyHabitHistoryDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = WeeklyHabitHistoryDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_ID,
            WeeklyHabitDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )

    private val remindDb = DataBaseHelper(
        context = context,
        tableName = WeeklyHabitReminderDataBaseInformation.TABLE_NAME,
        columnsName = WeeklyHabitReminderDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = WeeklyHabitReminderDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            WeeklyHabitReminderDataBaseInformation.COLUMN_TASK_ID,
            WeeklyHabitDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )

    override fun insertTask(task: Task): Long {
        val weeklyHabit = task as WeeklyHabit
        val values = ContentValues()

        values.put(WeeklyHabitDataBaseInformation.COLUMN_TASK_TITLE, weeklyHabit.title)
        values.put(WeeklyHabitDataBaseInformation.COLUMN_TASK_DESCRIPTION, weeklyHabit.description)
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            weeklyHabit.createdAt.getDay()
        )
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_WEEK,
            weeklyHabit.createdAt.getWeek()
        )
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            weeklyHabit.createdAt.getMonth()
        )
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            weeklyHabit.createdAt.getYear()
        )

        return taskDb.insert(values)
    }

    override fun readTask(taskId: Int): Cursor {
        return taskDb.read(
            arrayOf(WeeklyHabitDataBaseInformation.COLUMN_ID).toCollection(ArrayList()),
            arrayOf(taskId.toString()).toCollection(ArrayList())
        )
    }

    override fun readAllTask(): Cursor {
        return taskDb.readAll(WeeklyHabitDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTask(task: Task): Int {
        val weeklyHabit = task as WeeklyHabit
        val values = ContentValues()

        values.put(WeeklyHabitDataBaseInformation.COLUMN_TASK_TITLE, weeklyHabit.title)
        values.put(WeeklyHabitDataBaseInformation.COLUMN_TASK_DESCRIPTION, weeklyHabit.description)
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            weeklyHabit.createdAt.getDay()
        )
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            weeklyHabit.createdAt.getMonth()
        )
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_WEEK,
            weeklyHabit.createdAt.getWeek()
        )
        values.put(
            WeeklyHabitDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            weeklyHabit.createdAt.getYear()
        )

        return taskDb.update(values, WeeklyHabitDataBaseInformation.COLUMN_ID, weeklyHabit.id)
    }

    override fun deleteTask(taskId: Int): Int {
        return taskDb.delete(WeeklyHabitDataBaseInformation.COLUMN_ID, taskId)
    }

    override fun insertReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.getYear())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.getMonth())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_DAY, date.getDay())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.getHour())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.getMinute())
        remindDb.insert(values)
    }

    override fun readReminder(mainTaskId: Int): Cursor {
        return remindDb.read(
            arrayOf(WeeklyHabitReminderDataBaseInformation.COLUMN_TASK_ID).toCollection(ArrayList()),
            arrayOf(mainTaskId.toString()).toCollection(ArrayList())
        )
    }

    override fun updateReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.getYear())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.getMonth())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_DAY, date.getDay())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.getHour())
        values.put(WeeklyHabitReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.getMinute())
        remindDb.update(
            values,
            WeeklyHabitReminderDataBaseInformation.COLUMN_TASK_ID,
            mainTaskId
        )
    }

    override fun deleteReminder(mainTaskId: Int) {
        remindDb.delete(WeeklyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
    }

    override fun insertAllHistoryItem(mainTaskId: Int, days: ArrayList<ShamsiCalendar>) {
        days.forEach { day ->
            val values = ContentValues()
            values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_ID, mainTaskId)
            values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_YEAR, day.getYear())
            values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_WEEK, day.getWeek())
            values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_MONTH, day.getMonth())
            values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_DAY, day.getDay())
            values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_IS_DONE, 0)

            historyDb.insert(values)
        }
    }

    override fun readAllHistoryItem(mainTaskId: Int): Cursor {
        return historyDb.readAll(WeeklyHabitHistoryDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTaskHistory(mainTaskId: Int, day: ShamsiCalendar, doneState: Int) {
        val values = ContentValues()
        values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_ID, mainTaskId)
        values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_YEAR, day.getYear())
        values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_MONTH, day.getMonth())
        values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_WEEK, day.getMonth())
        values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_DAY, day.getDay())
        values.put(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_IS_DONE, doneState)

        historyDb.update(values, WeeklyHabitHistoryDataBaseInformation.COLUMN_ID, mainTaskId)
    }

    override fun deleteAllHistoryItem(mainTaskId: Int) {
        historyDb.deleteAll(WeeklyHabitHistoryDataBaseInformation.COLUMN_DATE_ID)
    }
}