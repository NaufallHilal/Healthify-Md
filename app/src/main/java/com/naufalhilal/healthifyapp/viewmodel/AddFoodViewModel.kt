package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.api.ApiConfig
import com.naufalhilal.healthifyapp.data.model.AddFoodToDiaryData
import com.naufalhilal.healthifyapp.data.model.FoodResponse
import com.naufalhilal.healthifyapp.data.model.ResponseMessage
import com.naufalhilal.healthifyapp.data.repository.HealthifyRepo
import com.naufalhilal.healthifyapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class AddFoodViewModel @Inject constructor(private val repository: HealthifyRepo) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FoodResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FoodResponse>>
        get() = _uiState
    private val _uiStateAddFoodToDiary: MutableStateFlow<ResponseMessage> = MutableStateFlow(
        ResponseMessage()
    )
    val uiStateAddFoodToDiaryData: StateFlow<ResponseMessage>
        get() = _uiStateAddFoodToDiary

    fun getAllFood() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Success(ApiConfig.getApiService().getAllFoods())
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message)
            }
        }
    }

    suspend fun addFoodToDiary(diaryId: Int, eatTime: String, foodId: Int): ResponseMessage {
        return suspendCancellableCoroutine { continuation ->
            val addFoodToDiaryData =
                AddFoodToDiaryData(diary_id = diaryId, eat_time = eatTime, food_id = foodId)
            viewModelScope.launch {
                val success = ApiConfig.getApiService().addFoodToDiary(addFoodToDiaryData)
                continuation.resume(success)
            }
        }
    }
}
