package com.naufalhilal.healthifyapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthDataResponse(

    @field:SerializedName("healthDataList")
    val healthDataList: HealthDataListItem? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class HealthDataListItem(

    @field:SerializedName("health_data_id")
    val healthDataId: Int? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("weight")
    val weight: Int? = null,

    @field:SerializedName("heart_rate")
    val heartRate: Int? = null,

    @field:SerializedName("calories")
    val calories: Int? = null,

    @field:SerializedName("steps")
    val steps: Int? = null,

    @field:SerializedName("height")
    val height: Int? = null
) : Parcelable
