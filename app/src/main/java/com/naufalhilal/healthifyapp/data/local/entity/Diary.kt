package com.naufalhilal.healthifyapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "diary")
@Parcelize
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val diary_id: Int = 0,

    @ColumnInfo(name = "diary_date")
    val diary_date: String,

    @ColumnInfo(name = "calorie_target")
    val calorie_target: Float
) : Parcelable
