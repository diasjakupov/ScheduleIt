package com.example.scheduleit.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskHeader(title: String, textStyle: TextStyle, modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title, style = textStyle, modifier = modifier
        )
        Divider(
            modifier = Modifier
                .padding(top = 8.dp, start = 12.dp),
            thickness = 2.dp
        )
    }
}