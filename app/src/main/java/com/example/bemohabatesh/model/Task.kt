package com.example.bemohabatesh.model

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

abstract class Task (
    var id: Int,
    var title: String,
    var description: String,
    var isDone: Int,
    var createdAt: ShamsiCalendar
)