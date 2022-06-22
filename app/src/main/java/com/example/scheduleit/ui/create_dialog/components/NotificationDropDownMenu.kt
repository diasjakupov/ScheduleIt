package com.example.scheduleit.ui.create_dialog.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.data.models.NotificationDelay
import com.example.scheduleit.data.viewModels.CreationFormViewModel

@Composable
fun NotificationDropDownMenu(VM: CreationFormViewModel = viewModel()) {
    val expanded = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            "Notification", style = TextStyle(
                fontWeight = FontWeight(500),
                fontSize = 18.sp
            ), modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(VM.selectedNotificationDelay.value.first, modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expanded.value = !expanded.value
            })
        Divider()
        if (expanded.value) {
            Surface(elevation = 1.dp, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(2.dp)) {
                    NotificationDelay.NOTIFICATION_DELAY.forEach {
                        Text(
                            it.first, style = TextStyle(
                                fontWeight = FontWeight(600),
                                fontSize = 18.sp
                            ), modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .clickable {
                                    VM.setNewNotificationDelay(it)
                                    expanded.value = !expanded.value
                                }
                        )
                        Divider()
                    }
                }
            }
        }
    }

}
