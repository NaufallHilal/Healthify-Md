package com.naufalhilal.healthifyapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "health_data",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ], indices = [Index("user_id")]
)
data class HealthData(
    @PrimaryKey(autoGenerate = true)
    val health_data_id: Int = 0,

    @ColumnInfo(name = "user_id")
    val user_id: Int,

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
)
