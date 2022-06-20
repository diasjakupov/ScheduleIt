package com.example.scheduleit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldValidator(
    isValid: Boolean ,content: @Composable ()->Unit
) {
    Column() {
        content()
        if (!isValid) {
            Text(
                text = "This field should be filled",
                modifier = Modifier.padding(top = 8.dp),
                style = TextStyle(fontSize = 16.sp, color = Red)
            )
        }
    }
}