package com.naufalhilal.healthifyapp.ui.route

import androidx.annotation.StringRes
import com.naufalhilal.healthifyapp.R

sealed class NavigationItem(val route: String, @StringRes val resourceId: Int) {
    data object Home : NavigationItem("home", R.string.Home)
    data object Diary : NavigationItem("diary", R.string.Diary)
    data object Food : NavigationItem("food", R.string.Food)
    data object Profile : NavigationItem("profile", R.string.Profile)
}