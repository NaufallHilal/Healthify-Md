package com.naufalhilal.healthifyapp.data.api

import com.naufalhilal.healthifyapp.data.entity.Diary
import com.naufalhilal.healthifyapp.data.entity.EatTime
import com.naufalhilal.healthifyapp.data.entity.Food
import com.naufalhilal.healthifyapp.data.entity.HealthData
import com.naufalhilal.healthifyapp.data.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("diary/{id}")
    suspend fun getDiaryById(@Path("id") id: Int): Response<Diary>

    @GET("diary/user/{userId}")
    suspend fun getDiariesByUserId(@Path("userId") userId: Int): Response<List<Diary>>

    @POST("diary")
    suspend fun insertDiary(@Body diary: Diary): Response<Void>

    // EatTime Endpoints
    @GET("eat-time/{id}")
    suspend fun getEatTimeById(@Path("id") id: Int): Response<EatTime>

    @GET("eat-time/diary/{diaryId}")
    suspend fun getEatTimesByDiaryId(@Path("diaryId") diaryId: Int): Response<List<EatTime>>

    @POST("eat-time")
    suspend fun insertEatTime(@Body eatTime: EatTime): Response<Void>

    // Food Endpoints
    @GET("food/{id}")
    suspend fun getFoodById(@Path("id") id: Int): Response<Food>

    @GET("food/user/{userId}")
    suspend fun getFoodsByUserId(@Path("userId") userId: Int): Response<List<Food>>

    @POST("food")
    suspend fun insertFood(@Body food: Food): Response<Void>

    // HealthData Endpoints
    @GET("health-data/{id}")
    suspend fun getHealthDataById(@Path("id") id: Int): Response<HealthData>

    @GET("health-data/user/{userId}")
    suspend fun getHealthDataByUserId(@Path("userId") userId: Int): Response<List<HealthData>>

    @POST("health-data")
    suspend fun insertHealthData(@Body healthData: HealthData): Response<Void>

    // User Endpoints
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<User>

    @GET("user/username/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): Response<User?>

    @POST("user")
    suspend fun insertUser(@Body user: User): Response<Void>
}
