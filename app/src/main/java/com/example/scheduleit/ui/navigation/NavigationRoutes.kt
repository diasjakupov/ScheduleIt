package com.example.scheduleit.ui.navigation

sealed class NavigationRoutes(val route:String){
    object DailyScreen: NavigationRoutes("daily_screen")
    object MonthScreen: NavigationRoutes("month_screen")
    object DetailScreenDialog: NavigationRoutes("detail_dialog"){
        fun withArgs(arg: Int):String{
            return "${this.route}/${arg}"
        }
    }
    object CreateScreenDialog: NavigationRoutes("create_dialog")
}
