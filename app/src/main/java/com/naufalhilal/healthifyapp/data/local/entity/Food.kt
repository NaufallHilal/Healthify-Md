package com.naufalhilal.healthifyapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "foods")
@Parcelize
data class Food(
    @PrimaryKey(autoGenerate = true)
    val food_id: Int = 0,

    @ColumnInfo(name = "calories")
    val calories: Float,

    @ColumnInfo(name = "protein")
    val protein: Float,

    @ColumnInfo(name = "carbohydrate")
    val carbohydrate: Float,

    @ColumnInfo(name = "fat")
    val fat: Float,

    @ColumnInfo(name = "food_name")
    val food_name: String
) : Parcelable
