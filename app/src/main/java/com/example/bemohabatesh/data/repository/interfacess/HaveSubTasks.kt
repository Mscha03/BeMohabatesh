package com.example.bemohabatesh.data.repository.interfacess

import android.database.Cursor
import com.example.bemohabatesh.data.model.tasks.SimpleTask
import com.example.bemohabatesh.data.model.tasks.Task

interface HaveSubTasks {
    fun insertSubTask(mainTask: Task, subTasks: ArrayList<SimpleTask>)
    fun readAllSubTask(taskId: Int): Cursor
    fun updateSubTask(mainTaskId: Int, subTask: SimpleTask)
    fun deleteTask(subTask: SimpleTask)
}