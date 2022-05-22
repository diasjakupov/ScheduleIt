package com.example.scheduleit.ui.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun TopBar() {
    Surface() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "To-Do List", modifier = Modifier.padding(top = 8.dp))
            TabsRow()
            Divider()
        }
    }
}


@Composable
@Preview
fun TopBarPreview() {
    ScheduleItTheme() {
        TopBar()
    }

}
