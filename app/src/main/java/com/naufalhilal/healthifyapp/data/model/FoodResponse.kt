package com.naufalhilal.healthifyapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodResponse(

    @field:SerializedName("listFoods")
    val listFoods: List<List<ListFoodsItemItem?>?>? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class ListFoodsItemItem(

    @field:SerializedName("food_name")
    val foodName: String? = null,

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
) : Parcelable
