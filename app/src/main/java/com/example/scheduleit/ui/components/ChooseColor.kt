package com.example.scheduleit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme


@Composable
fun ChooseColor() {
    Column() {
        Text(
            "Choose color:", style = TextStyle(
                fontWeight = FontWeight(500),
                fontSize = 18.sp
            ), modifier = Modifier.padding(bottom = 16.dp)
        )
        Surface(elevation = 0.3.dp) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(48.dp).padding(8.dp)) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .background(Aqua)
                    .fillMaxSize())
            }
        }
    }
}


@Composable
@Preview
fun ChooseColorPreview(){
    ScheduleItTheme {
        ChooseColor()
    }
}