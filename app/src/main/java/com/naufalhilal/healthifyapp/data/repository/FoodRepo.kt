package com.naufalhilal.healthifyapp.data.repository

import com.naufalhilal.healthifyapp.data.api.ApiService
import com.naufalhilal.healthifyapp.data.local.database.AppDatabase
import com.naufalhilal.healthifyapp.data.local.entity.Food
import com.naufalhilal.healthifyapp.data.model.AddToDiaryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodRepo @Inject constructor(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService
) {
    // Menambahkan makanan ke database lokal
    suspend fun addFood(food: Food) {
        appDatabase.foodDao().insertFood(food) // Menggunakan DAO dari database
    }

    // Mengupdate makanan di database lokal
    suspend fun updateFood(food: Food) {
        appDatabase.foodDao().updateFood(food) // Menggunakan DAO dari database
    }

    // Menghapus makanan dari database lokal
    suspend fun deleteFood(food: Food) {
        appDatabase.foodDao().deleteFood(food) // Menggunakan DAO dari database
    }

    // Mendapatkan semua makanan dari database lokal
    fun getAllFoods(): Flow<List<Food>> {
        return flow {
            val foods = appDatabase.foodDao().getAllFoods() // Menggunakan DAO dari database
            emit(foods)
        }.flowOn(Dispatchers.IO)
    }

    // Mendapatkan makanan berdasarkan ID dari database lokal
    fun getFoodById(id: Int): Flow<Food> {
        return flow {
            val food = appDatabase.foodDao().getFoodById(id) // Menggunakan DAO dari database
            emit(food)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchAllFoodsFromApi(): List<Food> {
        return apiService.getAllFoods()
    }

    suspend fun addFoodToDiary(diaryId: Int, foodId: Int, eatTime: String): Boolean {
        return try {
            val response = apiService.addToDiary(
                AddToDiaryRequest(diaryId, foodId, eatTime)
            )
            !response.error
        } catch (e: Exception) {
            false
        }
    }
}
