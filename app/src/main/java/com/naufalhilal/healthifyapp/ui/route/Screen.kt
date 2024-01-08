package com.naufalhilal.healthifyapp.ui.route

sealed class Screen(val route: String) {
    data object SplashScreenRoute : Screen("splashScreen")
    data object LoginScreenRoute : Screen("login")
    data object RegisterScreenRoute : Screen("register")
    data object MainScreenRoute : Screen("main")
    object LandingPage : Screen("landingPage")
    object LandingScreenRoute : Screen("landingScreen")
    data object EditProfileRoute : Screen("edit_profile")
    data object GoalRoute : Screen("goal")
    data object FoodRoute : Screen("food")
    data object ExerciseRoute : Screen("exercise")
    object AddFood : Screen("addFood/{eatTime}/{diaryId}") {
        fun createRoute(eatTime: String, diaryId: Int) = "addFood/$eatTime/$diaryId"
    }

    object CreateFood : Screen("createFood")
}
