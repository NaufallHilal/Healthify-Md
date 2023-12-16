package com.naufalhilal.healthifyapp.ui.route

sealed class Routes(val routes: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Search : Routes("search")
    object Diary : Routes("diary")
    object Food : Routes("food")
    object Profile : Routes("profile")
    object Detail : Routes("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}