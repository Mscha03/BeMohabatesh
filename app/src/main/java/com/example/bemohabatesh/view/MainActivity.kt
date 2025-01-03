package com.example.bemohabatesh.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bemohabatesh.R
import com.example.bemohabatesh.data.model.tasks.DateTask
import com.example.bemohabatesh.data.model.tasks.DeadlineTask
import com.example.bemohabatesh.data.model.tasks.SimpleTask
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.model.tasks.habit.DailyHabit
import com.example.bemohabatesh.data.model.tasks.habit.WeeklyHabit
import com.example.bemohabatesh.util.time.shamsi.ShamsiCalendar



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
    fun MainScreen() {

        Scaffold(

            // fab
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        startActivity(Intent(this, AddTaskActivity::class.java))
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add icon")
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(stringResource(R.string.fab_text))
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { _ ->

            // screen Item
            Column {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.size(110.dp))
                    DateBox(ShamsiCalendar())
                    TaskViewBox(getArray())
                    Spacer(Modifier.size(100.dp))
                }
            }

        }
    }

    private fun getArray(): ArrayList<Task>{

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

        val tasks = ArrayList<Task>()

        tasks.add(simpleTask)
        tasks.add(dateTask)
        tasks.add(deadlineTask)
        tasks.add(deadlineTask2)
        tasks.add(dailyHabit)
        tasks.add(dailyHabit2)

        return tasks
    }
}















