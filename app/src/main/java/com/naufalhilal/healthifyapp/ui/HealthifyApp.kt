package com.naufalhilal.healthifyapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.naufalhilal.healthifyapp.ui.screen.splash_screen.SplashScreen
import com.naufalhilal.healthifyapp.ui.navigation.Screen
import com.naufalhilal.healthifyapp.ui.screen.addFood.AddFoodScreen
import com.naufalhilal.healthifyapp.ui.screen.createFood.CreateFoodScreen
import com.naufalhilal.healthifyapp.ui.screen.dairyActivity.DairyScreen
import com.naufalhilal.healthifyapp.ui.screen.home.HomeScreen
import com.naufalhilal.healthifyapp.ui.screen.login.LoginScreen
import com.naufalhilal.healthifyapp.ui.screen.register.RegisterScreen
import com.naufalhilal.healthifyapp.ui.screen.splash_screen.LandingPage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthifyApp(
    modifier: Modifier=Modifier,
    navController: NavHostController= rememberNavController(),
){
    Scaffold(
        bottomBar = {

        },
        modifier = modifier,
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route,
            modifier = Modifier.padding(innerPadding),
            ){
            composable(Screen.SplashScreen.route){
                SplashScreen{
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            }
            composable(Screen.Home.route){
                HomeScreen(
                    navController = navController,
                    landingPageRoute = Screen.Login.route,
                )
            }
            composable(Screen.Login.route){
                LoginScreen(
                    navController = navController,
                    homeRoute = Screen.Home.route,
                    registerRoute = Screen.Register.route
                )
            }
            composable(Screen.Register.route){
                RegisterScreen(navigateToLogin = {navController.navigate(Screen.Login.route)})
            }
            composable(Screen.LandingPage.route){
                LandingPage(navigateToLogin = { navController.navigate(Screen.Login.route)}, navigateToRegister = {navController.navigate(Screen.Register.route)} )
            }
            composable(Screen.Diary.route){
                DairyScreen(navigateToAddFood = {eatTime,diaryId->navController.navigate(Screen.AddFood.createRoute(eatTime,diaryId))})
            }
            composable(route = Screen.AddFood.route, arguments = listOf(navArgument("eatTime"){type=
                NavType.StringType}, navArgument("diaryId"){type= NavType.IntType})){
                val eatTime= it.arguments?.getString("eatTime")?: "Pilih Makanan"
                val diaryId = it.arguments?.getInt("diaryId")?: 0
                AddFoodScreen(eatTime = eatTime,diaryId=diaryId, navigateToCreateFood = {
                    navController.navigate(
                        Screen.CreateFood.route
                    )
                })
            }
            composable(route=Screen.CreateFood.route){
                CreateFoodScreen(navigateBack = {navController.navigateUp()})
            }
        }
    }
}