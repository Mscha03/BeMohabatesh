package com.example.bemohabatesh.ui.view

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bemohabatesh.R
import com.example.bemohabatesh.utils.time.shamsi.ShamsiCalendar



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
                        .padding(horizontal = 16.dp)
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


}















