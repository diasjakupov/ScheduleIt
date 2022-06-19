package com.example.scheduleit.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.ui.theme.ScheduleItTheme

@Composable
fun TopBar() {
    Surface() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "TDL", style = TextStyle(
                        fontSize = 24.sp, fontWeight = FontWeight(900)
                    )
                )
            }

            TabsRow()
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
