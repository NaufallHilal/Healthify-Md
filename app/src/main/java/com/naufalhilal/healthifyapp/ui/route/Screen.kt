package com.naufalhilal.healthifyapp.ui.route

sealed class Screen(val route: String) {
    data object SplashScreenRoute : Screen("splash")
    data object LoginScreenRoute : Screen("login")
    data object RegisterScreenRoute : Screen("register")
    data object MainScreenRoute : Screen("main")
    data object EditProfileRoute : Screen("edit_profile")
    data object Goal : Screen("goal")
    data object Food : Screen("food")
    data object Exercise : Screen("exercise")
}
