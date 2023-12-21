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
