package com.naufalhilal.healthifyapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "health_data")
@Parcelize
data class HealthData(
    @PrimaryKey(autoGenerate = true)
    val health_data_id: Int = 0,

    @ColumnInfo(name = "calories")
    val calories: Float,

    @ColumnInfo(name = "weight")
    val weight: Float,

    @ColumnInfo(name = "height")
    val height: Float,

    @ColumnInfo(name = "steps")
    val steps: Float,

    @ColumnInfo(name = "heart_rate")
    val heart_rate: Float
) : Parcelable
