package com.example.scheduleit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scheduleit.data.viewModels.MainScreenViewModel
import com.example.scheduleit.ui.main_screen.MainScreen


@ExperimentalComposeUiApi
@Composable
fun NavigationComposable(navController: NavHostController){
    NavHost(navController = navController, startDestination = "main_screen"){
        composable("main_screen"){
            MainScreen()
        }
    }
}