package com.example.bemohabatesh.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bemohabatesh.R
import com.example.bemohabatesh.data.model.tasks.TaskType
import com.example.bemohabatesh.utils.time.shamsi.ShamsiCalendar
import com.example.bemohabatesh.utils.time.shamsi.ShamsiDetail
import java.time.LocalTime

@Composable
fun TitleScreenTextFiled(modifier: Modifier = Modifier.padding(10.dp)) {
    // Title
    Text(
        modifier = modifier,
        text = "Add your task",
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun taskTitleTextFiled(defaultText: String = ""): String {
    var taskTitle by remember { mutableStateOf(defaultText) }

    // Task Title
    TextField(
        value = taskTitle,
        onValueChange = { taskTitle = it },
        label = { Text("Task Title") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f))
    )

    return taskTitle
}


@Composable
fun taskDescription(defaultText: String = ""): String {
    // Task Description
    var taskDescription by remember { mutableStateOf(defaultText) }

    TextField(
        value = taskDescription,
        onValueChange = { taskDescription = it },
        label = { Text("Task Description") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f)),
    )

    return taskDescription
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun selectTaskType(): TaskType {
    var selectedTaskType by remember { mutableStateOf(TaskType.SimpleTask) }
    var expandedTaskType by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expandedTaskType,
            onExpandedChange = { expandedTaskType = !expandedTaskType }
        ) {
            TextField(
                value = selectedTaskType.name,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Task Type") },
                modifier = Modifier
                    .menuAnchor()
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f),),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTaskType) }
            )

            ExposedDropdownMenu(
                expanded = expandedTaskType,
                onDismissRequest = { expandedTaskType = false }
            ) {
                TaskType.entries.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.name) },
                        onClick = {
                            expandedTaskType = false
                            selectedTaskType = type
                        })
                }
            }
        }
    }
    return selectedTaskType
}

@Composable
fun selectDeadLine(): ShamsiCalendar {
    val openDialog = remember { mutableStateOf(false) }
    val specialDay by remember { mutableStateOf(ShamsiCalendar()) }


    Row(
        modifier = Modifier
            .padding()
    ) {

        // Text field show date
        TextField(
            modifier = Modifier
                .width(230.dp)
                .padding(vertical = 6.dp),
            value = "Date: ${specialDay.day} ${
                ShamsiDetail.shamsiMonthName(
                    monthNumber = specialDay.month,
                    context = LocalContext.current
                )
            } ${specialDay.year}",
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f))
        )

        // button select date
        Button(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(5.dp)
                .width(150.dp)
                .height(60.dp),
            onClick = { openDialog.value = true }
        ) {
            Text("Choose Date")
        }

    }

    // date selector
//        JalaliDatePickerDialog(
//            openDialog = openDialog,
//            onSelectDay = {
//                // nothing
//            },
//            onConfirm = {
//                specialDay = JalaliDateTime(it.year, it.month, it.day)
//                Log.d(
//                    "Date",
//                    "onConfirm: ${it.day} ${it.monthString} ${it.year}"
//                )
//            }
//        )
    return specialDay
}



@SuppressLint("MutableCollectionMutableState")
@Composable
fun addSubTaskForDeadlinedTask(): ArrayList<SimpleTask> {
    val subTaskTitles = remember { mutableStateListOf("sub task") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "sub Tasks",
            modifier = Modifier.padding(start = 25.dp, top = 15.dp),
        )
        LazyColumn(
            modifier = Modifier
                .height((subTaskTitles.size * 50).dp)
        ) {
            items(subTaskTitles.size) { index ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = {},
                    )
                    Column(modifier = Modifier.padding(10.dp)) {
                        BasicTextField(value = subTaskTitles[index],
                            onValueChange = {
                                subTaskTitles[index] = it
                            })
                    }

                    Spacer(modifier = Modifier.weight(1f)) // فضای خالی برای چسباندن آیکون به راست‌ترین قسمت


                    // دکمه با آیکون منفی
                    IconButton(
                        onClick = {
                            subTaskTitles.removeAt(index)
                        },
                        modifier = Modifier.padding(end = 10.dp) // فاصله از عنوان
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close, // استفاده از آیکون منفی
                            contentDescription = "حذف", // توصیف تصویر
                            modifier = Modifier.size(24.dp) // اندازه تصویر
                        )
                    }

                }
            }
        }

        Button(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(5.dp),
            onClick = {
                subTaskTitles.add("sub task")
            }
        ) {
            Text("Add sub task")
        }
    }

    val subTasks = ArrayList<SimpleTask>()

//        subTaskTitles.forEach { item -> subTasks.add(SimpleTask(item, "")) }

    return subTasks
}

@Composable
private fun ShowItem() {
    addSubTaskForDeadlinedTask()
}


@Composable
fun weeklySelector(): ArrayList<Int> {
    // وضعیت روزهای انتخاب شده
    var selectedDays by remember { mutableStateOf(listOf<String>()) }
    var selectedDaysInt by remember { mutableStateOf(listOf<Int>()) }

    Column {
        WeekDaySelector(selectedDays = selectedDays) { day ->
            // تغییر وضعیت انتخاب یک روز
            if (selectedDays.contains(day)) {
                selectedDays = selectedDays - day // حذف روز
                selectedDaysInt = selectedDaysInt - dayStrToInt(day)
            } else {
                selectedDays = selectedDays + day  // اضافه کردن روز
                selectedDaysInt = selectedDaysInt + dayStrToInt(day)

            }
        }
    }


    return ArrayList(selectedDaysInt)
}

