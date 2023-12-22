package com.naufalhilal.healthifyapp.data.response

import com.google.gson.annotations.SerializedName

data class CreateDiaryResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("diary_id")
	val diaryId: Int? = null
)
