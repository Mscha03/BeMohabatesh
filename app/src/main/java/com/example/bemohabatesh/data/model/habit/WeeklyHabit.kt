package com.example.bemohabatesh.data.model.habit

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar
import com.example.bemohabatesh.util.time.shamsi.ShamsiDetail

open class WeeklyHabit(
    id: Int = 0, title: String = "", description: String = "", isDone: Int = 0,
    createdAt: ShamsiCalendar = ShamsiCalendar()

): DailyHabit(id, title, description, isDone, createdAt){

    var remindDays = ArrayList<ShamsiDetail.Companion.DaysOfWeekNames>()
}