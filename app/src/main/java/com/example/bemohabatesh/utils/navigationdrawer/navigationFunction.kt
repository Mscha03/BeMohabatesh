package com.example.bemohabatesh.utils.navigationdrawer

import android.content.Context

fun changeActivity(context: Context,thisClass: Class<*>, destinationClass: Class<*>){
    if (thisClass != destinationClass){
        val intent = android.content.Intent(context, destinationClass)
        context.startActivity(intent)
    }
}