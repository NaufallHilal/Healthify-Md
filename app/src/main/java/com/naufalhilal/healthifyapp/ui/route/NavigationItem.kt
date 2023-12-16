package com.naufalhilal.healthifyapp.ui.route

import com.naufalhilal.healthifyapp.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem(Routes.Home.routes, R.drawable.ic_dot, "Home")
    object Diary : NavigationItem(Routes.Diary.routes, R.drawable.ic_dot, "Diary")
    object Food : NavigationItem(Routes.Food.routes, R.drawable.ic_dot, "Food")
    object Profile : NavigationItem(Routes.Profile.routes, R.drawable.ic_dot, "Profile")
}
