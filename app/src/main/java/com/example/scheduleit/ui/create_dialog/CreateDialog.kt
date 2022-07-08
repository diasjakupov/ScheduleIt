package com.example.scheduleit.ui.create_dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.ui.components.*
import com.example.scheduleit.ui.create_dialog.components.CalendarPicker
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateDialog(VM: CreationFormViewModel = hiltViewModel(), onDismissRequest: () -> Unit) {
    //reset initial data in VM
    LaunchedEffect(true) {
        VM.reset()
    }

    val isCalendarShown = remember {
        mutableStateOf(false)
    }

    val isValid = remember {
        mutableStateOf(true)
    }

    Dialog(
        onDismissRequest = {
            onDismissRequest()
        }, properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(0.9f)
        ) {
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
                    TaskHeader(title = "Create New Tasks", textStyle = TextStyle(
                        fontWeight = FontWeight(700),
                        fontSize = 26.sp
                    ))
                    Spacer(modifier = Modifier.height(24.dp))
                    //Body
                    Column {
                        TextFieldValidator(
                            value = VM.title.value,
                            validationError = "This field should be filled"
                        ) {

                            CustomTextField(
                                value = VM.title.value ?: "",
                                placeholder = "Write Topic",
                                isLabelShown = true,

                                label = "Topic",
                                onValueChange = { newValue ->
                                    isValid.value = true
                                    VM.setNewTitle(newValue)
                                })

                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        CustomTextField(
                            value = VM.desc.value,
                            placeholder = "Write Description",
                            label = "Description",
                            isLabelShown = true,
                            onValueChange = {
                                VM.setNewDesc(it)
                            })

                        Spacer(modifier = Modifier.height(48.dp))

                        DateTimeBlock(dataHolder = VM.formattedPickedDate.value){
                            isCalendarShown.value = true
                        }

                        Spacer(modifier = Modifier.height(48.dp))
                    }

                }
                //Footer
                Button(
                    onClick = {
                        isValid.value = VM.submitNewTask()
                        onDismissRequest()
                    },
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

                //showing calendar dialog if it is necessary
                if (isCalendarShown.value) {
                    CalendarPicker(VM) {
                        isCalendarShown.value = false
                    }
                }
            }
        }
    }
    //TODO add error on validation check

}


@ExperimentalComposeUiApi
@Composable
@Preview
fun CreateDialogPreview() {
    ScheduleItTheme() {
        CreateDialog() {}
    }
}