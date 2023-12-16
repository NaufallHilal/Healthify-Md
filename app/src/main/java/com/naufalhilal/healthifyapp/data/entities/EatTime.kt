package com.naufalhilal.healthifyapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "eat_time",
    foreignKeys = [
        ForeignKey(
            entity = Diary::class,
            parentColumns = ["diary_id"],
            childColumns = ["diary_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Food::class,
            parentColumns = ["food_id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EatTime(
    @PrimaryKey(autoGenerate = true)
    val eat_time_id: Int = 0,

    @ColumnInfo(name = "diary_id")
    val diary_id: Int,

    @ColumnInfo(name = "food_id")
    val food_id: Int,

    @ColumnInfo(name = "eat_time")
    val eat_time: String,

    @ColumnInfo(name = "food_name")
    val food_name: String
)

