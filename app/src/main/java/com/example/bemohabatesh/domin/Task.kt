package com.example.bemohabatesh.domin

import org.example.ui.screens.utils.time.shamsi.ShamsiCalendar


sealed class Task(
    open val id: Int,
    open var title: String,
    open var description: String,
    open val createdDay: ShamsiCalendar,
    open var hasAlarm: Boolean,
    open var alarmTime: ShamsiCalendar? = null,
    open var subTasks: List<SubTask>? = null,
) {
    data class SimpleTask(
        override val id: Int,
        override var title: String,
        override var description: String,
        override val createdDay: ShamsiCalendar,
        override var hasAlarm: Boolean,
        override var alarmTime: ShamsiCalendar? = null,
        override var subTasks: List<SubTask>? = null
    ): Task(id = id, title = title, description = description, createdDay = createdDay, hasAlarm = hasAlarm, alarmTime = alarmTime)

    data class DateTask(
        override val id: Int,
        override var title: String,
        override var description: String,
        override val createdDay: ShamsiCalendar,
        override var hasAlarm: Boolean,
        var deadlineTime: ShamsiCalendar,
        override var subTasks: List<SubTask>?= null
    ): Task(id = id, title = title, description = description, createdDay = createdDay, hasAlarm = hasAlarm, alarmTime = deadlineTime)

    data class DeadlineTask(
        override val id: Int,
        override var title: String,
        override var description: String,
        override val createdDay: ShamsiCalendar,
        override var hasAlarm: Boolean,
        var deadlineTime: ShamsiCalendar,
        override var subTasks: List<SubTask>?= null
    ): Task(id = id, title = title, description = description, createdDay = createdDay, hasAlarm = hasAlarm, alarmTime = deadlineTime)

    data class HabitTask(
        override val id: Int,
        override var title: String,
        override var description: String,
        override val createdDay: ShamsiCalendar,
        override var hasAlarm: Boolean,
        override var alarmTime: ShamsiCalendar?= null,
        var habitType: HabitType,
        var schedule: HabitSchedule

        ) : Task(id = id, title = title, description = description, createdDay = createdDay, hasAlarm = hasAlarm, alarmTime = alarmTime)

}