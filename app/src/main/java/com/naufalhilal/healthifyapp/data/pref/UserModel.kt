package com.naufalhilal.healthifyapp.data.pref

data class UserModel (
    val email:String,
    val token:String,
    val userId:Int,
    val isLogin:Boolean=false
)