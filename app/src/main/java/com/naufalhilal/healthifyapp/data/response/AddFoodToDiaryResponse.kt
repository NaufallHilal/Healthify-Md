package com.naufalhilal.healthifyapp.data.response

import com.google.gson.annotations.SerializedName

data class AddFoodToDiaryResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
