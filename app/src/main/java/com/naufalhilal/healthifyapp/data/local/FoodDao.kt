package com.naufalhilal.healthifyapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.entities.Diary
import com.naufalhilal.healthifyapp.data.entities.Food

@Dao
interface FoodDao {
    @Insert
    fun insertFood(food: Food)

    @Update
    fun updateFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Query("SELECT * FROM foods WHERE food_id = :id")
    fun getFoodById(id: Int): Food

    @Query("SELECT * FROM foods WHERE user_id = :userId")
    fun getFoodsByUserId(userId: Int): List<Food>
}