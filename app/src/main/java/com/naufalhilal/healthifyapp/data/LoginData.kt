package com.naufalhilal.healthifyapp.data

import retrofit2.http.Part

data class LoginData (
    val password:String,
    val username:String
)
data class RegisterData(
    val password:String,
    val username:String,
    val email:String,
)
data class CreateFoodData(
    val user_id:Int,
    val calories:Int,
    val protein:Int,
    val carbohydrate:Int,
    val fat:Int,
    val food_name:String,
)

data class CheckDiaryData (
    val user_id:Int,
    val diary_date:String
)
data class CreateDiaryData (
    val user_id:Int,
    val diary_date:String,
    val calorie_target:Int
)
data class AddFoodToDiaryData (
    val diary_id:Int,
    val food_id:Int,
    val eat_time:String
)