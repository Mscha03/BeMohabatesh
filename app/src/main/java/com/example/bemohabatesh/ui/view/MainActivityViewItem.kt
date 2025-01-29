package com.example.bemohabatesh.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bemohabatesh.R
import com.example.bemohabatesh.data.model.tasks.Task
import com.example.bemohabatesh.data.model.tasks.habit.DailyHabit
import com.example.bemohabatesh.ui.theme.Purple40
import com.example.bemohabatesh.ui.theme.TextDescriptionColorLight
import com.example.bemohabatesh.ui.theme.TextMainColorLight
import com.example.bemohabatesh.ui.theme.dateBoxColor
import com.example.bemohabatesh.ui.theme.dateTaskColor
import com.example.bemohabatesh.ui.theme.deadLinedTaskColor
import com.example.bemohabatesh.ui.theme.habitTaskColor
import com.example.bemohabatesh.ui.theme.simpleTaskColor
import com.example.bemohabatesh.utils.convert.BoolInt
import com.example.bemohabatesh.utils.navigationdrawer.changeActivity
import com.example.bemohabatesh.utils.time.shamsi.ShamsiCalendar
import com.example.bemohabatesh.utils.time.shamsi.ShamsiDetail
import com.example.bemohabatesh.utils.view.checkBoxColorSelect
import com.example.bemohabatesh.utils.view.habitTitleTextColor
import com.example.bemohabatesh.utils.view.habitTitleTextModifier
import com.example.bemohabatesh.utils.view.taskTitleTextColor
import com.example.bemohabatesh.utils.view.taskTitleTextModifier
import com.example.bemohabatesh.ui.view.custom.widget.MultiStateCheckbox
import kotlinx.coroutines.launch


// Navigation Drawer
@Composable
fun NavigationDrawer(screenUI: @Composable () -> Unit) {
    // State to control drawer visibility
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        },
        content = {
            MainContent(onOpenDrawer = {
                scope.launch {
                    drawerState.open() // Open drawer
                }
            }, screenUI)
        }
    )
}

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "App Logo"
        )
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .background(color = Color.White.copy(alpha = 0.7f), shape = RoundedCornerShape(16.dp))
                .border(width = 2.dp, color = Purple40, shape = RoundedCornerShape(16.dp))
        ){
            DrawerItem(thisClass = MainActivity::class.java, destinationClass = MainActivity::class.java)
        }
    }
}

@Composable
fun DrawerItem(thisClass: Class<*>, destinationClass: Class<*>){
    val context = LocalContext.current
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .clickable {
                changeActivity(context = context, thisClass = thisClass, destinationClass = destinationClass)
            }
    ){
        Icon(imageVector = Icons.Rounded.Build, contentDescription = "")
        Spacer(modifier = Modifier.size(15.dp))
        Text(text = "Main Activity", fontSize = 16.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(onOpenDrawer: () -> Unit, screenUI: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, Purple40, RoundedCornerShape(16.dp)),
                colors = TopAppBarColors(
                    containerColor = Color.White.copy(alpha = 0.9f),
                    scrolledContainerColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                title = { Text(text = stringResource(R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxSize(),
        ) {
            screenUI()
        }
    }
}


// box top of screen show date
@SuppressLint("ResourceType")
@Composable
fun DateBox(date: ShamsiCalendar) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 60.dp, width = 15.dp)
            .background(
                color = dateBoxColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = {

            }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {

        Spacer(modifier = Modifier.weight(1f))

        // date
        Text(
            text = "${
                ShamsiDetail.shamsiWeekDayName(
                    context = LocalContext.current,
                    dayNumber = date.dayOfWeek
                )
            } - " + "${date.year}" +
                    " ${
                        ShamsiDetail.shamsiMonthName(
                            context = LocalContext.current,
                            monthNumber = date.month
                        )
                    } ${date.day}",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
            contentDescription = "arrow down"
        )

        Spacer(modifier = Modifier.size(12.dp))

    }
}


