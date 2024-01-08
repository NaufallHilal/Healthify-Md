package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.api.ApiConfig
import com.naufalhilal.healthifyapp.data.model.LoginData
import com.naufalhilal.healthifyapp.data.model.LoginResponse
import com.naufalhilal.healthifyapp.data.model.UserModel
import com.naufalhilal.healthifyapp.data.repository.HealthifyRepo
import com.naufalhilal.healthifyapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: HealthifyRepo) : ViewModel() {

    private val _token: MutableStateFlow<String> = MutableStateFlow("")
    val token: StateFlow<String>
        get() = _token

    private val _uiState: MutableStateFlow<UiState<LoginResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<LoginResponse>>
        get() = _uiState

    private val _error: MutableStateFlow<String> = MutableStateFlow("")
    val error: StateFlow<String>
        get() = _error

    fun postLogin(email: String, password: String) {
        _uiState.value = UiState.Loading
        val loginData = LoginData(username = email, password = password)
        val client = ApiConfig.getApiService().login(loginData)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _uiState.value = UiState.Success(responseBody)
                    _token.value = responseBody.loginResult?.token.toString()
                    Timber.tag("Login").e("Success Login")
                } else {
                    _uiState.value = UiState.Error(responseBody?.message)
                    _error.value = responseBody?.message.toString()
                    Timber.tag("Login error").e("on Failure %s", response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _uiState.value = UiState.Error(t.message)
                _error.value = t.message.toString()
                Timber.tag("failure response").e("on Failure %s", t.message)
            }

        })
    }

    suspend fun saveSession(user: UserModel): Boolean {
        Timber.tag("userId Login to preference").e(user.userId.toString())
        return suspendCancellableCoroutine { continuation ->
            viewModelScope.launch {
                val success = repository.saveSession(user)
                continuation.resume(success)
            }
        }
    }
}