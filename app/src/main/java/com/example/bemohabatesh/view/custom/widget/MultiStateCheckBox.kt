package com.example.bemohabatesh.view.custom.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bemohabatesh.R
import com.example.bemohabatesh.util.view.multiStateCheckBoxColorSelect

@Preview
@Composable
fun MultiStateCheckbox(state : Int = 0, onCheckedChange: (state: Int) -> Unit = {}){

    var checkboxState by remember { mutableIntStateOf(state) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = multiStateCheckBoxColorSelect(checkboxState)
            )
            .clickable(
                onClick = {
                    checkboxState = ((checkboxState + 1) % 4)
                    onCheckedChange(checkboxState)
                }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (checkboxState == 0){
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.5.dp, Color.Black), shape = RoundedCornerShape(2.dp))
                    .size(20.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.baseline_done_24),
                contentDescription = "done",
                modifier = Modifier.size(20.dp),
            )
        }
    }

}