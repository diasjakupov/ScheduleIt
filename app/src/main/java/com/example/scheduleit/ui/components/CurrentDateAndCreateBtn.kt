package com.example.scheduleit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun CreateBtn(currentDate: String, navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 48.dp,
                end = 48.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            currentDate, style = TextStyle(
                fontWeight = FontWeight(500),
                fontSize = 24.sp
            )
        )
        Button(
            onClick = { navHostController.navigate("create_dialog") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Aqua)
        ) {
            Text("Create task", style = TextStyle(color = MaterialTheme.colors.primary))
        }
    }
}


@Composable
@Preview
fun CreateBtnPreview() {
    ScheduleItTheme() {
        CreateBtn(currentDate = "23 May 2018", rememberNavController())

    }
}