package com.example.scheduleit.ui.month_screen

import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MonthScreen() {
    Surface() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                top = 12.dp
            ).fillMaxWidth(),
        ) {
            AndroidView(factory = { context ->
                val view = CalendarView(context)
                //TODO change the appearance of the day view
                view
            })
        }
    }

}