package com.example.bemohabatesh.data.model.tasks

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

    abstract class Task (
    private var id: Int,
    private var title: String,
    private var description: String,
    private var isDone: Int,
    private var createdDay: ShamsiCalendar
)
