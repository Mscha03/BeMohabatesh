package com.example.bemohabatesh.util.view

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.bemohabatesh.ui.theme.CheckboxDoneColor
import com.example.bemohabatesh.ui.theme.CheckboxUnDoneColor
import com.example.bemohabatesh.ui.theme.TextDescriptionColor
import com.example.bemohabatesh.ui.theme.TextMainColor
import com.example.bemohabatesh.ui.theme.habitAverageColor
import com.example.bemohabatesh.ui.theme.habitBadColor
import com.example.bemohabatesh.ui.theme.habitErrorColor
import com.example.bemohabatesh.ui.theme.habitGoodColor
import com.example.bemohabatesh.ui.theme.habitUnDoneColor
import com.example.bemohabatesh.util.convert.BoolInt

fun checkBoxColorSelect(isDone: Boolean): Color {
    return if (isDone)  CheckboxDoneColor else CheckboxUnDoneColor
}

fun multiStateCheckBoxColorSelect(isDone: Int): Color {
    return when (isDone) {
        0 -> habitUnDoneColor
        1 -> habitBadColor
        2 -> habitAverageColor
        3 -> habitGoodColor
        else -> habitErrorColor
    }
}

fun taskTitleTextModifier(isDone: Int): Modifier {
    return if (BoolInt.intToBool(isDone)) {
        Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .drawBehind {
                drawLine(
                    color = Color.Black,
                    start = Offset(-30f, size.height / 1.7f),
                    end = Offset(size.width + 30f, size.height / 1.7f),
                    strokeWidth = Stroke.DefaultMiter,
                )
            }
    } else {
        Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
    }
}

fun taskTitleTextColor(isDone: Int): Color {
    return if (BoolInt.intToBool(isDone)) {
        TextDescriptionColor
    } else{
        TextMainColor
    }
}

fun habitTitleTextColor(isDone: Int): Color {
 return if (isDone == 3){
     TextDescriptionColor
 }else{
     TextMainColor
 }
}

fun habitTitleTextModifier(isDone: Int): Modifier {
    return if (isDone == 3) {
        Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .drawBehind {
                drawLine(
                    color = Color.Black,
                    start = Offset(-30f, size.height / 1.7f),
                    end = Offset(size.width + 30f, size.height / 1.7f),
                    strokeWidth = Stroke.DefaultMiter,
                )
            }
    } else {
        Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
    }
}