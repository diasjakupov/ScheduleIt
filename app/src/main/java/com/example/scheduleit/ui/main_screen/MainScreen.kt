package com.example.scheduleit.ui.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scheduleit.ui.components.CreateBtn
import com.example.scheduleit.ui.create_dialog.CreateDialog
import com.example.scheduleit.ui.theme.ScheduleItTheme

@ExperimentalComposeUiApi
@Composable
fun MainScreen() {
    var isShown by remember {
        mutableStateOf(false)
    }


    Surface() {
        Column(
            modifier = Modifier.padding(
                top = 12.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CreateBtn(currentDate = "23 May 2022"){
                isShown = true
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            )
            ItemList(PaddingValues(start = 48.dp, end = 48.dp, top = 18.dp))
            if (isShown){
                CreateDialog() {
                    isShown = false
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun MainScreenPreview() {
    ScheduleItTheme() {
        MainScreen()

    }
}

