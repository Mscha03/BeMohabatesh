package com.example.bemohabatesh.util.navigationdrawer

import android.content.Context
import androidx.compose.ui.platform.LocalContext

fun changeActivity(context: Context,thisClass: Class<*>, destinationClass: Class<*>){
    if (thisClass != destinationClass){
        val intent = android.content.Intent(context, destinationClass)
        context.startActivity(intent)
    }
}