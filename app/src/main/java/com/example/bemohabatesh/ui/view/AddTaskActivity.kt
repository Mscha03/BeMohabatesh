package com.example.bemohabatesh.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bemohabatesh.data.model.tasks.TaskType
import com.example.bemohabatesh.ui.theme.dateTaskColor
import com.example.bemohabatesh.ui.theme.deadLinedTaskColor
import com.example.bemohabatesh.ui.theme.habitTaskColor
import com.example.bemohabatesh.ui.theme.simpleTaskColor
import com.example.bemohabatesh.utils.time.shamsi.ShamsiCalendar
import com.example.bemohabatesh.ui.view.ui.theme.Purple40
import java.time.LocalTime

class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            var borderColor by remember { mutableStateOf(value = Purple40) }
            val taskType = listOf(
                TaskType.SimpleTask,
                TaskType.DateTask,
                TaskType.DeadLinedTask,
                TaskType.HabitType.DailyHabit,
                TaskType.HabitType.WeeklyHabit,
                TaskType.HabitType.MonthlyHabit
            )

            var taskTitle = ""
            var taskDescription = ""
            var selectedTaskType by remember { mutableStateOf(taskType[0]) }

            var dateTaskDate: ShamsiCalendar? = null
            var deadlinedDay: ShamsiCalendar? = null
            var subTasksOfDeadlinedTask = ArrayList<SimpleTask>()

            var selectedWeeklyHabitDays = ArrayList<Int>()
            var selectedMonthlyHabitDays = ArrayList<Int>()

            var selectedHabitType: TaskType.HabitType? = null

            var haveAlarm by remember { mutableStateOf(false) }
            var time: LocalTime? = null

            Column(
                modifier = Modifier
                    .fillMaxSize(),
//                    .border(
//                        width = 5.dp,
//                        color = borderColor,
//                        shape = RoundedCornerShape(25.dp)
//                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Row (
                    modifier = Modifier
                        .border(
                        width = 2.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(25.dp)
                    ),
                ){
                    // page title
                    TitleScreenTextFiled(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 50.dp, bottom = 20.dp)
                    )

                    Spacer(modifier = Modifier.weight(0.5f))

                    Button(
                        modifier = Modifier.padding(top = 50.dp, end = 20.dp, bottom = 20.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {}
                    ) {

                        Text("Add")
                    }
                }

                Box {
                    Column(
                        modifier = Modifier
                            .padding(25.dp)
                            .verticalScroll(rememberScrollState()),
                    ) {

                        Text("task title")
                        taskTitle = taskTitleTextFiled()

                        Text("task description")
                        taskDescription = taskDescription()

                        Text("select task type")
                        selectedTaskType = selectTaskType()


                        when (selectedTaskType) {


                            TaskType.SimpleTask -> {
                                borderColor = simpleTaskColor
                            }

                            TaskType.DateTask -> {
                                dateTaskDate = selectDeadLine()
                                borderColor = dateTaskColor
                            }

                            TaskType.DeadLinedTask -> {
                                borderColor = deadLinedTaskColor
                                Column(modifier = Modifier.padding()) {
                                    val haveSubTask = remember { mutableStateOf(false) }

                                    deadlinedDay = selectDeadLine()

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(
                                            checked = haveSubTask.value,
                                            onCheckedChange = { haveSubTask.value = it })
                                        Text(text = "Have Sub Tasks")
                                    }
                                    if (haveSubTask.value) {
                                        subTasksOfDeadlinedTask = addSubTaskForDeadlinedTask()
                                    }
                                }
                            }

                            TaskType.Habit -> {

                                Column {
                                    Text("habit type")
                                }

                                borderColor = habitTaskColor

                                Row(
                                    modifier = Modifier
                                        .padding()
                                ) {
                                    selectedHabitType = selectHabitType()
                                }
                                when (selectedHabitType) {

                                    TaskType.HabitType.DailyHabit -> {
                                        // nothing
                                    }

                                    TaskType.HabitType.WeeklyHabit -> {
                                        selectedWeeklyHabitDays = weeklySelector()
                                    }

                                    TaskType.HabitType.MonthlyHabit -> {
                                        selectedMonthlyHabitDays = monthlySelector()
                                    }

                                    null -> {}
                                }

                            }

                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(1.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = haveAlarm, onCheckedChange = { haveAlarm = it })
                            Text(text = "Add Alarm")
                        }

                        if (haveAlarm) {
                            time = selectTime()
                        }
                    }
                }
            }
        }
    }



