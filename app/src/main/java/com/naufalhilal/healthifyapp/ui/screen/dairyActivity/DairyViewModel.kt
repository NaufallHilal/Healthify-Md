package com.naufalhilal.healthifyapp.ui.screen.dairyActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.CheckDiaryData
import com.naufalhilal.healthifyapp.data.CreateDiaryData
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.data.pref.UserModel
import com.naufalhilal.healthifyapp.data.response.CheckDiaryResponse
import com.naufalhilal.healthifyapp.data.response.CreateDiaryResponse
import com.naufalhilal.healthifyapp.data.response.GetFoodInDiaryResponse
import com.naufalhilal.healthifyapp.data.response.HealthDataResponse
import com.naufalhilal.healthifyapp.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DairyViewModel(private val repository: HealthifyRepository):ViewModel() {
    private val _uiStateHealthData: MutableStateFlow<HealthDataResponse> = MutableStateFlow(
        HealthDataResponse()
    )
    val uiStateHealthData: StateFlow<HealthDataResponse>
        get() =_uiStateHealthData

    private val _uiStateCheckDiary: MutableStateFlow<CheckDiaryResponse> = MutableStateFlow(
        CheckDiaryResponse()
    )
    val uiStateCheckDiary: StateFlow<CheckDiaryResponse>
        get() =_uiStateCheckDiary

    private val _uiStateCreateDiary: MutableStateFlow<CreateDiaryResponse> = MutableStateFlow(
        CreateDiaryResponse()
    )
    val uiStateCreateDiary: StateFlow<CreateDiaryResponse>
        get() =_uiStateCreateDiary

    private val _uiStateGetFoodFromDiary: MutableStateFlow<GetFoodInDiaryResponse> = MutableStateFlow(
        GetFoodInDiaryResponse()
    )
    val uiStateGetFoodFromDiary: StateFlow<GetFoodInDiaryResponse>
        get() =_uiStateGetFoodFromDiary


    private val _session: MutableStateFlow<UserModel> = MutableStateFlow(UserModel("","",0,false))
    val session: StateFlow<UserModel>
        get() =_session

    fun getHealthDataFromUserId(userId:Int){
        val endpoint="health-data/$userId"
        viewModelScope.launch {
            try {
                _uiStateHealthData.value=ApiConfig.getApiService().getHealthData(endpoint)
            }catch (e:Exception){
                _uiStateHealthData.value=HealthDataResponse(message = e.message.toString(), error = true)
            }

        }
    }

    fun getSession(){
        viewModelScope.launch {
            repository.getSession()
                .collect { session ->
                    Log.e("sessionViewmodel crete food",session.toString())
                    _session.value = session
                }
        }
    }
    fun checkDiary(userId:Int,date:String){
        val checkDiaryData =CheckDiaryData(user_id = userId, diary_date = date)
        viewModelScope.launch {
            try {
                _uiStateCheckDiary.value=ApiConfig.getApiService().checkDiary(checkDiaryData)
            }catch (e:Exception){
                _uiStateCheckDiary.value= CheckDiaryResponse(message = e.message.toString(), error = true)
            }
        }
    }
    fun createDiary(userId:Int,date:String,calories:Int){
        val createDiaryData= CreateDiaryData(user_id =  userId, diary_date = date, calorie_target = calories)
        viewModelScope.launch {
            try {
                _uiStateCreateDiary.value=ApiConfig.getApiService().createDiary(createDiaryData)
            }catch (e:Exception){
                _uiStateCreateDiary.value=CreateDiaryResponse(message = e.message.toString(), error = true)
            }
        }
    }

    fun getFoodFromDiary(diaryId:Int){
        val endpoint="diary/diaryId/$diaryId"
        viewModelScope.launch {
            try {
                _uiStateGetFoodFromDiary.value=ApiConfig.getApiService().getFoodInDiary(endpoint)
            }catch (e:Exception){
                _uiStateGetFoodFromDiary.value=GetFoodInDiaryResponse(message = e.message.toString(), error = true)
            }
        }
    }
}