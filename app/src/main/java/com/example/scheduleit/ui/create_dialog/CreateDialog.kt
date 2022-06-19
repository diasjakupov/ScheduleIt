package com.example.scheduleit.ui.create_dialog

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.ui.components.CalendarPicker
import com.example.scheduleit.ui.components.ChooseColor
import com.example.scheduleit.ui.components.CustomTextField
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme


@ExperimentalComposeUiApi
@Composable
fun CreateDialog(VM: CreationFormViewModel = viewModel(), onDismissRequest: () -> Unit) {
    //reset initial data in VM
    LaunchedEffect(true){
        VM.reset()
    }

    val isCalendarShown = remember {
        mutableStateOf(false)
    }

    //showing calendar dialog if it is necessary
    if (isCalendarShown.value) {
        CalendarPicker(VM) {
            isCalendarShown.value = false
        }
    }

    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.95f)
                .fillMaxWidth(0.9f)
        ) {
            //TODO(apply logic and implement date picker)
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Column(
                    modifier = Modifier.padding(
                        start = 26.dp,
                        top = 32.dp,
                        end = 26.dp,
                        bottom = 32.dp
                    )
                ) {
                    //Header
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Create New Tasks", style = TextStyle(
                                fontWeight = FontWeight(700),
                                fontSize = 26.sp
                            )
                        )
                        Divider(
                            modifier = Modifier
                                .padding(top = 8.dp, start = 12.dp),
                            thickness = 2.dp
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    //Body
                    Column() {
                        CustomTextField(
                            value = VM.title.value,
                            placeholder = "Write Topic",
                            isLabelShown = true,
                            isUnderlined = true,
                            label = "Topic",
                            onValueChange = {
                                VM.setNewTitle(it)
                            })

                        Spacer(modifier = Modifier.height(32.dp))

                        CustomTextField(
                            value = VM.desc.value,
                            placeholder = "Write Description",
                            label = "Description",
                            isLabelShown = true,
                            isUnderlined = true,
                            onValueChange = {
                                VM.setNewDesc(it)
                            })

                        Spacer(modifier = Modifier.height(48.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            CustomTextField(
                                value = "",
                                placeholder = "Day",
                                isUnderlined = true,
                                readOnly = true,
                                decorationBoxModifier = Modifier
                                    .width(60.dp)
                                    .clickable {
                                        isCalendarShown.value = true
                                    })
                            CustomTextField(
                                value = "",
                                placeholder = "Month",
                                isUnderlined = true,
                                readOnly = true,
                                decorationBoxModifier = Modifier
                                    .width(60.dp)
                                    .clickable {
                                        isCalendarShown.value = true
                                    })
                            CustomTextField(
                                value = "",
                                placeholder = "Year",
                                isUnderlined = true,
                                readOnly = true,
                                decorationBoxModifier = Modifier
                                    .width(60.dp)
                                    .clickable {
                                        isCalendarShown.value = true
                                    })
                        }

                        Spacer(modifier = Modifier.height(48.dp))
                        //TODO(change value to list picker)
                        CustomTextField(
                            value = "10 min before",
                            readOnly = true,
                            isUnderlined = true,
                            label = "Notification",
                            isLabelShown = true,
                            onValueChange = {})

                        Spacer(modifier = Modifier.height(48.dp))

                        ChooseColor()
                    }

                }
                //Footer
                Button(
                    onClick = { VM.submit() },
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Aqua,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        "ADD", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@Composable
@Preview
fun CreateDialogPreview() {
    ScheduleItTheme() {
        CreateDialog() {}
    }
}