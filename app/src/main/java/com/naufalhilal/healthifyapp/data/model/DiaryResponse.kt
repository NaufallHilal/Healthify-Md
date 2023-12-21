package com.naufalhilal.healthifyapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiaryResponse(

    @field:SerializedName("diaryList")
    val diaryList: List<DiaryListItem?>? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class DiaryListItem(

    @field:SerializedName("calorie_target")
    val calorieTarget: Int? = null,

    @field:SerializedName("diary_date")
    val diaryDate: String? = null,

    @field:SerializedName("diary_id")
    val diaryId: Int? = null
) : Parcelable
