package com.example.bemohabatesh.data.model.tasks

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

data class DateTask(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var isDone: Int = 0,
    var createdAt: ShamsiCalendar = ShamsiCalendar(),
    var deadline: ShamsiCalendar = ShamsiCalendar()


) : Task(id, title, description, isDone, createdAt) {

}