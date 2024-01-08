package com.naufalhilal.healthifyapp.data.model

data class ResponseMessage(
    val error: Boolean? = null,
    val message: String? = null
)

data class CheckDiaryRequest(
    val user_id: Int? = null,
    val diary_date: String? = null
)

data class CheckDiaryResponse(
    val error: Boolean? = null,
    val message: String? = null,
    val diaryId: Int? = null,
    val diaryDetails: Any? = null
)

data class AddFoodToDiaryData(
    val diary_id: Int? = null,
    val food_id: Int? = null,
    val eat_time: String? = null
)

data class DiaryWithFoodNames(
    val error: Boolean? = null,
    val message: String? = null,
    val diaryDetailsWithFood: List<DiaryDetailWithFood>? = null
)

data class DiaryDetailWithFood(
    val eat_time_id: Int? = null,
    val diary_id: Int? = null,
    val food_id: Int? = null,
    val eat_time: String? = null,
    val food_name: String? = null,
    val calories: Int? = null
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
