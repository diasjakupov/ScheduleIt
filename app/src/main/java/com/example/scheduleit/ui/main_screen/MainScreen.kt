package com.example.scheduleit.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun MainScreen() {
    Surface() {
        Column(
            modifier = Modifier.padding(
                top = 12.dp,
                bottom = 12.dp,
                start = 48.dp,
                end = 48.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Daily View", style = TextStyle(
                        fontWeight = FontWeight(700),
                        fontSize = 20.sp
                    )
                )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Aqua)
                ) {
                    Text("Create task")
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )
            Text(
                "22 May, 2022", style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 26.sp
                )
            )

        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    ScheduleItTheme() {
        MainScreen()

    }
}

