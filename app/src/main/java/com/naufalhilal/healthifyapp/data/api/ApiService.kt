package com.naufalhilal.healthifyapp.data.api

import com.naufalhilal.healthifyapp.data.local.entity.Diary
import com.naufalhilal.healthifyapp.data.local.entity.EatTime
import com.naufalhilal.healthifyapp.data.local.entity.Food
import com.naufalhilal.healthifyapp.data.local.entity.HealthData
import com.naufalhilal.healthifyapp.data.model.AddFoodToDiaryData
import com.naufalhilal.healthifyapp.data.model.CaloriePredictionRequest
import com.naufalhilal.healthifyapp.data.model.CaloriePredictionResponse
import com.naufalhilal.healthifyapp.data.model.CheckDiaryRequest
import com.naufalhilal.healthifyapp.data.model.CheckDiaryResponse
import com.naufalhilal.healthifyapp.data.model.CreateDiaryData
import com.naufalhilal.healthifyapp.data.model.CreateFoodData
import com.naufalhilal.healthifyapp.data.model.DiaryWithFoodNames
import com.naufalhilal.healthifyapp.data.model.FoodRecommendationRequest
import com.naufalhilal.healthifyapp.data.model.FoodRecommendationResponse
import com.naufalhilal.healthifyapp.data.model.FoodResponse
import com.naufalhilal.healthifyapp.data.model.HealthDataResponse
import com.naufalhilal.healthifyapp.data.model.LoginData
import com.naufalhilal.healthifyapp.data.model.LoginResponse
import com.naufalhilal.healthifyapp.data.model.RegisterData
import com.naufalhilal.healthifyapp.data.model.RegisterResponse
import com.naufalhilal.healthifyapp.data.model.ResponseMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    // Get all foods
    @GET("food")
    suspend fun getAllFoods(): FoodResponse

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
    @GET
    suspend fun getFoodInDiary(
        @Url url: String,
    ): DiaryWithFoodNames

    // Get all eat-time
    @GET("eat-time")
    suspend fun getAllEatTimes(): List<EatTime>

    // Get eat-time by ID
    @GET("eat-time/{id}")
    suspend fun getEatTimeById(@Path("id") id: Int): EatTime

    // Get all health data
    @GET
    suspend fun getHealthData(
        @Url url: String,
    ): HealthDataResponse


    // Get health data by ID
    @GET("health-data/{id}")
    suspend fun getHealthDataById(@Path("id") id: Int): HealthData

    // Get DiaryID based on selected date and user ID
    @POST("diary/checkDiary")
    suspend fun checkDiary(@Body request: CheckDiaryRequest): CheckDiaryResponse

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("diary")
    suspend fun createDiary(
        @Body createDiaryData: CreateDiaryData
    ): CheckDiaryResponse

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("food/addToDiary/")
    suspend fun addFoodToDiary(
        @Body addFoodToDiaryData: AddFoodToDiaryData
    ): ResponseMessage

    // Add food
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("food")
    suspend fun createFood(
        @Body createFoodData: CreateFoodData
    ): FoodResponse

    // Add user food to the user diary for that day
    @POST("food/addToDiary")
    suspend fun addToDiary(@Body request: AddFoodToDiaryData): ResponseMessage

    @POST("predict")
    suspend fun predictCalories(@Body request: CaloriePredictionRequest): CaloriePredictionResponse

    // Food Recommendation Model API
    @POST("recommend_foods")
    suspend fun recommendFoods(@Body request: FoodRecommendationRequest): FoodRecommendationResponse

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("login")
    fun login(@Body loginData: LoginData): Call<LoginResponse>

    // Register endpoint
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("register")
    fun register(@Body registerData: RegisterData): Call<RegisterResponse>
}




