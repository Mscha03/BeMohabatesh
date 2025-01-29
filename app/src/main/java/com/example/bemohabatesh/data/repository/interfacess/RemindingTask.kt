package com.example.bemohabatesh.data.repository.interfacess

import android.database.Cursor
import com.example.bemohabatesh.utils.time.shamsi.ShamsiCalendar

interface RemindingTask {
    fun insertReminder(mainTaskId: Int, date: ShamsiCalendar)
    fun readReminder(mainTaskId: Int): Cursor
    fun updateReminder(mainTaskId: Int, date: ShamsiCalendar)
    fun deleteReminder(mainTaskId: Int)
}