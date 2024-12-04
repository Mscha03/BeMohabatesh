package com.example.bemohabatesh.data.repository.interfacess

import android.database.Cursor
import com.example.bemohabatesh.data.model.tasks.Task

interface EditableTask {
     fun insertTask(task: Task): Long
     fun readTask(taskId: Int): Cursor
     fun readAllTask(): Cursor
     fun updateTask(task: Task): Int
     fun deleteTask(taskId: Int): Int
}

