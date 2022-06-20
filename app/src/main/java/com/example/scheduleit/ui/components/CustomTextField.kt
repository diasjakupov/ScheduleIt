package com.example.scheduleit.ui.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    placeholder: String = "",
    isLabelShown: Boolean = false,
    label: String = "",

    onValueChange: (String) -> Unit = {},
) {
    Column() {
        if (isLabelShown) {
            Text(
                label, style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 18.sp
                ), modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            decorationBox = {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }
                it()
            }, modifier = Modifier.fillMaxWidth())

        Divider()

    }
}


@Composable
@Preview
fun CustomTextFieldPreview() {
    Surface() {
        CustomTextField(
            value = "Hello world",
            placeholder = "Topic",
            isLabelShown = true,
            label = "Topic",
            onValueChange = {})
    }

}