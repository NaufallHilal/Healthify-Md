package com.naufalhilal.healthifyapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "foods",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Food(
    @PrimaryKey(autoGenerate = true)
    val food_id: Int = 0,

    @ColumnInfo(name = "user_id")
    val user_id: Int,

    @ColumnInfo(name = "calories")
    val calories: Float,

    @ColumnInfo(name = "protein")
    val protein: Float,

    @ColumnInfo(name = "carbohydrate")
    val carbohydrate: Float,

    @ColumnInfo(name = "fat")
    val fat: Float
)
