package com.naufalhilal.healthifyapp.ui.screen.addFood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.AddFoodToDiaryData
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.data.response.AddFoodToDiaryResponse
import com.naufalhilal.healthifyapp.data.response.AllFoodResponse
import com.naufalhilal.healthifyapp.data.retrofit.ApiConfig
import com.naufalhilal.healthifyapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AddFoodViewModel(private val repository: HealthifyRepository):ViewModel() {
    private val _uiState:MutableStateFlow<UiState<AllFoodResponse>> = MutableStateFlow(UiState.Loading)
    val uiState:StateFlow<UiState<AllFoodResponse>>
        get() =_uiState
    private val _uiStateAddFoodToDiary: MutableStateFlow<AddFoodToDiaryResponse> = MutableStateFlow(
        AddFoodToDiaryResponse()
    )
    val uiStateAddFoodToDiaryData: StateFlow<AddFoodToDiaryResponse>
        get() =_uiStateAddFoodToDiary

    fun getAllFood(){
        viewModelScope.launch {
            try {
                _uiState.value=UiState.Success(ApiConfig.getApiService().getAllFood())
            }catch (e:Exception){
                _uiState.value=UiState.Error(e.message)
            }

        }
    }
    suspend fun addFoodToDiary(diaryId:Int, eatTime:String, foodId:Int):AddFoodToDiaryResponse {
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