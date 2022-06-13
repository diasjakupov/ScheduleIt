package com.example.scheduleit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.scheduleit.ui.navigation.NavigationComposable
import com.example.scheduleit.ui.navigation.TopBar
import com.example.scheduleit.ui.theme.ScheduleItTheme


@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            ScheduleItTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(topBar = {
                    TopBar()
                }) {
                    NavigationComposable(navController = navHostController)
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewActivity() {
    TopBar()
    NavigationComposable(navController = rememberNavController())
}
