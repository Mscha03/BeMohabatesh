package com.example.bemohabatesh.data.repository.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bemohabatesh.data.model.database.dateTask.DateTaskReminderDataBaseInformation
import com.example.bemohabatesh.data.model.database.deadlineTask.DeadLineDataBaseInformation
import com.example.bemohabatesh.data.model.database.deadlineTask.DeadLineReminderDataBaseInformation
import com.example.bemohabatesh.data.model.database.deadlineTask.DeadLineSubTaskDataBaseInformation
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.repository.interfacess.EditableTask
import com.example.bemohabatesh.data.repository.interfacess.HaveSubTasks
import com.example.bemohabatesh.data.repository.interfacess.RemindingTask
import com.example.bemohabatesh.utils.time.shamsi.ShamsiCalendar

class DeadLineTaskDataBaseHelper(context: Context) : EditableTask, RemindingTask, HaveSubTasks {

    private val taskDb = DataBaseHelper(
        context = context,
        tableName = DeadLineDataBaseInformation.TABLE_NAME,
        columnsName = DeadLineDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DeadLineDataBaseInformation.TABLE_COLUMNS_TYPE
    )

    private val subTaskDb = DataBaseHelper(
        context = context,
        tableName = DeadLineSubTaskDataBaseInformation.TABLE_NAME,
        columnsName = DeadLineSubTaskDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DeadLineSubTaskDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            DeadLineSubTaskDataBaseInformation.COLUMN_TASK_ID,
            DeadLineDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )

    private val remindDb = DataBaseHelper(
        context = context,
        tableName = DeadLineReminderDataBaseInformation.TABLE_NAME,
        columnsName = DeadLineReminderDataBaseInformation.TABLE_COLUMNS_NAME,
        columnsType = DeadLineReminderDataBaseInformation.TABLE_COLUMNS_TYPE,
        primaryKey = true,
        foreignKeyColumns =
        arrayOf(
            DeadLineReminderDataBaseInformation.COLUMN_TASK_ID,
            DeadLineSubTaskDataBaseInformation.COLUMN_ID
        ).toCollection(ArrayList())
    )


    override fun insertTask(task: Task): Long {
        val deadlineTask = task as DeadlineTask
        val values = ContentValues()

        values.put(DeadLineDataBaseInformation.COLUMN_TASK_TITLE, deadlineTask.title)
        values.put(DeadLineDataBaseInformation.COLUMN_TASK_DESCRIPTION, deadlineTask.description)
        values.put(DeadLineDataBaseInformation.COLUMN_TASK_IS_DONE, deadlineTask.isDone)
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            deadlineTask.createdAt.day
        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            deadlineTask.createdAt.month

        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            deadlineTask.createdAt.year
        )

        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_DEADLINE_DAY,
            deadlineTask.deadline.day
        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_DEADLINE_MONTH,
            deadlineTask.deadline.month

        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_DEADLINE_YEAR,
            deadlineTask.deadline.year
        )

        return taskDb.insert(values)
    }

    override fun readTask(taskId: Int): Cursor {
        return taskDb.read(
            arrayOf(DeadLineDataBaseInformation.COLUMN_ID).toCollection(ArrayList()),
            arrayOf(taskId.toString()).toCollection(ArrayList())
        )
    }

    override fun readAllTask(): Cursor {
        return taskDb.readAll(DeadLineDataBaseInformation.TABLE_COLUMNS_NAME)
    }

    override fun updateTask(task: Task): Int {
        val deadlineTask = task as DeadlineTask
        val values = ContentValues()

        values.put(DeadLineDataBaseInformation.COLUMN_TASK_TITLE, deadlineTask.title)
        values.put(DeadLineDataBaseInformation.COLUMN_TASK_DESCRIPTION, deadlineTask.description)
        values.put(DeadLineDataBaseInformation.COLUMN_TASK_IS_DONE, deadlineTask.isDone)
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_CREATE_DAY,
            deadlineTask.createdAt.day
        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_CREATE_MONTH,
            deadlineTask.createdAt.month

        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_CREATE_YEAR,
            deadlineTask.createdAt.year
        )

        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_DEADLINE_DAY,
            deadlineTask.deadline.day
        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_DEADLINE_MONTH,
            deadlineTask.deadline.month

        )
        values.put(
            DeadLineDataBaseInformation.COLUMN_TASK_DEADLINE_YEAR,
            deadlineTask.deadline.year
        )

        return taskDb.update(values, DeadLineDataBaseInformation.COLUMN_ID, deadlineTask.id)
    }

    override fun deleteTask(taskId: Int): Int {
        return taskDb.delete(DeadLineDataBaseInformation.COLUMN_ID, taskId)
    }


    override fun insertSubTask(mainTask: Task, subTasks: ArrayList<SimpleTask>) {

        val task = mainTask as DeadlineTask

        subTasks.forEach { subTask ->
            val values = ContentValues()
            values.put(DeadLineSubTaskDataBaseInformation.COLUMN_TASK_ID, task.id)
            values.put(DeadLineSubTaskDataBaseInformation.COLUMN_TASK_TITLE, subTask.title)
            values.put(DeadLineSubTaskDataBaseInformation.COLUMN_TASK_IS_DONE, subTask.isDone)
            subTaskDb.insert(values)
        }
    }

    override fun readAllSubTask(taskId: Int): Cursor {
        return remindDb.read(
            arrayOf(DeadLineSubTaskDataBaseInformation.COLUMN_TASK_ID).toCollection(ArrayList()),
            arrayOf(taskId.toString()).toCollection(ArrayList())
        )
    }

    override fun updateSubTask(mainTaskId: Int, subTask: SimpleTask) {

        val values = ContentValues()
        values.put(DeadLineSubTaskDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(DeadLineSubTaskDataBaseInformation.COLUMN_TASK_TITLE, subTask.title)
        values.put(DeadLineSubTaskDataBaseInformation.COLUMN_TASK_IS_DONE, subTask.isDone)
        subTaskDb.insert(values)
    }

    override fun deleteSubTask(subTask: SimpleTask) {
        remindDb.delete(DateTaskReminderDataBaseInformation.COLUMN_TASK_ID, subTask.id)
    }

    override fun insertReminder(mainTaskId: Int, date: ShamsiCalendar) {
        val values = ContentValues()

        values.put(DeadLineReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month
        )
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
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

        values.put(DeadLineReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_YEAR, date.year)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_MONTH, date.month
        )
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_DAY, date.day)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_HOUR, date.hour)
        values.put(DeadLineReminderDataBaseInformation.COLUMN_REMIND_MINUTE, date.minute)
        remindDb.update(
            values,
            DeadLineReminderDataBaseInformation.COLUMN_TASK_ID,
            mainTaskId
        )
    }

    override fun deleteReminder(mainTaskId: Int) {
        remindDb.delete(DeadLineReminderDataBaseInformation.COLUMN_TASK_ID, mainTaskId)
    }

}