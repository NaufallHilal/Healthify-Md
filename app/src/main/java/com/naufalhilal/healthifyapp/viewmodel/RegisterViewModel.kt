package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import com.naufalhilal.healthifyapp.data.api.ApiConfig
import com.naufalhilal.healthifyapp.data.model.RegisterData
import com.naufalhilal.healthifyapp.data.model.RegisterResponse
import com.naufalhilal.healthifyapp.data.repository.HealthifyRepo
import com.naufalhilal.healthifyapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: HealthifyRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<RegisterResponse>>(UiState.Loading)
    val uiState: StateFlow<UiState<RegisterResponse>> get() = _uiState

    private val _error = MutableStateFlow<String>("")
    val error: StateFlow<String> get() = _error

    fun postRegister(username: String, email: String, password: String) {
        _uiState.value = UiState.Loading
        val registerData = RegisterData(username = username, email = email, password = password)
        ApiConfig.getApiService().register(registerData).enqueue(createCallback())
    }

    private fun createCallback(): Callback<RegisterResponse> = object : Callback<RegisterResponse> {
        override fun onResponse(
            call: Call<RegisterResponse>,
            response: Response<RegisterResponse>
        ) {
            handleResponse(response)
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            handleError(t.message)
        }
    }

    private fun handleResponse(response: Response<RegisterResponse>) {
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            _uiState.value = UiState.Success(responseBody)
            Timber.tag("Register").e(responseBody.message.toString())
        } else {
            _uiState.value = UiState.Error(responseBody?.message)
            _error.value = responseBody?.message.toString()
            Timber.tag("Register error").e("on Failure %s", response.message())
        }
    }

    private fun handleError(message: String?) {
        _uiState.value = UiState.Error(message)
        _error.value = message ?: ""
        Timber.tag("failure response").e("on Failure %s", message)
    }
}
