package com.naufalhilal.healthifyapp.ui.screen.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.data.RegisterData
import com.naufalhilal.healthifyapp.data.response.LoginResponse
import com.naufalhilal.healthifyapp.data.response.RegisterResponse
import com.naufalhilal.healthifyapp.data.retrofit.ApiConfig
import com.naufalhilal.healthifyapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val repository: HealthifyRepository):ViewModel() {
    private val _uiState: MutableStateFlow<UiState<RegisterResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<RegisterResponse>>
        get() = _uiState

    private val _error:MutableStateFlow<String> = MutableStateFlow("")
    val error:StateFlow<String>
        get() = _error

    fun postRegister(username:String,email:String,password:String){
        _uiState.value=UiState.Loading
        val registerData=RegisterData(username = username, email = email, password = password)
        val client=ApiConfig.getApiService().register(registerData)
        client.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val responseBody=response.body()
                if (response.isSuccessful&&responseBody!=null){
                    _uiState.value=UiState.Success(responseBody)
                    Log.e("Register",responseBody.message.toString())
                }else{
                    _uiState.value=UiState.Error(responseBody?.message)
                    _error.value=responseBody?.message.toString()
                    Log.e("Register error","on Failure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _uiState.value=UiState.Error(t.message)
                _error.value=t.message.toString()
                Log.e("failure response","on Failure ${t.message}")
            }

        })
    }
}