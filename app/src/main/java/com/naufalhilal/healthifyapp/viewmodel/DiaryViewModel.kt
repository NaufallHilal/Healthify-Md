package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.api.ApiConfig
import com.naufalhilal.healthifyapp.data.model.CheckDiaryRequest
import com.naufalhilal.healthifyapp.data.model.CheckDiaryResponse
import com.naufalhilal.healthifyapp.data.model.CreateDiaryData
import com.naufalhilal.healthifyapp.data.model.DiaryWithFoodNames
import com.naufalhilal.healthifyapp.data.model.HealthDataResponse
import com.naufalhilal.healthifyapp.data.model.UserModel
import com.naufalhilal.healthifyapp.data.repository.HealthifyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(private val repository: HealthifyRepo) : ViewModel() {

    private val _uiStateHealthData: MutableStateFlow<HealthDataResponse> =
        MutableStateFlow(HealthDataResponse())
    val uiStateHealthData: StateFlow<HealthDataResponse>
        get() = _uiStateHealthData

    private val _uiStateCheckDiary: MutableStateFlow<CheckDiaryResponse> = MutableStateFlow(
        CheckDiaryResponse()
    )
    val uiStateCheckDiary: StateFlow<CheckDiaryResponse>
        get() = _uiStateCheckDiary

    private val _uiStateCreateDiary: MutableStateFlow<CheckDiaryResponse> =
        MutableStateFlow(CheckDiaryResponse())
    val uiStateCreateDiary: StateFlow<CheckDiaryResponse>
        get() = _uiStateCreateDiary

    private val _uiStateGetFoodFromDiary: MutableStateFlow<DiaryWithFoodNames> =
        MutableStateFlow(DiaryWithFoodNames())
    val uiStateGetFoodFromDiary: StateFlow<DiaryWithFoodNames>
        get() = _uiStateGetFoodFromDiary

    private val _session: MutableStateFlow<UserModel> =
        MutableStateFlow(UserModel("", "", 0, false))
    val session: StateFlow<UserModel>
        get() = _session

    fun getHealthDataFromUserId(userId: Int) {
        val endpoint = "health-data/$userId"
        viewModelScope.launch {
            try {
                _uiStateHealthData.value = ApiConfig.getApiService().getHealthData(endpoint)
            } catch (e: Exception) {
                _uiStateHealthData.value =
                    HealthDataResponse(message = e.message.toString(), error = true)
            }

        }
    }

    fun getSession() {
        viewModelScope.launch {
            repository.getSession()
                .collect { session ->
                    Timber.tag("sessionViewmodel crete food").e(session.toString())
                    _session.value = session
                }
        }
    }

    fun checkDiary(userId: Int, date: String) {
        val checkDiaryData = CheckDiaryRequest(user_id = userId, diary_date = date)
        viewModelScope.launch {
            try {
                _uiStateCheckDiary.value = ApiConfig.getApiService().checkDiary(checkDiaryData)
            } catch (e: Exception) {
                _uiStateCheckDiary.value =
                    CheckDiaryResponse(message = e.message.toString(), error = true)
            }
        }
    }

    fun createDiary(userId: Int, date: String, calories: Int) {
        val createDiaryData =
            CreateDiaryData(user_id = userId, diary_date = date, calorie_target = calories)
        viewModelScope.launch {
            try {
                _uiStateCreateDiary.value = ApiConfig.getApiService().createDiary(createDiaryData)
            } catch (e: Exception) {
                _uiStateCreateDiary.value =
                    CheckDiaryResponse(message = e.message.toString(), error = true)
            }
        }
    }

    fun getFoodFromDiary(diaryId: Int) {
        val endpoint = "diary/diaryId/$diaryId"
        viewModelScope.launch {
            try {
                _uiStateGetFoodFromDiary.value = ApiConfig.getApiService().getFoodInDiary(endpoint)
            } catch (e: Exception) {
                _uiStateGetFoodFromDiary.value =
                    DiaryWithFoodNames(message = e.message.toString(), error = true)
            }
        }
    }
}