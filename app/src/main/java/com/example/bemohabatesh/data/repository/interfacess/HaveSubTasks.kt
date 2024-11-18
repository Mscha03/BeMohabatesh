package com.example.bemohabatesh.data.repository.interfacess

import android.database.Cursor
import com.example.bemohabatesh.data.model.SimpleTask
import com.example.bemohabatesh.data.model.Task

interface HaveSubTasks {
    fun insertSubTask(mainTask: Task, subTasks: ArrayList<SimpleTask>): Long
    fun readAllSubTask(taskId: Int): Cursor
    fun updateSubTask(mainTaskId: Int, subTask: SimpleTask)
    fun deleteTask(subTask: SimpleTask)
}