package com.example.bemohabatesh.model

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

class DateTask(
    id: Int, title: String, description: String, isDone: Int, createdAt: ShamsiCalendar

) :Task(id, title, description, isDone, createdAt) {

    private var deadline = ShamsiCalendar()
}