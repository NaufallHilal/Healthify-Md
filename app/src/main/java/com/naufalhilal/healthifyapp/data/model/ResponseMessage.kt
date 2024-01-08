package com.naufalhilal.healthifyapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseMessage(
    val error: Boolean? = null,
    val message: String? = null
)

data class CheckDiaryResponse(

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("diary_id")
    val diaryId: Int? = null
)

data class CreateDiaryResponse(

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("diary_id")
    val diaryId: Int? = null
)


data class AddFoodToDiaryData(
    val diary_id: Int? = null,
    val food_id: Int? = null,
    val eat_time: String? = null
)

data class GetFoodInDiaryResponse(

    @field:SerializedName("diaryDetailsWithFoodAndCalories")
    val diaryDetailsWithFoodAndCalories: List<DiaryDetailsWithFoodAndCaloriesItem?>? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class DiaryDetailsWithFoodAndCaloriesItem(

    @field:SerializedName("food_name")
    val foodName: String? = null,

    @field:SerializedName("eat_time")
    val eatTime: String? = null,

    @field:SerializedName("eat_time_id")
    val eatTimeId: Int? = null,

    @field:SerializedName("calories")
    val calories: Int? = null,

    @field:SerializedName("food_id")
    val foodId: Int? = null,

    @field:SerializedName("diary_id")
    val diaryId: Int? = null
)

data class CheckDiaryData(
    val user_id: Int,
    val diary_date: String
)

data class WeightEntry(
    val date: String? = null,
    val weight: Int? = null
)

data class CaloriePredictionRequest(
    val protein: Int? = null,
    val fat: Int? = null,
    val carbohydrate: Int? = null
)

data class CaloriePredictionResponse(
    val calories: Double? = null
)

data class FoodRecommendationRequest(
    val sex: String? = null,
    val age: Int? = null,
    val weight: Float? = null,
    val height: Float? = null,
    val activity_level: Float? = null,
    val weight_target: Float? = null,
    val pace: String? = null,
    val vegetarian: String? = null
)

data class FoodRecommendationResponse(
    val message: String? = null,
    val recommended_foods: List<List<Any>>? = null
)

data class UserModel(
    val email: String,
    val token: String,
    val userId: Int,
    val isLogin: Boolean = false
)

data class LoginData(
    val password: String? = null,
    val username: String? = null
)

data class RegisterData(
    val password: String? = null,
    val username: String? = null,
    val email: String? = null
)

data class CreateFoodData(
    val user_id: Int? = null,
    val calories: Int? = null,
    val protein: Int? = null,
    val carbohydrate: Int? = null,
    val fat: Int? = null,
    val food_name: String? = null
)

data class CreateDiaryData(
    val user_id: Int? = null,
    val diary_date: String? = null,
    val calorie_target: Int? = null
)
