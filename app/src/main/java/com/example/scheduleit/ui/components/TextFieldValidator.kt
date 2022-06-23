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
    value:String?, validationError:String, content: @Composable ()->Unit
) {
    Column() {
        content()
        if ((value != null) && value.isEmpty()) {
            Text(
                text = validationError,
                modifier = Modifier.padding(top = 8.dp),
                style = TextStyle(fontSize = 16.sp, color = Red)
            )
        }
    }
}