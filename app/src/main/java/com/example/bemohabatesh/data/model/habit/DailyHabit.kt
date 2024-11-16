package com.example.bemohabatesh.data.model.habit

import com.example.bemohabatesh.data.model.Task
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

open class DailyHabit(
    id: Int = 0,
    title: String = "",
    description: String = "",
    isDone: Int = 0,
    createdAt: ShamsiCalendar = ShamsiCalendar()

) : Task(id, title, description, isDone, createdAt)