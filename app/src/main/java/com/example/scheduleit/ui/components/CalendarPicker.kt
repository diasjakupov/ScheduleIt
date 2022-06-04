package com.example.scheduleit.ui.components

import android.widget.CalendarView
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun CalendarPicker(onDismiss: ()->Unit) {
    Dialog(onDismissRequest = {onDismiss()}) {
        Surface() {
            AndroidView(factory = { context ->
                CalendarView(context)
            })
        }
    }
}


@Composable
@Preview
fun CalendarPickerPreview() {
    ScheduleItTheme {
        CalendarPicker(){}
    }
}