package com.example.bemohabatesh.data.model.tasks.habit

import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

open class DailyHabit(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var isDone: Int = 0,
    var createdAt: ShamsiCalendar = ShamsiCalendar()

) : Task(id, title, description, isDone, createdAt)