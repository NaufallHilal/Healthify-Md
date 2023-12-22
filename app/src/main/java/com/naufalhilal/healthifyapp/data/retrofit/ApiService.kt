package com.naufalhilal.healthifyapp.data.retrofit

import com.naufalhilal.healthifyapp.data.AddFoodToDiaryData
import com.naufalhilal.healthifyapp.data.CheckDiaryData
import com.naufalhilal.healthifyapp.data.CreateDiaryData
import com.naufalhilal.healthifyapp.data.CreateFoodData
import com.naufalhilal.healthifyapp.data.LoginData
import com.naufalhilal.healthifyapp.data.RegisterData
import com.naufalhilal.healthifyapp.data.response.AddFoodToDiaryResponse
import com.naufalhilal.healthifyapp.data.response.AllFoodResponse
import com.naufalhilal.healthifyapp.data.response.CheckDiaryResponse
import com.naufalhilal.healthifyapp.data.response.CreateDiaryResponse
import com.naufalhilal.healthifyapp.data.response.CreateFoodResponse
import com.naufalhilal.healthifyapp.data.response.GetFoodInDiaryResponse
import com.naufalhilal.healthifyapp.data.response.HealthDataResponse
import com.naufalhilal.healthifyapp.data.response.LoginResponse
import com.naufalhilal.healthifyapp.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("login")
    fun login(
        @Body loginData:LoginData,
    ): Call<LoginResponse>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("register")
    fun register(
        @Body registerData: RegisterData,
    ):Call<RegisterResponse>

    @GET("food")
    suspend fun getAllFood():AllFoodResponse

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("food")
    suspend fun createFood(
        @Body createFoodData: CreateFoodData
    ): CreateFoodResponse

    @GET
    suspend fun getHealthData(
        @Url url: String,
    ):HealthDataResponse

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("diary/checkDiary")
    suspend fun checkDiary(
        @Body chechDiaryData:CheckDiaryData
    ):CheckDiaryResponse

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("diary")
    suspend fun createDiary(
        @Body createDiaryData:CreateDiaryData
    ):CreateDiaryResponse

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("food/addToDiary/")
    suspend fun addFoodToDiary(
        @Body addFoodToDiaryData: AddFoodToDiaryData
    ): AddFoodToDiaryResponse

    @GET
    suspend fun getFoodInDiary(
        @Url url: String,
    ):GetFoodInDiaryResponse
}