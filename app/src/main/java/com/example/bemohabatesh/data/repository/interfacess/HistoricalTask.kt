package com.example.bemohabatesh.data.repository.interfacess

import android.database.Cursor
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

interface HistoricalTask {
    fun insertAllHistoryItem(mainTaskId: Int, days: ArrayList<ShamsiCalendar>?)
    fun readAllHistoryItem(mainTaskId: Int): Cursor
    fun updateTaskHistory(mainTaskId: Int, day: ShamsiCalendar, doneState: Int)
    fun deleteAllHistoryItem(mainTaskId: Int)
}