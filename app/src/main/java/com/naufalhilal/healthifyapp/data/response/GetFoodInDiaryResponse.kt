package com.naufalhilal.healthifyapp.data.response

import com.google.gson.annotations.SerializedName

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
