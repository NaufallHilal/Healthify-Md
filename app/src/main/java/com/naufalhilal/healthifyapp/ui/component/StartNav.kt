package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naufalhilal.healthifyapp.ui.route.Screen
import com.naufalhilal.healthifyapp.ui.screen.LandingPage
import com.naufalhilal.healthifyapp.ui.screen.LandingScreen
import com.naufalhilal.healthifyapp.ui.screen.LoginScreen
import com.naufalhilal.healthifyapp.ui.screen.MainScreen
import com.naufalhilal.healthifyapp.ui.screen.RegisterScreen
import com.naufalhilal.healthifyapp.ui.screen.SplashScreen

@Composable
fun StartNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreenRoute.route
    ) {
        composable(Screen.SplashScreenRoute.route) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(Screen.LandingScreenRoute.route)
            }
        }
        composable(Screen.LandingScreenRoute.route) {
            LandingScreen(
                navController = navController,
                landingPageRoute = Screen.LoginScreenRoute.route,
            )
        }
        composable(Screen.LandingPage.route) {
            LandingPage(
                navigateToLogin = { navController.navigate(Screen.LoginScreenRoute.route) },
                navigateToRegister = { navController.navigate(Screen.RegisterScreenRoute.route) })
        }
        composable(Screen.LoginScreenRoute.route) {
            LoginScreen(
                navController = navController,
                homeRoute = Screen.MainScreenRoute.route,
                registerRoute = Screen.RegisterScreenRoute.route
            )
        }
        composable(Screen.RegisterScreenRoute.route) {
            RegisterScreen(navigateToLogin = { navController.navigate(Screen.LoginScreenRoute.route) })
        }
        composable(Screen.MainScreenRoute.route) {
            MainScreen()
        }

    }
}
