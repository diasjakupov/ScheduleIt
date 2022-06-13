package com.example.scheduleit.ui.components

import android.widget.NumberPicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun TimePicker(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(shape = RoundedCornerShape(12.dp)) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AndroidView(factory = { context ->
                        val hours = NumberPicker(context)
                        hours.minValue = 0
                        hours.maxValue = 23
                        hours.setFormatter {
                            String.format("%02d", it)
                        }

                        hours
                    }, modifier = Modifier.fillMaxWidth(0.5f))
                    AndroidView(factory = { context ->
                        val minutes = NumberPicker(context)
                        minutes.maxValue = 59
                        minutes.minValue = 0
                        minutes.setFormatter {
                            String.format("%02d", it)
                        }
                        minutes
                    }, modifier = Modifier.fillMaxWidth())
                }
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(end = 12.dp)
                        .clip(CircleShape)
                        .clickable { onDismiss() }
                        .padding(8.dp)
                        ) {
                    Text(text = "OK")
                }
            }

        }
    }
}


@Composable
@Preview
fun TimePickerPreview() {
    ScheduleItTheme() {
        TimePicker() {}
    }
}