// box top of screen show date
@Composable
fun SimpleTaskViewBox(simpleTask: SimpleTask) {

    var checkBoxState by remember {
        mutableIntStateOf(simpleTask.isDone)
    }

    // task box
    Surface(
        modifier = Modifier
            .padding(
                start = 0.dp,
                top = 15.dp
            )
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
        ) {

            // task color box
            Surface(
                color = simpleTaskColor,
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 15.dp, height = 60.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)

                    )
                }
            }

            // task detail
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
                    .clickable(onClick = {
                        TODO("روش که کلیک میکنی باید صفحه ویرایش باز بشه")
                    })
            ) {
                // task title
                Text(
                    text = simpleTask.title,
                    color = taskTitleTextColor(simpleTask.isDone),
                    fontSize = 20.sp,
                    modifier = taskTitleTextModifier(simpleTask.isDone)
                )
                // space between title and description
                Spacer(modifier = Modifier.size(2.dp))

                // date of dead line
                Text(
                    text = simpleTask.description,
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    color = TextDescriptionColorLight,
                )
            }

            // space between task detail and checkbox
            Spacer(
                modifier =
                Modifier.weight(1f)
            )

            // checkbox
            Surface(
                // checkbox scope
                color = checkBoxColorSelect(BoolInt.intToBool(checkBoxState)),
            ) {
                // checkbox

                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 30.dp, height = 60.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Checkbox(
                        checked = BoolInt.intToBool(checkBoxState),
                        onCheckedChange = {
                            simpleTask.isDone = checkboxChangeState(checkBoxState)
                            checkBoxState = checkboxChangeState(checkBoxState)
                        },
                        Modifier.size(100.dp)
                    )
                }
            }
        }
    }

}

fun checkboxChangeState(isDone: Int): Int {
    return if (isDone == 0) 1 else 0
}

// box top of screen show date task
@Composable
fun DateTaskViewBox(dateTask: DateTask) {

    var checkBoxState by remember {
        mutableIntStateOf(dateTask.isDone)
    }

    // task box
    Surface(
        modifier = Modifier
            .padding(
                start = 0.dp,
                top = 15.dp
            )
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
        ) {
            // task color box
            Surface(
                color = dateTaskColor,
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 15.dp, height = 60.dp)
                        .align(Alignment.CenterVertically)
                ) {

                    Text(
                        text = "",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)

                    )
                }
            }


            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
            ) {
                // task title
                Text(
                    text = dateTask.title,
                    color = taskTitleTextColor(checkBoxState),
                    fontSize = 20.sp,
                    modifier = taskTitleTextModifier(checkBoxState)
                )

                // space between title and description
                Spacer(modifier = Modifier.size(2.dp))

                // description
                Text(
                    text = dateTask.description,

                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    color = TextDescriptionColorLight
                )
            }

            // space
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            //  deadline of task
            Text(
                text = "${dateTask.deadline.year} / ${dateTask.deadline.month} / ${dateTask.deadline.day}",
                color = TextMainColorLight,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp)
            )

            //  scope of checkbox
            Surface(
                color = checkBoxColorSelect(BoolInt.intToBool(checkBoxState)),
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 30.dp, height = 60.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    // checkbox
                    Checkbox(
                        checked = BoolInt.intToBool(dateTask.isDone),
                        onCheckedChange = {
                            dateTask.isDone = checkboxChangeState(checkBoxState)
                            checkBoxState = checkboxChangeState(checkBoxState)
                        },
                        Modifier.size(100.dp)
                    )
                }
            }
        }
    }

}

@Composable
fun DeadlineTaskViewBox(deadlineTask: DeadlineTask) {
    if (deadlineTask.subTasks.size != 0) {
        DeadlineTaskWithSubTaskViewBox(deadlineTask = deadlineTask)
    } else {
        DeadlineTaskWithOutSubTaskViewBox(deadlineTask = deadlineTask)
    }
}

