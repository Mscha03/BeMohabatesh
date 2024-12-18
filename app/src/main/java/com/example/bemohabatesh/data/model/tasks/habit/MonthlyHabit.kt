package com.example.bemohabatesh.data.model.tasks.habit

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar
import com.example.bemohabatesh.util.time.shamsi.ShamsiDetail

data class MonthlyHabit(
    override var id: Int = 0,
    override var title: String = "",
    override var description: String = "",
    override var isDone: Int = 0,
    override var createdAt: ShamsiCalendar = ShamsiCalendar(),
    var remindDays : ArrayList<ShamsiDetail.Companion.DaysOfWeekNames> = ArrayList()

): DailyHabit(id, title, description, isDone, createdAt)