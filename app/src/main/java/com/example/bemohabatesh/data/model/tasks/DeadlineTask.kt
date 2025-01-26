package com.example.bemohabatesh.data.model.tasks

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

data class DeadlineTask(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var isDone: Int = 0,
    var createdAt: ShamsiCalendar = ShamsiCalendar(),
    var deadline: ShamsiCalendar = ShamsiCalendar(),
    var subTasks: ArrayList<SimpleTask> = ArrayList<SimpleTask>(),

    ): Task (
        id = id,
        title = title,
        description = description,
        isDone = isDone,
        createdDay = createdAt
    )