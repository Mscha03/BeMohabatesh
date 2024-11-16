package com.example.bemohabatesh.data.model.habit

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

class MonthlyHabit(
    id: Int = 0, title: String = "", description: String = "", isDone: Int = 0,
    createdAt: ShamsiCalendar = ShamsiCalendar()

): DailyHabit(id, title, description, isDone, createdAt) {

    var remindDays = ArrayList<Int>()
}