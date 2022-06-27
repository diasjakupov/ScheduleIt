package com.example.scheduleit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.data.viewModels.MainScreenViewModel
import com.example.scheduleit.ui.create_dialog.CreateDialog
import com.example.scheduleit.ui.create_dialog.components.CalendarPicker
import com.example.scheduleit.ui.detail.DetailDialog
import com.example.scheduleit.ui.main_screen.MainScreen


@ExperimentalComposeUiApi
@Composable
fun NavigationComposable(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            MainScreen(navController = navController)
        }

        dialog(
            route = "detail_dialog/{taskId}",
            arguments = listOf(navArgument("taskId") {
                type = NavType.IntType
            })
        ) {
            DetailDialog(it.arguments!!.getInt("taskId")) {
                navController.navigate("main_screen")
            }
        }
        dialog("create_dialog") {
            val creationViewModel = hiltViewModel<CreationFormViewModel>()
            CreateDialog(VM = creationViewModel) {
                navController.navigate("main_screen")
            }
        }
    }
}