fun dayStrToInt(day: String): Int {
    return when (day) {
        "Sat" -> 1
        "Sun" -> 2
        "Mon" -> 3
        "Tue" -> 4
        "Wen" -> 5
        "Thu" -> 6
        "Fri" -> 7
        else -> -1
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeekDaySelector(
    selectedDays: List<String>,  // روزهای انتخاب شده
    onDaySelected: (String) -> Unit,  // تابعی برای زمانی که کاربر یک روز را انتخاب یا حذف کند
) {
    // آرایه‌ای از روزهای هفته
    val daysOfWeek = listOf("Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri")

    FlowRow(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        daysOfWeek.forEach { day ->
            val isSelected = selectedDays.contains(day)

            Box(
                modifier = Modifier
                    .size(50.dp) // اندازه دایره
                    .clip(shape = CircleShape)
                    .background(
                        if (isSelected) {
                            ButtonDefaults.buttonColors().containerColor
                        } else {
                            ButtonDefaults.buttonColors().containerColor.copy(alpha = 0.2f)
                        }
                    )  // توپر بنفش برای انتخاب شده و شفاف برای انتخاب نشده
                    .border(
                        width = 2.dp,
                        color = if (isSelected) ButtonDefaults.buttonColors().disabledContainerColor else Color.Gray,
                        shape = CircleShape
                    )
                    .clickable { onDaySelected(day) },  // کلیک کردن برای انتخاب/حذف روز
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    color = if (isSelected) Color.White else Color.Black
                )  // رنگ متن برای حالت انتخاب شده و نشده
            }
            Spacer(modifier = Modifier.size(2.dp))
        }
    }
}


@Composable
fun monthlySelector(): ArrayList<Int> {
    // وضعیت روزهای انتخاب شده
    var selectedDays by remember { mutableStateOf(listOf<Int>()) }

    Column {
        MonthDaySelector(selectedDays = selectedDays) { day ->
            // تغییر وضعیت انتخاب یک روز
            selectedDays = if (selectedDays.contains(day)) {
                selectedDays - day  // حذف روز
            } else {
                selectedDays + day  // اضافه کردن روز
            }
        }

    }

    return ArrayList(selectedDays)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MonthDaySelector(
    selectedDays: List<Int>,  // روزهای انتخاب شده
    onDaySelected: (Int) -> Unit,  // تابعی برای زمانی که کاربر یک روز را انتخاب یا حذف کند
) {
    // آرایه‌ای از روزهای ماه
    val daysOfWeek = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
        31
    )

    FlowRow(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEach { day ->
            val isSelected = selectedDays.contains(day)

            Box(
                modifier = Modifier
                    .size(50.dp)  // اندازه دایره
                    .clip(CircleShape)
                    .background(
                        if (isSelected) {
                            ButtonDefaults.buttonColors().containerColor
                        } else {
                            ButtonDefaults.buttonColors().containerColor.copy(alpha = 0.2f)
                        }
                    )  // توپر بنفش برای انتخاب شده و شفاف برای انتخاب نشده
                    .border(
                        width = 2.dp,
                        color = if (isSelected) ButtonDefaults.buttonColors().disabledContainerColor else Color.Gray,
                        shape = CircleShape
                    )
                    .clickable { onDaySelected(day) },  // کلیک کردن برای انتخاب/حذف روز
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.toString(),
                    color = if (isSelected) Color.White else Color.Black
                )  // رنگ متن برای حالت انتخاب شده و نشده
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun selectHabitType(): TaskType.HabitType {
    var selectedHabitType by remember { mutableStateOf(TaskType.HabitType.DailyHabit) }
    var expandedTaskType by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expandedTaskType,
            onExpandedChange = { expandedTaskType = !expandedTaskType }
        ) {
            TextField(
                value = selectedHabitType.name,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Task Type") },
                modifier = Modifier
                    .menuAnchor()
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTaskType) },
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f))

            )

            ExposedDropdownMenu(
                expanded = expandedTaskType,
                onDismissRequest = { expandedTaskType = false }
            ) {
                TaskType.HabitType.entries.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.name) },
                        onClick = {
                            expandedTaskType = false
                            selectedHabitType = type
                        })
                }
            }
        }
    }
    return selectedHabitType
}



//    @RequiresApi(Build.VERSION_CODES.O)
@Composable
fun selectTime(): LocalTime? {
    val time by remember { mutableStateOf(LocalTime.now()) }
//        val timeDialogState = rememberMaterialDialogState()

    Row(
        modifier = Modifier
            .padding()
    ) {

        // Text field show date
        TextField(
            modifier = Modifier
                .width(230.dp)
                .padding(vertical = 6.dp),
            value = "${time.hour}:${time.minute}",
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f))
        )

        // button select date
        Button(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(5.dp)
                .width(150.dp)
                .height(60.dp),
            onClick = {
//                    timeDialogState.show()
            }
        ) {
            Text("Choose Time")
        }

    }


//        MaterialDialog(
//            dialogState = timeDialogState,
//            buttons = {
//                positiveButton(text = "Ok") {
//
//                }
//                negativeButton(text = "Cancel")
//            },
//            shape = RoundedCornerShape(30.dp),
//            backgroundColor = Color.White,
//        ) {
//            timepicker(
//                title = "Pick a time",
//            ) {
//                time = it
//            }
//        }


    return time
}

