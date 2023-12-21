package com.naufalhilal.healthifyapp.data.api

import com.naufalhilal.healthifyapp.data.local.entity.Diary
import com.naufalhilal.healthifyapp.data.local.entity.EatTime
import com.naufalhilal.healthifyapp.data.local.entity.Food
import com.naufalhilal.healthifyapp.data.local.entity.HealthData
import com.naufalhilal.healthifyapp.data.model.AddToDiaryRequest
import com.naufalhilal.healthifyapp.data.model.CheckDiaryRequest
import com.naufalhilal.healthifyapp.data.model.CheckDiaryResponse
import com.naufalhilal.healthifyapp.data.model.DiaryWithFoodNames
import com.naufalhilal.healthifyapp.data.model.ResponseMessage
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Get all foods
    @GET("food")
    suspend fun getAllFoods(): List<Food>

    // Get food by ID
    @GET("food/{id}")
    suspend fun getFoodById(@Path("id") id: Int): Food

    // Get all diaries
    @GET("diary")
    suspend fun getAllDiaries(): List<Diary>

    // Get diary by ID
    @GET("diary/{id}")
    suspend fun getDiaryById(@Path("id") id: Int): Diary

    // Get diary by ID with food names
    @GET("diary/diaryId/{id}")
    suspend fun getDiaryWithFoodNamesById(@Path("id") id: Int): DiaryWithFoodNames

    // Get all eat-time
    @GET("eat-time")
    suspend fun getAllEatTimes(): List<EatTime>

    // Get eat-time by ID
    @GET("eat-time/{id}")
    suspend fun getEatTimeById(@Path("id") id: Int): EatTime

    // Get all health data
    @GET("health-data")
    suspend fun getAllHealthData(): List<HealthData>

    // Get health data by ID
    @GET("health-data/{id}")
    suspend fun getHealthDataById(@Path("id") id: Int): HealthData

    // Get DiaryID based on selected date and user ID
    @POST("diary/checkDiary")
    suspend fun checkDiary(@Body request: CheckDiaryRequest): CheckDiaryResponse

    // Add food
    @POST("food")
    suspend fun addFood(@Body food: Food): ResponseMessage

    // Add user food to the user diary for that day
    @POST("food/addToDiary")
    suspend fun addToDiary(@Body request: AddToDiaryRequest): ResponseMessage

}