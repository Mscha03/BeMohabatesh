package com.example.bemohabatesh.domin

sealed class HabitSchedule(
    open var repeat: RepeatType
){
    data class Flexible(
        override var repeat: RepeatType
    ): HabitSchedule(repeat = repeat)

    data class Scheduled(
        override var repeat: RepeatType,
        var days: List<Int>? = null
    ): HabitSchedule(repeat = repeat)
}

enum class RepeatType {
    DAILY,
    WEEKLY,
    MONTHLY
}