@Composable
fun ProgressBar(progress: Float) {

    // تعیین رنگ بر اساس مقدار پیشرفت
    val progressColor = when {
        progress <= 0.2f -> Color.Red
        progress <= 0.4f -> Color(0xFFFFA500) // نارنجی
        progress <= 0.6f -> Color.Yellow
        progress <= 0.8f -> Color.Green
        progress < 1.0f -> Color.Blue
        else -> Color(0xFF7140C7)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .height(8.dp),
            color = progressColor,
            trackColor = Color.Gray,
        )

    }
}

fun changeProgress(progress: Float, done: Boolean): Float {
    return if (done) {
        (progress + 1)
    } else {
        (progress - 1)
    }
}

fun calculateDoneSubTask(task: DeadlineTask): Float {
    var done = 0f
    task.subTasks.forEach { t ->
        if (t.isDone == 1) done++
    }
    return (done)
}

@Composable
private fun DeadlineTaskWithSubTaskViewBox(deadlineTask: DeadlineTask) {

    var progressInt by remember { mutableFloatStateOf(calculateDoneSubTask(deadlineTask)) }
    var progressPercent = (progressInt / deadlineTask.subTasks.size)
    var checkboxState by remember { mutableIntStateOf(deadlineTask.isDone) }

// box top of screen show deadline task
    Surface(
        modifier = Modifier
            .padding(
                start = 0.dp,
                top = 15.dp
            )
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp))
    ) {
        Column {

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
            ) {
                // task color box
                Surface(
                    color = deadLinedTaskColor,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(
                                width = 15.dp,
                                height = (90 + (50 * deadlineTask.subTasks.size)).dp
                            )
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)

                        )
                    }
                }

                Column {
                    Row {
                        // task and sub task title
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 5.dp)
                                .clickable(onClick = {
                                    TODO("وقتی میزنی باید بتونی تسک اصلی رو ویرایش کنی")
                                })
                        ) {

                            // task title
                            Text(
                                text = deadlineTask.title,
                                color = taskTitleTextColor(checkboxState),
                                fontSize = 20.sp,
                                modifier = taskTitleTextModifier(checkboxState)
                            )

                            // space between title and description
                            Spacer(modifier = Modifier.size(2.dp))

                            // description
                            Text(
                                text = deadlineTask.description,

                                modifier = Modifier
                                    .padding(horizontal = 10.dp),
                                color = TextDescriptionColorLight
                            )

                            Spacer(modifier = Modifier.size(20.dp))

                        }

                        // space
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                        )

                        //  deadline of task
                        Text(
                            text = "${deadlineTask.deadline.year} / ${deadlineTask.deadline.month} / ${deadlineTask.deadline.day}",
                            color = TextMainColorLight,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp)
                        )

                        // checkbox scope
                        Surface(
                            color = checkBoxColorSelect(BoolInt.intToBool(checkboxState)),
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(width = 30.dp, height = 60.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                // checkbox
                                Checkbox(
                                    checked = BoolInt.intToBool(checkboxState),
                                    onCheckedChange = {

                                    },
                                    Modifier.size(100.dp)
                                )
                            }
                        }
                    }


                    ProgressBar(progress = progressPercent)


                    deadlineTask.subTasks.forEachIndexed() { index, subTask ->

                        var subTaskState by remember { mutableIntStateOf(subTask.isDone) }

                        Row {

                            Spacer(modifier = Modifier.size(30.dp))

                            // sub task title
                            Text(
                                text = "${index + 1} . ${subTask.title}",
                                color = taskTitleTextColor(subTask.isDone),
                                fontSize = 20.sp,
                                modifier = taskTitleTextModifier(subTaskState)
                                    .clickable(onClick = {
                                        TODO("قابلیت ویرایش زیر تسک ها")
                                    })
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Checkbox(
                                checked = BoolInt.intToBool(subTaskState),
                                onCheckedChange = {
                                    subTask.isDone = checkboxChangeState(subTaskState)
                                    subTaskState = subTask.isDone
                                    progressInt = changeProgress(progressInt, it)
                                    val cbs = deadlineTask.subTasks.size.toFloat() == progressInt
                                    checkboxState = BoolInt.boolToInt(cbs)
                                }
                            )
                        }
                    }


                }
            }
        }
    }
}

