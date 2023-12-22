package com.naufalhilal.healthifyapp.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.data.pref.UserModel
import com.naufalhilal.healthifyapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HealthifyRepository):ViewModel() {
    private val _uiState:MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val uiState:StateFlow<UiState<UserModel>>
        get() =_uiState

    fun getSession(){
        viewModelScope.launch {
            repository.getSession()
                .collect { session ->
                    Log.e("sessionViewmodel",session.toString())
                    _uiState.value = UiState.Success(session)
                }
        }
    }

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }
}