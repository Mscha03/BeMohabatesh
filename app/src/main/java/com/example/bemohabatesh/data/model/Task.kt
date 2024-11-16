package com.example.bemohabatesh.data.model

import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar

abstract class Task (
    private var id: Int,
    private var title: String,
    private var description: String,
    private var isDone: Int,
    private var createdDay: ShamsiCalendar
){
    // getter
    fun getId(): Int {return id}
    fun getTitle(): String {return title}
    fun getDescription(): String {return description}
    fun getIsDone(): Int {return isDone}
    fun getCreatedDay(): ShamsiCalendar {return createdDay}
}
