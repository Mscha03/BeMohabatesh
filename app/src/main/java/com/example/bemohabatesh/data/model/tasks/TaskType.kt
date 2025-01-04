package com.example.bemohabatesh.data.model.tasks

enum class TaskType {
    SimpleTask,
    DateTask,
    DeadLinedTask,
    Habit;

    enum class HabitType {
        DailyHabit,
        WeeklyHabit,
        MonthlyHabit
    }

}