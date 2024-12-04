package com.example.bemohabatesh.data.model.tasks.habit

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar
import com.example.bemohabatesh.util.time.shamsi.ShamsiDetail

data class WeeklyHabit(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var isDone: Int = 0,
    var createdAt: ShamsiCalendar = ShamsiCalendar()

): DailyHabit(id, title, description, isDone, createdAt){

    var remindDays = ArrayList<ShamsiDetail.Companion.DaysOfWeekNames>()
}