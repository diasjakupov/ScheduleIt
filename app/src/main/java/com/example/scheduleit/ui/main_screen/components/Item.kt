package com.example.scheduleit.ui.main_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.data.models.TaskForView
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun TaskItem(taskForView: TaskForView) {
    Surface(elevation = 2.dp, shape = RoundedCornerShape(6.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)

        ) {
            Text(taskForView.date)
            Text(taskForView.name,style= TextStyle(
                fontWeight = FontWeight(600),
                fontSize = 18.sp
            ) ,modifier = Modifier.padding(start = 18.dp))
        }
    }
}


@Composable
@Preview
fun TaskItemPreview() {
    ScheduleItTheme {
        TaskItem(TaskForView("Meeting conference", "1"))
    }
}