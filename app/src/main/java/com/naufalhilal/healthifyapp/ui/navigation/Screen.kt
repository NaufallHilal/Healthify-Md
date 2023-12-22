package com.naufalhilal.healthifyapp.ui.navigation

sealed class Screen (val route:String) {
    object Home : Screen("home")
    object SplashScreen : Screen("splashScreen")
    object Login : Screen("login")
    object Register : Screen("register")
    object LandingPage : Screen("landingPage")
    object Diary : Screen("diary")
    object AddFood : Screen("addFood/{eatTime}/{diaryId}"){
        fun createRoute(eatTime:String,diaryId:Int)="addFood/$eatTime/$diaryId"
    }
    object CreateFood : Screen("createFood")
}