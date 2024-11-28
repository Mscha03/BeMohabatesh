package com.example.bemohabatesh.data.model.habit

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

data class MonthlyHabit(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var isDone: Int = 0,
    var createdAt: ShamsiCalendar = ShamsiCalendar()

): DailyHabit(id, title, description, isDone, createdAt) {

    var remindDays = ArrayList<Int>()
}