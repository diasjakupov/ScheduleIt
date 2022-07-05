package com.example.scheduleit.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scheduleit.ui.create_dialog.components.DateField
import com.example.scheduleit.ui.create_dialog.components.NotificationDropDownMenu
import com.example.scheduleit.ui.wrappers.CalendarDateFormat

@Composable
fun DateTimeBlock(dataHolder: CalendarDateFormat, onDateClick: ()->Unit){
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DateField(
                value = dataHolder.day.toString(),
                placeholder = "Day",
                modifier = Modifier
                    .width(60.dp)
                    .clickable {
                        onDateClick()
                    })
            DateField(
                value = dataHolder.monthName,
                placeholder = "Month",
                modifier = Modifier
                    .width(80.dp)
                    .clickable {
                        onDateClick()
                    })
            DateField(
                value = dataHolder.year.toString(),
                placeholder = "Year",
                modifier = Modifier
                    .width(100.dp)
                    .clickable {
                        onDateClick()
                    })
        }

        Spacer(modifier = Modifier.height(48.dp))
        NotificationDropDownMenu()
    }
}