package com.example.scheduleit.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.ui.wrappers.TaskForView
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun TaskItem(taskForView: TaskForView, onClick: ()->Unit) {
    Surface(elevation = 2.dp, shape = RoundedCornerShape(6.dp), modifier = Modifier.clickable {
        onClick()
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)

        ) {
            Text(taskForView.time)
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
        TaskItem(TaskForView(0,"Meeting conference", "1")){}
    }
}