package com.example.bemohabatesh.data.repository.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bemohabatesh.data.model.database.monthlyHabit.MonthlyHabitDataBaseInformation
import com.example.bemohabatesh.data.model.database.monthlyHabit.MonthlyHabitHistoryDataBaseInformation
import com.example.bemohabatesh.data.model.database.monthlyHabit.MonthlyHabitReminderDataBaseInformation
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.model.tasks.habit.WeeklyHabit
import com.example.bemohabatesh.data.repository.interfacess.EditableTask
import com.example.bemohabatesh.data.repository.interfacess.HistoricalTask
import com.example.bemohabatesh.data.repository.interfacess.RemindingTask
import com.example.bemohabatesh.utils.time.shamsi.ShamsiCalendar

class MonthlyHabitDataBaseHelper(context: Context) : EditableTask, RemindingTask, HistoricalTask {

    private val taskDb = DataBaseHelper(
        context = context,
        tableName = MonthlyHabitDataBaseInformation.TABLE_NAME,
        columnsName = MonthlyHabitDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = MonthlyHabitDataBaseInformation.TABLE_COLUMNS_TYPE
    )

    private val historyDb = DataBaseHelper(
        context = context,
        tableName = MonthlyHabitHistoryDataBaseInformation.TABLE_NAME,
        columnsName = MonthlyHabitHistoryDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = MonthlyHabitHistoryDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_ID,
            MonthlyHabitDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )

    private val remindDb = DataBaseHelper(
        context = context,
        tableName = MonthlyHabitReminderDataBaseInformation.TABLE_NAME,
        columnsName = MonthlyHabitReminderDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = MonthlyHabitReminderDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            MonthlyHabitReminderDataBaseInformation.COLUMN_TASK_ID,
            MonthlyHabitDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )


    override fun insertTask(task: Task): Long {
        val weeklyHabit = task as WeeklyHabit
        val values = ContentValues()

        values.put(MonthlyHabitDataBaseInformation.COLUMN_TASK_TITLE, weeklyHabit.title)
        values.put(MonthlyHabitDataBaseInformation.COLUMN_TASK_DESCRIPTION, weeklyHabit.description)
        values.put(
            MonthlyHabitDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            weeklyHabit.createdDay.day
        )
        values.put(
            MonthlyHabitDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            weeklyHabit.createdDay.month
        )
        values.put(
            MonthlyHabitDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            weeklyHabit.createdDay.year
        )

        return taskDb.insert(values)
    }

    override fun readTask(taskId: Int): Cursor {
        return taskDb.read(
            arrayOf(MonthlyHabitDataBaseInformation.COLUMN_ID).toCollection(ArrayList()),
            arrayOf(taskId.toString()).toCollection(ArrayList())
        )
    }

    override fun readAllTask(): Cursor {
        return taskDb.readAll(MonthlyHabitDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTask(task: Task): Int {
        val weeklyHabit = task as WeeklyHabit
        val values = ContentValues()

        values.put(MonthlyHabitDataBaseInformation.COLUMN_TASK_TITLE, weeklyHabit.title)
        values.put(MonthlyHabitDataBaseInformation.COLUMN_TASK_DESCRIPTION, weeklyHabit.description)
        values.put(
            MonthlyHabitDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            weeklyHabit.createdDay.day
        )
        values.put(
            MonthlyHabitDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            weeklyHabit.createdDay.month
        )

        values.put(
            MonthlyHabitDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            weeklyHabit.createdDay.year
        )

        return taskDb.update(values, MonthlyHabitDataBaseInformation.COLUMN_ID, weeklyHabit.id)
    }

    override fun deleteTask(taskId: Int): Int {
        return taskDb.delete(MonthlyHabitDataBaseInformation.COLUMN_ID, taskId)
    }

    override fun insertReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
        remindDb.insert(values)
    }

    override fun readReminder(mainTaskId: Int): Cursor {
        return remindDb.read(
            arrayOf(MonthlyHabitReminderDataBaseInformation.COLUMN_TASK_ID).toCollection(ArrayList()),
            arrayOf(mainTaskId.toString()).toCollection(ArrayList())
        )
    }

    override fun updateReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(MonthlyHabitReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
        remindDb.update(
            values,
            MonthlyHabitReminderDataBaseInformation.COLUMN_TASK_ID,
            mainTaskId
        )
    }

    override fun deleteReminder(mainTaskId: Int) {
        remindDb.delete(MonthlyHabitReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
    }

    override fun insertAllHistoryItem(mainTaskId: Int, days: ArrayList<ShamsiCalendar>) {
        days.forEach { day ->
            val values = ContentValues()
            values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_ID, mainTaskId)
            values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_YEAR, day.year)
            values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_MONTH, day.month)
            values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_DAY, day.day)
            values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_IS_DONE, 0)

            historyDb.insert(values)
        }
    }

    override fun readAllHistoryItem(mainTaskId: Int): Cursor {
        return historyDb.readAll(MonthlyHabitHistoryDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTaskHistory(mainTaskId: Int, day: ShamsiCalendar, doneState: Int) {
        val values = ContentValues()
        values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_ID, mainTaskId)
        values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_YEAR, day.year)
        values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_MONTH, day.month)
        values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_DAY, day.day)
        values.put(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_IS_DONE, doneState)

        historyDb.update(values, MonthlyHabitHistoryDataBaseInformation.COLUMN_ID, mainTaskId)
    }

    override fun deleteAllHistoryItem(mainTaskId: Int) {
        historyDb.deleteAll(MonthlyHabitHistoryDataBaseInformation.COLUMN_DATE_ID)
    }
}