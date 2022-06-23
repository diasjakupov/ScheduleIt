package com.example.scheduleit.ui.components

import android.util.Log
import android.widget.NumberPicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun TimePicker(VM: CreationFormViewModel,onDismiss: () -> Unit) {
    val hours = remember {
        mutableStateOf(0)
    }
    val minutes = remember {
        mutableStateOf(0)
    }
    
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
                        val hourView = NumberPicker(context)
                        hourView.minValue = 0
                        hourView.maxValue = 23
                        hourView.setFormatter {
                            String.format("%02d", it)
                        }
                        hourView.setOnValueChangedListener { _, _, new ->
                            hours.value = new
                        }
                        hourView
                    }, modifier = Modifier.fillMaxWidth(0.5f))
                    AndroidView(factory = { context ->
                        val minutesView = NumberPicker(context)
                        minutesView.maxValue = 59
                        minutesView.minValue = 0
                        minutesView.setFormatter {
                            String.format("%02d", it)
                        }
                        minutesView.setOnValueChangedListener { _, _, new ->
                            minutes.value = new
                        }
                        minutesView
                    }, modifier = Modifier.fillMaxWidth())
                }
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(CircleShape)
                        .clickable {
                            VM.setNewTime(hours.value, minutes.value)
                            onDismiss()
                        }
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
        TimePicker(hiltViewModel()) {}
    }
}