@Composable
private fun DeadlineTaskWithOutSubTaskViewBox(deadlineTask: DeadlineTask) {

    var checkBoxState by remember {
        mutableIntStateOf(deadlineTask.isDone)
    }

    // task box
    Surface(
        modifier = Modifier
            .padding(
                start = 0.dp,
                top = 15.dp
            )
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
        ) {
            // task color box
            Surface(
                color = deadLinedTaskColor,
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 15.dp, height = 60.dp)
                        .align(Alignment.CenterVertically)
                ) {

                    Text(
                        text = "",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)

                    )
                }
            }


            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
            ) {
                // task title
                Text(
                    text = deadlineTask.title,
                    color = taskTitleTextColor(checkBoxState),
                    fontSize = 20.sp,
                    modifier = taskTitleTextModifier(checkBoxState)
                        .clickable(onClick = {
//                            TODO("قابلیت ویرایش تسک اصلی")
                        })
                )

                // space between title and description
                Spacer(modifier = Modifier.size(2.dp))

                // description
                Text(
                    text = deadlineTask.description,

                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    color = TextDescriptionColorLight
                )
            }

            // space
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            //  deadline of task
            Text(
                text = "${deadlineTask.deadline.year} / ${deadlineTask.deadline.month} / ${deadlineTask.deadline.day}",
                color = TextMainColorLight,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp)
            )

            //  scope of checkbox
            Surface(
                color = checkBoxColorSelect(BoolInt.intToBool(checkBoxState)),
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 30.dp, height = 60.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    // checkbox
                    Checkbox(
                        checked = BoolInt.intToBool(deadlineTask.isDone),
                        onCheckedChange = {
                            deadlineTask.isDone = checkboxChangeState(checkBoxState)
                            checkBoxState = checkboxChangeState(checkBoxState)
                        },
                        Modifier.size(100.dp)
                    )
                }
            }
        }
    }

}

// box top of screen show date task
@Composable
fun HabitViewBox(habit: DailyHabit) {

    var checkBoxState by remember { mutableIntStateOf(habit.isDone) }

    // task box
    Surface(
        modifier = Modifier
            .padding(
                start = 0.dp,
                top = 15.dp
            )
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        ) {
            // task color box

            Column(
                modifier = Modifier
                    .background(
                        color = habitTaskColor,
                    )
                    .align(Alignment.CenterVertically)
                    .padding(5.dp)
                    .size(width = 15.dp, height = 60.dp)
            ) {

                Text(
                    text = "",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)

                )
            }


            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
            ) {
                // task title
                Text(
                    text = habit.title,
                    color = habitTitleTextColor(checkBoxState),
                    fontSize = 20.sp,
                    modifier = habitTitleTextModifier(checkBoxState)
                )

                // space between title and description
                Spacer(modifier = Modifier.size(2.dp))

                // description
                Text(
                    text = habit.description,

                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    color = TextDescriptionColorLight
                )
            }

            // space
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            //  scope of checkbox
            Column(
                modifier = Modifier
                    .size(width = 40.dp, height = 70.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // checkbox
                MultiStateCheckbox(
                    state = checkBoxState,
                    onCheckedChange = {
                        checkBoxState = ((checkBoxState + 1) % 4)
                    }
                )

            }
        }

    }
}


@Composable
fun TaskViewBox(tasks: List<Task>) {

    tasks.forEach { task ->
        when (task) {
            is SimpleTask -> SimpleTaskViewBox(task)
            is DateTask -> DateTaskViewBox(task)
            is DeadlineTask -> DeadlineTaskViewBox(task)
            is DailyHabit -> HabitViewBox(task)
        }
    }
}
