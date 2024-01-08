package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.api.ApiConfig
import com.naufalhilal.healthifyapp.data.model.CreateFoodData
import com.naufalhilal.healthifyapp.data.model.FoodResponse
import com.naufalhilal.healthifyapp.data.model.UserModel
import com.naufalhilal.healthifyapp.data.repository.HealthifyRepo
import com.naufalhilal.healthifyapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class CreateFoodViewModel @Inject constructor(private val repository: HealthifyRepo) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<UserModel>>
        get() = _uiState

    fun getSession() {
        viewModelScope.launch {
            repository.getSession()
                .collect { session ->
                    Timber.tag("sessionViewmodel crete food").e(session.toString())
                    _uiState.value = UiState.Success(session)
                }
        }
    }

    suspend fun createFood(
        user_id: Int,
        calories: Int,
        protein: Int,
        carbohydrate: Int,
        fat: Int,
        food_name: String,
    ): FoodResponse {
        return suspendCancellableCoroutine { continuation ->
            val createFoodData = CreateFoodData(
                user_id = user_id,
                calories = calories,
                protein = protein,
                carbohydrate = carbohydrate,
                fat = fat,
                food_name = food_name
            )
            viewModelScope.launch {
                val success = ApiConfig.getApiService().createFood(createFoodData)
                Timber.tag("create Food").e(createFoodData.toString())
                continuation.resume(success)
            }
        }
    }
}
