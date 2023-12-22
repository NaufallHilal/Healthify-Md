package com.naufalhilal.healthifyapp.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.data.LoginData
import com.naufalhilal.healthifyapp.data.pref.UserModel
import com.naufalhilal.healthifyapp.data.response.LoginResponse
import com.naufalhilal.healthifyapp.data.retrofit.ApiConfig
import com.naufalhilal.healthifyapp.ui.common.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

class LoginViewModel(private val repository: HealthifyRepository):ViewModel() {
    private val _token: MutableStateFlow<String> = MutableStateFlow("")
    val token: StateFlow<String>
        get() =_token

    private val _uiState:MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Loading)
    val uiState:StateFlow<UiState<LoginResponse>>
        get() = _uiState

    private val _error:MutableStateFlow<String> = MutableStateFlow("")
    val error:StateFlow<String>
        get() = _error

    fun postLogin(email:String,password:String){
        _uiState.value=UiState.Loading
        val loginData=LoginData(username = email, password = password)
        val client= ApiConfig.getApiService().login(loginData)
        client.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responseBody=response.body()
                if (response.isSuccessful&&responseBody!=null){
                    _uiState.value=UiState.Success(responseBody)
                    _token.value=responseBody.loginResult?.token.toString()
                    Log.e("Login","Success Login")
                }else{
                    _uiState.value=UiState.Error(responseBody?.message)
                    _error.value=responseBody?.message.toString()
                    Log.e("Login error","on Failure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _uiState.value=UiState.Error(t.message)
                _error.value=t.message.toString()
                Log.e("failure response","on Failure ${t.message}")
            }

        })
    }
    suspend fun saveSession(user: UserModel): Boolean {
        Log.e("userId Login to preference",user.userId.toString())
        return suspendCancellableCoroutine { continuation ->
            viewModelScope.launch {
                val success = repository.saveSession(user)
                continuation.resume(success)
            }
        }
    }
}