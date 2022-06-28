package com.example.scheduleit.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scheduleit.data.viewModels.MainScreenViewModel
import com.example.scheduleit.ui.components.CreateBtn
import com.example.scheduleit.ui.create_dialog.CreateDialog
import com.example.scheduleit.ui.detail.DetailDialog
import com.example.scheduleit.ui.theme.ExtremeLightGrey
import com.example.scheduleit.ui.theme.ScheduleItTheme

@ExperimentalComposeUiApi
@Composable
fun MainScreen(VM: MainScreenViewModel = hiltViewModel(), navController: NavController) {
    Surface() {
        Column(
            modifier = Modifier.padding(
                top = 12.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(modifier = Modifier.padding(start = 48.dp, end = 48.dp, bottom = 18.dp)) {
                CreateBtn(format = "MMMM d, y", textStyle = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 30.sp
                ), horizontal = Arrangement.SpaceBetween, VM = hiltViewModel<MainScreenViewModel>()) {
                    navController.navigate("create_dialog")
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                )
            }
            Surface(color = ExtremeLightGrey) {
                Box(modifier = Modifier.padding(start = 48.dp, end = 48.dp)){
                    ItemList(VM, top = 18.dp, onClick = {
                        navController.navigate("detail_dialog/${it}")
                    })
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
        MainScreen(viewModel(), rememberNavController())

    }
}

