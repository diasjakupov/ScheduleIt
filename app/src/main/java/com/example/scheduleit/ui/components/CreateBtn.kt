package com.example.scheduleit.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scheduleit.data.viewModels.IGetDateRepresentation
import com.example.scheduleit.data.viewModels.MainScreenViewModel
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme


@Composable
fun CreateBtn(
    format: String,
    textStyle: TextStyle,
    horizontal: Arrangement.Horizontal,
    VM: IGetDateRepresentation,
    reversed: Boolean = false,
    date: Long,
    onClick: () -> Unit
) {

    val currentDate = remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = date, block = {
        currentDate.value = VM.getDateRepresentation(format, date)
    })


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = horizontal,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (reversed) {
            IconButton(onClick = {
                onClick()
            }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "calendar icon",
                    modifier = Modifier
                )
            }
            Text(
                currentDate.value, style = textStyle
            )
        } else {
            Text(
                currentDate.value, style = textStyle
            )
            IconButton(onClick = {
                onClick()
            }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "calendar icon",
                    modifier = Modifier
                )
            }
        }
    }
}


@Composable
@Preview
fun CreateBtnPreview() {
    ScheduleItTheme() {
        CreateBtn(
            format = "",
            textStyle = TextStyle(),
            horizontal = Arrangement.Start,
            VM = hiltViewModel(), date = 0L
        ) {}
    }
}