//    @Preview
//    @Composable
//    fun AddTaskMain() {
//
//        var taskTitle = ""
//        var taskDescription = ""
//
//        var specialDay: ShamsiCalendar? = null
//        var deadlinedDay: ShamsiCalendar? = null
//        var subTasksOfDeadlinedTask = ArrayList<SimpleTask>()
//
//        var selectedWeeklyHabitDays = ArrayList<Int>()
//        var selectedMonthlyHabitDays = ArrayList<Int>()
//
//        var expandedHabitType by remember { mutableStateOf(false) }
//        var selectedHabitType: TaskType.HabitType? = null
////
//        var haveAlarm by remember { mutableStateOf(false) }
//        val currentTime = Calendar.getInstance()
//        var time = LocalTime.now()
//
//        // UI
//        Column(
//            modifier = Modifier
////                .padding(16.dp)
//                .verticalScroll(rememberScrollState()),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            TitleScreenTextFiled()
//
//            // Background
//            Box(
//                modifier = Modifier
//                    .padding(7.dp)
//                    .fillMaxSize()
//                    .background(
//                        color = colorResource(id = R.color.white),
//                        shape = RoundedCornerShape(16.dp)
//                    )
//            ) {
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(20.dp),
//                    verticalArrangement = Arrangement.spacedBy(1.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    taskTitle = taskTitleTextFiled()
//
//                    taskDescription = taskDescription()
//
//                    val selectedTaskType = selectTaskType()
//
//                    // Show field depends on Task Type
//                    when (selectedTaskType) {
//
//                        TaskType.SimpleTask -> {
//                            // No field
//                        }
//
//                        TaskType.DateTask -> {
//                            specialDay = selectDeadLine()
//                        }
//
//                        TaskType.DeadLinedTask -> {
//                            Column(modifier = Modifier.padding()) {
//                                val haveSubTask = remember { mutableStateOf(false) }
//
//                                deadlinedDay = selectDeadLine()
//
//                                Row(verticalAlignment = Alignment.CenterVertically) {
//                                    Checkbox(
//                                        checked = haveSubTask.value,
//                                        onCheckedChange = { haveSubTask.value = it })
//                                    Text(text = "Have Sub Tasks")
//                                }
//                                if (haveSubTask.value) {
//                                    subTasksOfDeadlinedTask = addSubTaskForDeadlinedTask()
//                                }
//                            }
//                        }
//
//                        else -> {
//
//                            Row(
//                                modifier = Modifier
//                                    .padding()
//                            ) {
//
//                                selectedHabitType = selectHabitType()
//
//                            }
//                            when (selectedHabitType) {
//
//                                TaskType.HabitType.DailyHabit -> {
//                                    // nothing
//                                }
//
//                                TaskType.HabitType.WeeklyHabit -> {
//                                    selectedWeeklyHabitDays = weeklySelector()
//                                }
//
//                                TaskType.HabitType.MonthlyHabit -> {
//                                    selectedMonthlyHabitDays = monthlySelector()
//                                }
//
//                                null -> {}
//                            }
//
//                        }
//
//                    }
//
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(1.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Checkbox(checked = haveAlarm, onCheckedChange = { haveAlarm = it })
//                        Text(text = "Add Alarm")
//                    }
//
//                    if (haveAlarm) {
//                        time = selectTime()
//                    }
//                    // Add Button
//                    Button(modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 15.dp)
//                        .size(55.dp),
//                        shape = RoundedCornerShape(10.dp),
//                        onClick = {
//                            when (selectedTaskType) {
//
//                                TaskType.SimpleTask -> {
////                                    addSimpleTask(
////                                        taskTitle,
////                                        taskDescription
////                                    )
////                                    val intent = Intent(context, MainActivity::class.java)
////                                    context.startActivity(intent)
////                                    Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
//                                }
//
//                                TaskType.DateTask -> {
////                                    addSpecialTask(
////                                        taskTitle,
////                                        taskDescription,
////                                        specialDay!!
////                                    )
////                                    val intent = Intent(context, MainActivity::class.java)
////                                    context.startActivity(intent)
////                                    Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
//                                }
//
//                                TaskType.DeadLinedTask -> {
////                                    addDeadLinedTask(
////                                        taskTitle,
////                                        taskDescription,
////                                        deadlinedDay!!,
////                                        subTasksOfDeadlinedTask
////                                    )
////                                    val intent = Intent(context, MainActivity::class.java)
////                                    context.startActivity(intent)
////                                    Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
//                                }
//
//                                else -> {
//                                    val date = ShamsiCalendar()
////                                    when (selectedHabitType) {
////                                        TaskType.HabitType.DailyHabit -> {
////                                            addDailyHabit(
////                                                taskTitle,
////                                                taskDescription,
////                                                ShamsiCalendar(
////                                                    date.year,
////                                                    date.month,
////                                                    ShamsiCalendar.weekOfYear(JalaliCalendar()),
////                                                    date.day
////                                                )
////                                            )
////                                            val intent = Intent(context, MainActivity::class.java)
////                                            context.startActivity(intent)
////                                            Toast.makeText(
////                                                context,
////                                                "Task Added",
////                                                Toast.LENGTH_SHORT
////                                            ).show()
////                                        }
////
////                                        TaskType.HabitType.WeeklyHabit -> {
////                                            addWeeklyHabit(
////                                                taskTitle,
////                                                taskDescription,
////                                                WithWeekJalaliDateTime(
////                                                    date.year,
////                                                    date.month,
////                                                    getPersianWeekOfYear(JalaliCalendar()),
////                                                    date.day
////                                                ),
////                                                selectedWeeklyHabitDays
////                                            )
////                                            val intent = Intent(context, MainActivity2::class.java)
////                                            context.startActivity(intent)
////                                            Toast.makeText(
////                                                context,
////                                                "Task Added",
////                                                Toast.LENGTH_SHORT
////                                            ).show()
////                                        }
////
////                                        HabitType.MONTHLY -> {
////                                            addMonthlyHabit(
////                                                taskTitle,
////                                                taskDescription,
////                                                WithWeekJalaliDateTime(
////                                                    date.year,
////                                                    date.month,
////                                                    getPersianWeekOfYear(JalaliCalendar()),
////                                                    date.day
////                                                ),
////                                                selectedMonthlyHabitDays
////                                            )
////                                            val intent = Intent(context, MainActivity2::class.java)
////                                            context.startActivity(intent)
////                                            Toast.makeText(
////                                                context,
////                                                "Task Added",
////                                                Toast.LENGTH_SHORT
////                                            ).show()
////                                        }
////
////                                        null -> {}
////                                    }
//
//                                }
//                            }
//                        }
//                    ) { Text(text = "Add Task") }
//                }
//            }
//
//        }
//    }


//    fun addSimpleTask(title: String, description: String) {
//        val simpleTask = SimpleTask(title, description)
//        simpleDB!!.insertRecord(simpleTask)
//    }
//
//
//    fun addSpecialTask(title: String, description: String, deadLine: JalaliDateTime) {
//        val specialDayTask = SpecialDayTask(title, description, deadLine)
//        specialDB!!.insertRecord(specialDayTask)
//    }
//
//    fun addDeadLinedTask(
//        title: String,
//        description: String,
//        deadLine: JalaliDateTime,
//        subTasks: ArrayList<SimpleTask>
//    ) {
//
//        val deadLinedTask = DeadLinedTask(title, description, deadLine)
//        deadLinedTask.subTask = subTasks
//
//        val taskId = deadLinedDB!!.insertRecord(deadLinedTask)
//
//        subTasks.forEach {
//            deadLinedDB!!.insertSubTask(it, taskId.toInt())
//        }
//    }
//
//    fun addDailyHabit(
//        title: String,
//        description: String,
//        createDate: WithWeekJalaliDateTime,
//    ) {
//        val id = dailyHabitDB!!.insertRecord(
//            title, description, createDate.day, createDate.week, createDate.month, createDate.year
//        )
//
//        AddInformationForHistory.addForDaily(id.toInt(), dailyHabitDB, createDate)
//    }
//
//    fun addWeeklyHabit(
//        title: String,
//        description: String,
//        createDate: WithWeekJalaliDateTime,
//        daysOfWeek: ArrayList<Int>
//    ) {
//        val id = weeklyHabitDB!!.insertRecord(
//            title, description, createDate.day, createDate.week, createDate.month, createDate.year
//        )
//        AddInformationForHistory.addForWeekly(id.toInt(), weeklyHabitDB, createDate, daysOfWeek)
//    }
//
//    fun addMonthlyHabit(
//        title: String,
//        description: String,
//        createDate: WithWeekJalaliDateTime,
//        daysOfMonth: ArrayList<Int>
//    ) {
//        val id = monthlyHabitDB!!.insertRecord(
//            title, description, createDate.day, createDate.week, createDate.month, createDate.year
//        )
//        AddInformationForHistory.addForMonthly(id.toInt(), monthlyHabitDB, createDate, daysOfMonth)
//    }


}
