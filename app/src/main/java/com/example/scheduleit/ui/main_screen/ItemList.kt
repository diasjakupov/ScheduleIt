package com.example.scheduleit.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.scheduleit.data.models.TaskForView
import com.example.scheduleit.ui.main_screen.components.TaskItem
import com.example.scheduleit.ui.theme.ExtremeLightGrey

@Composable
fun ItemList(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .background(ExtremeLightGrey)
            .padding(paddingValues = paddingValues)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // TODO  (change to dynamic)
        items(5) {
            TaskItem(TaskForView("Meeting conference", "10.00"))
        }
    }
}


@Composable
@Preview
fun ItemListPreview(){
    ItemList(PaddingValues(start = 48.dp, end = 48.dp, top = 18.dp))
}