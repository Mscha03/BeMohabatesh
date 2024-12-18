package com.example.bemohabatesh.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bemohabatesh.data.model.tasks.DateTask
import com.example.bemohabatesh.data.model.tasks.DeadlineTask
import com.example.bemohabatesh.data.model.tasks.SimpleTask
import com.example.bemohabatesh.data.model.tasks.habit.DailyHabit
import com.example.bemohabatesh.data.model.tasks.habit.WeeklyHabit
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar
import com.example.bemohabatesh.view.custom.widget.MultiStateCheckbox

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDrawer { MainScreen() }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreen(){

        val simpleTask = SimpleTask(
            title = "simple task",
            description = "description of simple task",
            isDone = 1
        )

        val dateTask = DateTask(
            title = "date task",
            description = "description of date task",
            isDone = 1
        )

        val deadlineTask = DeadlineTask(
            title = "deadline task",
            description = "description of deadline task",
            isDone = 0,
            deadline = ShamsiCalendar(),
            subTasks = arrayOf(
                SimpleTask(title = "sub 1", isDone = 0),
                SimpleTask(title = "sub 2", isDone = 0),
                SimpleTask(title = "sub 3", isDone = 0),
                SimpleTask(title = "sub 3", isDone = 0),
            ).toCollection(ArrayList())
        )

        val deadlineTask2 = DeadlineTask(
            title = "deadline2 task",
            description = "description of deadline 2 task",
            isDone = 0,
            deadline = ShamsiCalendar()
        )

        val dailyHabit = DailyHabit(
            title = "daily habit",
            description = "description of daily habit",
            isDone = 3,
        )

        val dailyHabit2 = WeeklyHabit(
            title = "weekly habit",
            description = "description of weekly habit",
            isDone = 1
        )



        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ){
            DateBox(ShamsiCalendar())
            SimpleTaskViewBox(simpleTask)
            DateTaskViewBox(dateTask)
            DeadlineTaskViewBox(deadlineTask)
            DeadlineTaskViewBox(deadlineTask2)
            HabitViewBox(dailyHabit)
            HabitViewBox(dailyHabit2)
        }
    }
}















