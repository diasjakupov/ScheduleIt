package com.example.scheduleit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.data.viewModels.DetailViewModel
import com.example.scheduleit.ui.create_dialog.CreateDialog
import com.example.scheduleit.ui.detail.DetailDialog
import com.example.scheduleit.ui.main_screen.DailyScreen


@ExperimentalComposeUiApi
@Composable
fun NavigationComposable(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoutes.DailyScreen.route) {
        composable(NavigationRoutes.DailyScreen.route) {
            DailyScreen(navController = navController)
        }
        composable(NavigationRoutes.MonthScreen.route){}

        dialog(
            route = NavigationRoutes.DetailScreenDialog.route,
            arguments = listOf(navArgument("taskId") {
                type = NavType.IntType
            })
        ) {
            DetailDialog(
                id = it.arguments?.getInt("taskId") ?: 0,
                detailVM = hiltViewModel<DetailViewModel>(),
                creationVM = hiltViewModel<CreationFormViewModel>()
            ) {
                navController.navigate(NavigationRoutes.DailyScreen.route)
            }
        }
        dialog(NavigationRoutes.CreateScreenDialog.route) {
            CreateDialog(VM = hiltViewModel<CreationFormViewModel>()) {
                navController.navigate(NavigationRoutes.DailyScreen.route)
            }
        }
    }
}