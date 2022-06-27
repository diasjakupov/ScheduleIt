package com.example.scheduleit.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme


@Composable
fun CreateBtn(currentDate: String, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 48.dp,
                end = 48.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            currentDate, style = TextStyle(
                fontWeight = FontWeight(500),
                fontSize = 30.sp
            )
        )
        Surface(
        ) {
            IconButton(onClick = { onClick() }, modifier = Modifier.padding(2.dp)) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "calendar icon",
                    modifier = Modifier

                )
            }


        }
    }
}


@Composable
@Preview
fun CreateBtnPreview() {
    ScheduleItTheme() {
        CreateBtn(currentDate = "23 May 2018") {}

    }
}