package com.naufalhilal.healthifyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.local.entity.Food

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: Food)

    @Update
    fun updateFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Query("SELECT * FROM foods")
    fun getAllFoods(): List<Food>

    @Query("SELECT * FROM foods WHERE food_id = :id")
    fun getFoodById(id: Int): Food
}