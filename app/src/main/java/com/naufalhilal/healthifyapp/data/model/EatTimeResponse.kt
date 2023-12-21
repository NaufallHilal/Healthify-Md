package com.naufalhilal.healthifyapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EatTimeResponse(

    @field:SerializedName("eatTimeList")
    val eatTimeList: List<EatTimeListItem?>? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class EatTimeListItem(

    @field:SerializedName("eat_time")
    val eatTime: String? = null,

    @field:SerializedName("eat_time_id")
    val eatTimeId: Int? = null,

    @field:SerializedName("food_id")
    val foodId: Int? = null,

    @field:SerializedName("diary_id")
    val diaryId: Int? = null
) : Parcelable
