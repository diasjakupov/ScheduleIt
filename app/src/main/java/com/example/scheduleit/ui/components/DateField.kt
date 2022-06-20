package com.example.scheduleit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun DateField(value: String, placeholder: String, modifier: Modifier){
    Column(modifier = modifier) {
        Text(if(value == "0"){
            placeholder
        }else{
            value
        }, style = TextStyle(fontSize = 16.sp))
        Divider()
    }

}