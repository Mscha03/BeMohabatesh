package com.example.bemohabatesh.domin

data class SubTask(
    val id: Int,
    val taskId: Int,
    val title: String,
    var isCompleted: Boolean,
)