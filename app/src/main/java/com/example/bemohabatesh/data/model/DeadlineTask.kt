package com.example.bemohabatesh.data.model

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

class DeadlineTask(
    id: Int = 0, title: String = "", description: String = "", isDone: Int = 0, createdAt: ShamsiCalendar = ShamsiCalendar()

) : Task(id, title, description, isDone, createdAt) {

    private var deadline = ShamsiCalendar()
    private var subTasks = ArrayList<SimpleTask>()
}