package com.naufalhilal.healthifyapp.data.model

data class ResponseMessage(
    val error: Boolean,
    val message: String
)

data class CheckDiaryRequest(
    val user_id: Int,
    val diary_date: String
)

data class CheckDiaryResponse(
    val error: Boolean,
    val message: String,
    val diary_id: Int? = null,
    val diaryDetails: Any? = null
)

data class AddToDiaryRequest(
    val diary_id: Int,
    val food_id: Int,
    val eat_time: String
)

data class DiaryWithFoodNames(
    val error: Boolean,
    val message: String,
    val diaryDetailsWithFood: List<DiaryDetailWithFood>
)

data class DiaryDetailWithFood(
    val eat_time_id: Int,
    val diary_id: Int,
    val food_id: Int,
    val eat_time: String,
    val food_name: String
)

data class WeightEntry(
    val date: String,
    val weight: Int
)

data class CaloriePredictionRequest(
    val protein: Int,
    val fat: Int,
    val carbohydrate: Int
)

data class CaloriePredictionResponse(
    val calories: Double
)

data class FoodRecommendationRequest(
    val sex: String,
    val age: Int,
    val weight: Float,
    val height: Float,
    val activity_level: Float,
    val weight_target: Float,
    val pace: String,
    val vegetarian: String
)

data class FoodRecommendationResponse(
    val message: String,
    val recommended_foods: List<List<Any>>
)
