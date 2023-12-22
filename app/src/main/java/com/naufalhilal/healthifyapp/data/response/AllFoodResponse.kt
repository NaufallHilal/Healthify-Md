package com.naufalhilal.healthifyapp.data.response

import com.google.gson.annotations.SerializedName

data class AllFoodResponse(

	@field:SerializedName("listFoods")
	val listFoods: List<List<ListFoodsItemItem>> ,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ListFoodsItemItem(

	@field:SerializedName("food_name")
	val foodName: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("protein")
	val protein: Int? = null,

	@field:SerializedName("fat")
	val fat: Int? = null,

	@field:SerializedName("calories")
	val calories: Int? = null,

	@field:SerializedName("food_id")
	val foodId: Int? = null,

	@field:SerializedName("carbohydrate")
	val carbohydrate: Int? = null
)
