package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naufalhilal.healthifyapp.ui.route.Screen
import com.naufalhilal.healthifyapp.ui.screen.LoginScreen
import com.naufalhilal.healthifyapp.ui.screen.MainScreen
import com.naufalhilal.healthifyapp.ui.screen.RegisterScreen
import com.naufalhilal.healthifyapp.ui.screen.SplashScreen

@Composable
fun StartNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreenRoute.route
    ) {
        composable(Screen.SplashScreenRoute.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreenRoute.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreenRoute.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.MainScreenRoute.route) {
            MainScreen()
        }
    }
}
