package com.example.scheduleit.ui.navigation

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scheduleit.ui.theme.Aqua

@Composable
fun TabsRow() {
    var selectedTabIndex by remember {
        mutableStateOf(1)
    }

    val tabs by remember {
        mutableStateOf(arrayOf("Monthly", "Daily"))
    }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(top = 8.dp, start = 48.dp, end = 48.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(50.dp)),
        indicator = {},
        divider = {}

    ) {
        tabs.forEachIndexed { index, text ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                modifier = if (selectedTabIndex == index) {
                    Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Aqua)
                        .padding(top = 12.dp, bottom = 12.dp)

                } else {
                    Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(top = 12.dp, bottom = 12.dp)

                }
            ) {
                Text(
                    text = text, color = if (selectedTabIndex == index) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
            }
        }
    }

}


@Composable
@Preview
fun TabsRowPreview() {
    TabsRow()
}