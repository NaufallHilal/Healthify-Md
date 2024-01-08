package com.naufalhilal.healthifyapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.naufalhilal.healthifyapp.ui.component.BottomNavigationBar
import com.naufalhilal.healthifyapp.ui.component.DrawerContent
import com.naufalhilal.healthifyapp.ui.component.TopBar
import com.naufalhilal.healthifyapp.ui.route.NavigationItem
import com.naufalhilal.healthifyapp.ui.route.Screen
import com.naufalhilal.healthifyapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContent(
                onItemClick = { route ->
                    coroutineScope.launch {
                        drawerState.close()
                        navController.navigate(route)
                    }
                },
                drawerState = drawerState
            )
        },
        content = {
            Scaffold(
                topBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    TopBar(navBackStackEntry = navBackStackEntry, onMenuIconClick = {
                        if (navBackStackEntry?.destination?.route != NavigationItem.Home.route) {
                            navController.navigateUp()
                        } else {
                            coroutineScope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    })
                },
                bottomBar = {
                    BottomNavigationBar(navController)
                },
                content = { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = NavigationItem.Home.route
                    ) {
                        composable(NavigationItem.Home.route) {
                            val viewModel: HomeViewModel = hiltViewModel()
                            HomeScreen(
                                navController = navController,
                                paddingValues = paddingValues,
                                homeViewModel = viewModel
                            )
                        }
                        composable(NavigationItem.Diary.route) {
                            DiaryScreen(navigateToAddFood = { eatTime, diaryId ->
                                navController.navigate(
                                    Screen.AddFood.createRoute(eatTime, diaryId)
                                )
                            })
                        }
                        composable(NavigationItem.Food.route) {
                            FoodScreen(navController = navController, paddingValues = paddingValues)
                        }
                        composable(NavigationItem.Profile.route) {
                            ProfileScreen(
                                navController = navController,
                                paddingValues = paddingValues
                            )
                        }
                        composable(Screen.EditProfileRoute.route) {
                            EditProfileScreen(
                                navController = navController,
                                paddingValues = paddingValues
                            )
                        }
                        composable(
                            route = Screen.AddFood.route,
                            arguments = listOf(navArgument("eatTime") {
                                type =
                                    NavType.StringType
                            }, navArgument("diaryId") { type = NavType.IntType })
                        ) {
                            val eatTime = it.arguments?.getString("eatTime") ?: "Pilih Makanan"
                            val diaryId = it.arguments?.getInt("diaryId") ?: 0
                            AddFoodScreen(
                                eatTime = eatTime,
                                diaryId = diaryId,
                                navigateToCreateFood = {
                                    navController.navigate(
                                        Screen.CreateFood.route
                                    )
                                })
                        }
                        composable(route = Screen.CreateFood.route) {
                            CreateFoodScreen(navigateBack = { navController.navigateUp() })
                        }
                    }
                }
            )
        }
    )
}