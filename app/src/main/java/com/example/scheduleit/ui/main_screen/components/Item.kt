package com.example.scheduleit.ui.main_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun TaskItem() {
    Surface() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(1.dp, Color.DarkGray)) {
            Text("10:00")
            Text("Meeting conference", modifier = Modifier.padding(start = 18.dp))
        }
    }
}


@Composable
@Preview
fun TaskItemPreview() {
    ScheduleItTheme() {
        LazyColumn(){
            items(10){
                TaskItem()
            }
        }
    }
}