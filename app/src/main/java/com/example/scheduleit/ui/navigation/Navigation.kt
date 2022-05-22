package com.example.scheduleit.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scheduleit.ui.main_screen.MainScreen


@Composable
fun NavigationComposable(navController: NavHostController){
    NavHost(navController = navController, startDestination = "main_screen"){
        composable("main_screen"){
            MainScreen()
        }
    }
}