package com.example.bemohabatesh.data.repository.interfacess

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

interface RemindingTask {
    fun insertReminder(mainTaskId: Int, date: ShamsiCalendar)
    fun readReminder(mainTaskId: Int)
    fun updateReminder(mainTaskId: Int, date: ShamsiCalendar)
    fun deleteReminder(mainTaskId: Int)
}