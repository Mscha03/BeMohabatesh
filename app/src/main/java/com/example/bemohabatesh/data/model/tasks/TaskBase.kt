package com.example.bemohabatesh.data.model.tasks

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

abstract class TaskBase (
    private var id: Int,
    private var title: String,
    private var description: String,
    private var createdDay: ShamsiCalendar
)