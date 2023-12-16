package com.naufalhilal.healthifyapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import java.util.Date

@Entity(
    tableName = "diary",
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
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val diary_id: Int = 0,

    @ColumnInfo(name = "user_id")
    val user_id: Int,

    @ColumnInfo(name = "diary_date")
    val diary_date: Date,

    @ColumnInfo(name = "calorie_target")
    val calorie_target: Float
)
