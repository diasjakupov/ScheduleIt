package com.example.scheduleit.ui.create_dialog.components


import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.ui.theme.ScheduleItTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CalendarPicker(VM: CreationFormViewModel, onDismiss: () -> Unit) {

    val isTimePickerShown = remember {
        mutableStateOf(false)
    }
    val date = remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ) {
        if (isTimePickerShown.value) {
            TimePicker(VM) {
                isTimePickerShown.value = false
            }
        }
        Surface(shape = RoundedCornerShape(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(16.dp)

                ) {
                    Text(
                        "select date".uppercase(), style = TextStyle(
                            fontSize = 18.sp, fontWeight = FontWeight(500)
                        )
                    )
                    Text(
                        date.value, style = TextStyle(
                            fontSize = 28.sp, fontWeight = FontWeight(600)
                        )
                    )
                }
                AndroidView(factory = { context ->
                    val calendarView = CalendarView(context)
                    calendarView.date = VM.oldNewDataHolderDateTime.old.first.value!!
                    calendarView.setOnDateChangeListener { _, year, month, day ->
                        VM.setNewDate(year, month, day)
                    }
                    calendarView
                }, modifier = Modifier.padding())
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        "Time", style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight(400)
                        )
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
                            .padding(4.dp)
                            .clickable {
                                isTimePickerShown.value = true
                            }
                    ) {
                        Text(
                            text = VM.oldNewDataHolderDateTime.new.second.value.ifEmpty {
                                VM.oldNewDataHolderDateTime.old.second.value
                            }, fontSize = 20.sp, fontWeight = FontWeight(400)
                        )

                    }

                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(bottom = 24.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onDismiss()
                                VM.cancelDateTime()
                            }
                            .padding(8.dp)) {
                        Text(
                            "Cancel", style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight(500)
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onDismiss()
                                VM.submitDateTime()
                            }
                            .padding(8.dp)) {
                        Text(
                            "ok".uppercase(), style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight(500)
                            )
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = VM.oldNewDataHolderDateTime.new.first.value, block = {
        if (VM.oldNewDataHolderDateTime.new.first.value == null) {
            date.value = VM.getDateRepresentation(
                "MMMM d, y",
                VM.oldNewDataHolderDateTime.old.first.value!!
            )
        } else {
            date.value = VM.getDateRepresentation(
                "MMMM d, y",
                VM.oldNewDataHolderDateTime.new.first.value!!
            )
        }
    })
}


@ExperimentalComposeUiApi
@Composable
@Preview
fun CalendarPickerPreview() {
    ScheduleItTheme {
        CalendarPicker(viewModel()) {}
    }
}