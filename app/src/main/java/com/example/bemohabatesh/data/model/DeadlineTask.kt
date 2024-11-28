package com.example.bemohabatesh.data.model

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

data class DeadlineTask(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var isDone: Int = 0,
    var createdAt: ShamsiCalendar = ShamsiCalendar()

) : Task(id, title, description, isDone, createdAt) {

    var deadline = ShamsiCalendar()
    var subTasks = ArrayList<SimpleTask>()
}