package com.example.scheduleit.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.data.viewModels.DetailViewModel
import com.example.scheduleit.ui.components.DateTimeBlock
import com.example.scheduleit.ui.create_dialog.components.CalendarPicker
import com.example.scheduleit.ui.create_dialog.components.NotificationDropDownMenu
import com.example.scheduleit.ui.theme.Aqua

@Composable
fun DateTimeEditBlock(
    VM: CreationFormViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onCancel: () -> Unit
) {

    val isCalendarShown = remember {
        mutableStateOf(false)
    }
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Change time and date")
                    Spacer(modifier = Modifier.height(12.dp))
                    DateTimeBlock(dataHolder = VM.formattedPickedDate.value) {
                        isCalendarShown.value = true
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row() {
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth(0.5f),
                        shape = RectangleShape,
                        contentPadding = PaddingValues(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Aqua,
                            contentColor = Color.White
                        )
                    ) {
                        Text("save".uppercase())
                    }
                    Button(
                        onClick = { onCancel() },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        contentPadding = PaddingValues(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text("cancel".uppercase())
                    }
                }
            }

        }

    }

    if (isCalendarShown.value) {
        CalendarPicker(hiltViewModel<CreationFormViewModel>()) {
            isCalendarShown.value = false
        }
    }
}