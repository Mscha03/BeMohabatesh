package com.example.bemohabatesh.data.repository.interfacess

import com.example.bemohabatesh.data.model.Task
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

interface RemindingTask {
    fun insertReminder(mainTaskId: Int, date: ShamsiCalendar)
    fun readReminder(mainTaskId: Int)
    fun updateTask(mainTaskId: Int, date: ShamsiCalendar)
    fun deleteTask(mainTaskId: Int)
}