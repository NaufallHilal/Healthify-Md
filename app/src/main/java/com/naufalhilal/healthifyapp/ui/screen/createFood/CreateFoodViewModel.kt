package com.naufalhilal.healthifyapp.ui.screen.createFood

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.CreateFoodData
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.data.pref.UserModel
import com.naufalhilal.healthifyapp.data.response.CreateFoodResponse
import com.naufalhilal.healthifyapp.data.retrofit.ApiConfig
import com.naufalhilal.healthifyapp.ui.common.UiState
import kotlin.coroutines.resume
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class CreateFoodViewModel(private val repository: HealthifyRepository):ViewModel() {
    private val _uiState: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<UserModel>>
        get() =_uiState

    fun getSession(){
        viewModelScope.launch {
            repository.getSession()
                .collect { session ->
                    Log.e("sessionViewmodel crete food",session.toString())
                    _uiState.value = UiState.Success(session)
                }
        }
    }

    suspend fun createFood(
        user_id:Int,
        calories:Int,
        protein:Int,
        carbohydrate:Int,
        fat:Int,
        food_name:String,
    ): CreateFoodResponse {
        return suspendCancellableCoroutine { continuation ->
            val createFoodData =CreateFoodData(user_id = user_id, calories =  calories, protein = protein, carbohydrate = carbohydrate, fat = fat, food_name = food_name)
            viewModelScope.launch {
                val success = ApiConfig.getApiService()
                    .createFood(createFoodData)
                Log.e("create Food",createFoodData.toString())
                continuation.resume(success)
            }
        }
    }
}