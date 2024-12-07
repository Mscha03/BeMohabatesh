package com.example.bemohabatesh.data.model.tasks.habit

import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

open class DailyHabit(
    open var id: Int = 0,
    open var title: String = "",
    open var description: String = "",
    open var isDone: Int = 0,
    open var createdAt: ShamsiCalendar = ShamsiCalendar()

) : Task(id, title, description, isDone, createdAt)